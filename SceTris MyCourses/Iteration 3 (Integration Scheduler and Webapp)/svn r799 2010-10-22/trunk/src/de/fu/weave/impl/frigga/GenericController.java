/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.frigga;

import static de.fu.weave.xml.Namespaces.XMLNS_REQ;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.fu.weave.AccessDeniedException;
import de.fu.weave.Config;
import de.fu.weave.Controller;
import de.fu.weave.Module;
import de.fu.weave.ModuleException;
import de.fu.weave.RequestMethod;
import de.fu.weave.Session;
import de.fu.weave.TemplateManager;
import de.fu.weave.TemplateManagerException;
import de.fu.weave.UnknownActionException;
import de.fu.weave.orm.ConnectionManager;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.orm.RelationManager;
import de.fu.weave.reflect.ActionReflector;
import de.fu.weave.reflect.ModuleReflector;
import de.fu.weave.xml.PrintableException;
import de.fu.weave.xml.XmlHelper;

/**
 * 
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
abstract public class GenericController<R extends RelationManager> extends HttpServlet implements
		Controller<R> {

	/**
	 * #{@link java.io.Serializable}
	 */
	private static final long serialVersionUID = 6235035043142302821L;

	/**
	 * The name of the module responsible for the first page.
	 * 
	 * @since Iteration2
	 */
	private String firstPage = "start";

	/**
	 * {@link #getLoadedModules()}
	 * 
	 * @since Iteration2
	 */
	final private Map<String,ModuleReflector<? extends Module<R>>> loadedModules =
			new TreeMap<String,ModuleReflector<? extends Module<R>>>();

	/**
	 * {@link #getTemplateManager()}
	 * 
	 * @since Iteration2
	 */
	final private TemplateManager templateManager;

	/**
	 * {@link #getConnectionManager()}
	 * 
	 * @since Iteration2
	 */
	final private ConnectionManager connectionManager;

	/**
	 * An XML-Helper (providing methods for XSL-Transformations and DOM-Manipulation)
	 * 
	 * @since Iteration2
	 */
	final private XmlHelper xmlHelper;

	/**
	 * The context the Servlet lives in.
	 * <p>
	 * This field is set during initialization.
	 * 
	 * @since Iteration2
	 */
	private ServletContext context;

	/**
	 * Hols a key-value map storing configuration items.
	 * <p>
	 * Typically these will come from web.xml in the web applications WEB-INF directory.
	 * 
	 * @since Iteration2
	 */
	private final Map<String,String> config = new TreeMap<String,String>();

	/**
	 * @since Iteration2
	 */
	private R relationManager = null;

	/**
	 * @since Iteration2
	 */
	private final Class<? extends Module<R>>[] initiallyLoadedModules;

	/**
	 * @since Iteration2
	 */
	final private Class<R> relationManagerClass;

	/**
	 * Create a new MetaMeta-Controller
	 * <p>
	 * This constructor will initialize it's own XMLHelper, TemplateManager and ConnectionManager.
	 * 
	 * @throws ParserConfigurationException
	 *             throws a ParserConfigurationException (if, for example, your JRE is seriously
	 *             fucked up) since it can't live without a #
	 *             {@link javax.xml.parsers.DocumentBuilder}.
	 * @since Iteration2
	 */
	public GenericController(final Class<R> entityManagerClass,
			final Class<? extends Module<R>>... modulesToBeLoaded)
			throws ParserConfigurationException, ControllerException {
		super();
		xmlHelper = new XmlHelper();
		templateManager = new TemplateManager(xmlHelper);
		connectionManager = new ConnectionManagerImpl(new Config(config));

		initiallyLoadedModules = modulesToBeLoaded;
		relationManagerClass = entityManagerClass;
	}

	/**
	 * 
	 * 
	 * @param request
	 *            The original Request to get data from
	 * @param response
	 *            The response to write data to
	 * @param method
	 *            The HTTP Method used to provoke this Request
	 * @throws IOException
	 *             If something cannot be read or written
	 * @throws ServletException
	 *             If an Error occurs during processing the Request (most likely in a Module)
	 * 
	 * @since Iteration2
	 */
	private final void answerRequest(final HttpServletRequest request,
									 final HttpServletResponse response, final RequestMethod method)
			throws IOException, ServletException {

		try {
			answerRequestDoIt(request, response, method);
		} catch (ServletException servletException) {
			try {
				ErrorHandler<R> errorHandler = new ErrorHandler<R>(this);
				errorHandler.setException(new PrintableException(servletException));

				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/xml");

				String stylesheetDefinition =
						"<?xml-stylesheet type=\"text/xsl\" href=\"" + context.getContextPath()
								+ "/" + config.get("public-xsl") + "/error.xsl\"?>\n";

				xmlHelper.newXMLWriter(response.getWriter()).write(errorHandler.getDocument(),
																   stylesheetDefinition);
				/*
				 * Transformer transformer = templateManager.getTemplate("error.xsl"); Source source
				 * = new DOMSource(errorHandler.getDocument()); Result result = new
				 * StreamResult(response.getWriter()); transformer.transform(source, result);
				 */
			} catch (Exception newException) {
				throw servletException;
			}
		}
	}

	/**
	 * Handles a single request. This is where most of the action takes place.
	 * 
	 * @param request
	 *            The original Request to get data from
	 * @param response
	 *            The response to write data to
	 * @param method
	 *            The HTTP Method used to provoke this Request
	 * @throws IOException
	 *             If something cannot be read or written
	 * @throws ServletException
	 *             If an Error occurs during processing the Request (most likely in a Module)
	 * @since Iteration2
	 */
	private void answerRequestDoIt(final HttpServletRequest request,
								   final HttpServletResponse response, final RequestMethod method)
			throws IOException, ServletException {

		String path = request.getPathInfo();
		String requestURI = request.getRequestURI();

		String redirect = null;
		String moduleName = null;
		String actionName = null;
		String[] params = new String[0];

		if (path == null) {
			redirect = request.getRequestURI() + '/' + firstPage + '/';
		} else {
			String[] pathParts = path.split("/+", 4);
			for (int i = 0; i < pathParts.length; i++) {
				pathParts[i] = pathParts[i].trim();
			}

			if (pathParts.length == 2) {
				if (pathParts[1].length() == 0) {
					redirect = requestURI + firstPage + '/';
				} else {
					redirect = requestURI + '/';
				}
			} else if (pathParts.length >= 3) {
				moduleName = pathParts[1];
				actionName = pathParts[2];
				if (pathParts.length > 3) {
					params = pathParts[3].split("/+");
				}
			}
		}

		if (redirect != null) {
			response.sendRedirect(redirect);
		} else {
			Session session = new de.fu.weave.impl.frigga.SessionImpl(request, relationManager);
			String template = null;
			Document document = null;

			@SuppressWarnings("unchecked")
			// some javax.servlet-implementations do not yet feature EE 1.6
			Map<String,String[]> parameterMap = request.getParameterMap();

			try {
				try {
					if (moduleName.charAt(0) == '~') {
						String specialActionName = moduleName.substring(1);
						SpecialAction<R> specialAction = null;
						if ("login".equals(specialActionName)) {
							specialAction = new LoginHandler<R>(this);
							template = "login.xsl";
						} else if ("logout".equals(specialActionName)) {
							specialAction = new LogoutHandler<R>(this);
							template = "logout.xsl";
						} else {
							throw new Exception("Unknown Special Action");
						}
						specialAction.setEntityManager(relationManager);
						specialAction.setSession(session);
						try {
							specialAction.doIt(parameterMap);
						} catch (SpecialActionException e) {
							// FIXME: Is it okay to rely on put() here?
							// FIXME: Should put be moved to the Module Interface?
							specialAction.setException(new PrintableException(e));
						}
						specialAction.put("user", session.getUser());
						document = specialAction.getDocument();
					} else {

						if (!isModuleLoaded(moduleName)) {
							throw new ServletException("request module " + moduleName
									+ " is not loaded. Loaded are: "
									+ Arrays.toString(loadedModules.keySet().toArray()));
						}
						ModuleReflector<? extends Module<R>> module = getLoadedModule(moduleName);

						Module<R> moduleInstance = module.newInstance(this);
						ActionReflector<? extends Module<R>> action = null;

						if (module.isDatabaseRequired()) {
							moduleInstance.setEntityManager(relationManager);
						}
						moduleInstance.setSession(session);

						try {
							if (!module.hasAction(actionName)) {
								action = module.getAction("");
								throw new UnknownActionException("The action \"" + actionName
										+ "\" is not known by the module \"" + moduleName + "\".");
							} else {
								action = module.getAction(actionName);
								template = action.getTemplateName();
							}
							if (action.isPermissionRequired()) {
								if (!session.getUser().hasPrivilege(action.getRequiredPermission())) {
									throw new AccessDeniedException("You lack the privilege "
											+ action.getRequiredPermission());
								}
							}

							moduleInstance.invokeAction(actionName, params, parameterMap);
						} catch (Exception e) {
							if (!action.isExceptionHandled(e)) {
								throw e;
							}
							moduleInstance.setException(new PrintableException(e));
						}
						moduleInstance.put("user", session.getUser());
						document = moduleInstance.getDocument();
					}
				} catch (DatabaseException e) {
					// validate connection
					throw e; // needs to be thrown always
				}

				response.setHeader("Be-Bold",
								   "Break the rules, and break them beautifully, deliberately and well");

				String mimeType = "text/html";

				String type = session.getContentType();
				if (type.equals("xml") || type.equals("xsl")) {
					mimeType = "text/xml";
				}
				if (type.equals("xhtml")) {
					mimeType = "application/xhtml+xml";
				}

				Element documentElement = document.getDocumentElement();

				documentElement.setAttribute("context-path", context.getContextPath());
				documentElement.setAttribute("servlet-path", context.getContextPath() + '/'
						+ config.get("servlet-path"));
				documentElement.setAttribute("module-path",
											 context.getContextPath() + '/'
													 + config.get("servlet-path") + '/'
													 + moduleName);

				if (moduleName != null) {
					documentElement.setAttribute("module", moduleName);
				}
				if (actionName != null) {
					documentElement.setAttribute("action", actionName);
				}

				documentElement.setAttribute("language", session.getLocale());
				documentElement.setAttribute("style", session.getStylesheet());
				documentElement.setAttribute("type", session.getContentType());
				documentElement.setAttribute("logged-in", session.isUserLoggedIn() ? "yes" : "no");

				Element requestElement = document.createElementNS(XMLNS_REQ, "req:req");
				requestElement.setAttribute("method", method.toString());
				for (String key : parameterMap.keySet()) {
					String[] reqParams = parameterMap.get(key);
					for (String reqParam : reqParams) {
						Element paramElement = document.createElementNS(XMLNS_REQ, "req:arg");
						paramElement.setAttribute("name", key);
						paramElement.appendChild(document.createTextNode(reqParam));
						requestElement.appendChild(paramElement);
					}
				}
				documentElement.appendChild(requestElement);

				response.setCharacterEncoding("UTF-8");
				if (type.equals("plain")) {
					response.setContentType("text/plain");
				} else {
					response.setContentType(mimeType);
				}
				if (mimeType.equals("text/xml")) {
					if (type.equals("xml")) {
						xmlHelper.newXMLWriter(response.getWriter()).write(document);
					} else {
						String stylesheetDefinition =
								"<?xml-stylesheet type=\"text/xsl\" href=\""
										+ context.getContextPath() + "/" + config.get("public-xsl")
										+ "/" + template + "\"?>\n";

						xmlHelper.newXMLWriter(response.getWriter()).write(document,
																		   stylesheetDefinition);
					}
				} else {
					Transformer transformer = templateManager.getTemplate(template);

					Source source = new DOMSource(document);
					Result result = new StreamResult(response.getWriter());

					transformer.transform(source, result);
				}
			} catch (IOException e) {
				throw e;
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}

	/**
	 * Perform a GET request.
	 * <p>
	 * Basically this method simply passes the request to answerRequest, but maintains information
	 * about the RequestMethod (GET in this case).
	 * 
	 * @see de.fu.weave.annotation.Action
	 * @since Iteration2
	 */
	@Override
	public void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {
		answerRequest(request, response, RequestMethod.GET);
	}

	/**
	 * Perform a POST request.
	 * <p>
	 * Basically this method simply passes the request to answerRequest, but maintains information
	 * about the RequestMethod (POST in this case).
	 * 
	 * @see de.fu.weave.annotation.Commit
	 * @since Iteration2
	 */
	@Override
	public void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws java.io.IOException, javax.servlet.ServletException {
		answerRequest(request, response, RequestMethod.POST);
	}

	/**
	 * Parses the configuration and sanitizes in case of error.
	 * <p>
	 * If no templates directory (dir:templates) is specified it will be set to "xsl" by this
	 * method; if no first-page is specified it will take the one already stored in the private
	 * field firstPage.
	 * 
	 * @since Iteration2
	 */
	private void evaluateConfig() {
		if (!config.containsKey("dir:templates")) {
			config.put("dir:templates", "xsl");
		}
		if (!config.containsKey("first-page")) {
			config.put("first-page", firstPage);
		}
		if (!config.containsKey("public-xsl")) {
			config.put("public-xsl", "xsl");
		}
		firstPage = config.get("first-page");
		templateManager.setBaseDir(context.getRealPath(config.get("dir:templates")));
	}

	/**
	 * Retrieve the ConnectionManager used by this Servlet
	 * 
	 * @return This Controllers ConnectionManager
	 * @since Iteration2
	 */
	public ConnectionManager getConnectionManager() {
		return connectionManager;
	}

	/**
	 * Retrieve information about a certain Module.
	 * 
	 * @param module
	 *            The name of the module (not it's classname).
	 * @return The Reflector for that Module.
	 * @since Iteration2
	 */
	public ModuleReflector<? extends Module<R>> getLoadedModule(final String module) {
		return loadedModules.get(module);
	}

	@Override
	public Collection<ModuleReflector<? extends Module<R>>> getLoadedModules() {
		return loadedModules.values();
	}

	/**
	 * Retrieve the TemplateManager used by this Servlet
	 * 
	 * @return This Controllers TemplateManager
	 * @since Iteration2
	 */
	public TemplateManager getTemplateManager() {
		return templateManager;
	}

	@Override
	public XmlHelper getXmlHelper() {
		return xmlHelper;
	}

	/**
	 * This is called by the Webserver to put the servlet into service.
	 * <p>
	 * This' init() basically does 4 things: (1) load and (2) evaluate the configuration given by
	 * the servlets context, (3) load all modules specified in that configuration and open a
	 * connection to the database using a ConnectionManager created while constructing the Servlet.
	 * 
	 * @since Iteration2
	 */
	@Override
	public void init(final ServletConfig servletConfig) throws ServletException {
		try {
			try {
				Constructor<R> entityManagerConstructor =
						relationManagerClass.getConstructor(ConnectionManager.class);
				relationManager = entityManagerConstructor.newInstance(connectionManager);
			} catch (NoSuchMethodException e) {
				throw new ControllerException(
						"EntityManager does not provide required constructor", e);
			} catch (SecurityException e) {
				throw new ControllerException("...security... Freedom!", e);
			} catch (IllegalAccessException e) {
				throw new ControllerException("Entity Manager object is not accessible.");
			} catch (InstantiationException e) {
				throw new ControllerException("Entity Manager object coult not be instantiated.");
			} catch (InvocationTargetException e) {
				throw new ControllerException("Entity Manager failed to startup.");
			}

			loadConfig(servletConfig);
			evaluateConfig();

			loadModules();

			connectionManager.connect();
			// entityManager.loadSchema(xmlHelper.newDocument(context.getRealPath(config
			// .get("entities.xml"))));
		} catch (ServletException e) {
			throw e;
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * Check if a given Module is loaded
	 * 
	 * @see ModuleReflector
	 * @param module
	 *            The Name of the Module (not it's qualified Name)
	 * @return true or false.
	 * @since Iteration2
	 */
	public boolean isModuleLoaded(final String module) {
		return loadedModules.containsKey(module);
	}

	/**
	 * Load Config from a ServletConfig into this Controller
	 * 
	 * @param servletConfig
	 *            A given ServletConfig
	 * @since Iteration2
	 */
	private void loadConfig(final ServletConfig servletConfig) {
		context = servletConfig.getServletContext();

		@SuppressWarnings("unchecked")
		Enumeration<String> initParams = servletConfig.getInitParameterNames();
		while (initParams.hasMoreElements()) {
			String key = initParams.nextElement();
			config.put(key, servletConfig.getInitParameter(key));
		}
	}

	/**
	 * 
	 * 
	 * @since Iteration2
	 */
	private String loadModule(final Class<? extends Module<R>> clazz) throws ModuleException {
		ModuleReflector<? extends Module<R>> module = ModuleReflector.forClass(clazz);
		String moduleName = module.getName();

		try {
			for (ActionReflector<? extends Module<R>> actionMethod : module.getActions()) {
				templateManager.loadTemplate(actionMethod.getTemplateName());
			}
			loadedModules.put(moduleName, module);
		} catch (TemplateManagerException e) {
			throw new ModuleException(e);
		}
		return moduleName;
	}

	/**
	 * Load Modules into this Controller.
	 * <p>
	 * 
	 * @throws ServletException
	 *             If a Module was not found or bad referenced
	 * @throws ModuleException
	 *             If an invalid Module was attempted to be loaded
	 * @since Iteration2
	 */
	private void loadModules() throws ServletException, ModuleException {
		for (String key : config.keySet()) {
			String value = config.get(key);
			try {
				if (key.substring(0, 7).equals("module:")) {
					Class<?> clazz = Class.forName(value).asSubclass(Module.class);
					@SuppressWarnings("unchecked")
					// XXX: I just dont know any other solution right now
					String moduleName = loadModule((Class<? extends Module<R>>) clazz);
					if (!moduleName.equals(key.substring(7))) {
						throw new ServletException("Module " + moduleName
								+ " bad referenced in web.xml (as " + key + ")");
					}
				}
			} catch (ClassNotFoundException e) {
				throw new ServletException("The class " + value + " can not be found, hence " + key
						+ " could not be loaded.", e);
			}
		}
		for (Class<? extends Module<R>> module : initiallyLoadedModules) {
			loadModule(module);
		}
	}
}
