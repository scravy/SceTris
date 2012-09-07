/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.frigga;

import static de.fu.weave.xml.Namespaces.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
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
import de.fu.weave.TemplateManagerException;
import de.fu.weave.UnknownActionException;
import de.fu.weave.impl.ControllerException;
import de.fu.weave.impl.ymir.BaseController;
import de.fu.weave.impl.ymir.ConnectionManagerImpl;
import de.fu.weave.orm.ConnectionManager;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.orm.RelationManager;
import de.fu.weave.reflect.ActionReflector;
import de.fu.weave.reflect.ModuleReflector;
import de.fu.weave.xml.PrintableException;

/**
 * 
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
abstract public class GenericController<R extends RelationManager> extends BaseController implements
		Controller<R> {

	/**
	 * #{@link java.io.Serializable}
	 */
	private static final long serialVersionUID = 6235035043142302821L;

	/**
	 * 
	 * @since Iteration3
	 */
	final private Class<? extends Module<R>> startModule;

	/**
	 * {@link #getLoadedModules()}
	 * 
	 * @since Iteration2
	 */
	final private Map<String,ModuleReflector<? extends Module<R>>> loadedModules =
			new TreeMap<String,ModuleReflector<? extends Module<R>>>();

	/**
	 * {@link #getConnectionManager()}
	 * 
	 * @since Iteration2
	 */
	final private ConnectionManager connectionManager;

	/**
	 * 
	 * @since Iteration2
	 */
	private String firstPage = null;

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
			final Class<? extends Module<R>> firstModule,
			final Class<? extends Module<R>>... modulesToBeLoaded)
			throws ParserConfigurationException, ControllerException {
		super();
		connectionManager = new ConnectionManagerImpl(new Config(config));

		startModule = firstModule;
		initiallyLoadedModules = modulesToBeLoaded;
		relationManagerClass = entityManagerClass;
	}

	@Override
	protected final void answerRequest(final HttpServletRequest request,
									   final HttpServletResponse response, final RequestMethod method)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
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
			Session session = new de.fu.weave.impl.ymir.SessionImpl(request, relationManager);
			String template = null;
			Document document = null;

			Map<String,String[]> parameterMap = getRequestParameters(request, method);

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

							if ((method == RequestMethod.POST) && action.hasCommitAction()) {
								moduleInstance.invokeCommitAction(actionName, params, parameterMap);
								response.sendRedirect(context.getContextPath()
										+ request.getServletPath()
										+ '/' + moduleName + '/' + action.getAfterCommit());
								return;
							} else {
								moduleInstance.invokeAction(actionName, params, parameterMap);
							}
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
					connectionManager.validate();
					throw e;
				}

				String type = session.getContentType();
				String mimeType = getMimeType(type);

				responseApplyAttributes(document, session, request, context, moduleName, actionName);
				responseApplyRequest(document, parameterMap, method);
				responsePerform(response, document, template, type, mimeType);
			} catch (IOException e) {
				throw e;
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
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
			initBefore(servletConfig);
			context = servletConfig.getServletContext();
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

			config.put("dir:templates", "xsl");
			config.put("public-xsl", "xsl");

			String baseDir = config.get("dir:templates");
			String realBaseDir = context.getRealPath(baseDir);
			templateManager.setBaseDir(realBaseDir);

			loadModules();
			templateManager.setBaseDir(context.getRealPath(config.get("dir:templates")));
			connectionManager.connect();
			initAfter(servletConfig);
		} catch (ServletException e) {
			throw e;
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void initAfter(final ServletConfig servletConfig) throws Exception {
		// override this if you want.
	}

	protected void initBefore(final ServletConfig servletConfig) throws Exception {
		// override this if you want.
	}

	@Override
	public boolean isModuleLoaded(final String module) {
		return loadedModules.containsKey(module);
	}

	@Override
	public String loadModule(final Class<? extends Module<R>> clazz) throws ModuleException {
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
		firstPage = loadModule(startModule);
		for (Class<? extends Module<R>> module : initiallyLoadedModules) {
			loadModule(module);
		}
	}

	/**
	 * 
	 * @param filename
	 * @since Iteration3
	 */
	public boolean loadProperties(final String filename, final String prefix) {
		File file = new File(filename);
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(file));

			String theKey;
			for (String key : props.stringPropertyNames()) {
				if (key.startsWith(prefix)) {
					theKey = key.substring(prefix.length());
					if (theKey.equals("webapp.db.database")) {
						config.put("name", props.getProperty(key));
					} else if (theKey.equals("webapp.db.username")) {
						config.put("user", props.getProperty(key));
					} else if (theKey.equals("webapp.db.password")) {
						config.put("password", props.getProperty(key));
					} else if (theKey.equals("webapp.db.hostname")) {
						config.put("host", props.getProperty(key));
					} else if (theKey.equals("webapp.db.port")) {
						config.put("port", props.getProperty(key));
					}
				}
			}

		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/**
	 * @param documentElement
	 * @param request
	 * @param context2
	 * @since Iteration3
	 */
	private void responseApplyAttributes(final Document document,
										 final Session session,
										 final HttpServletRequest request,
										 final ServletContext context,
										 final String moduleName,
										 final String actionName) {
		Element documentElement = document.getDocumentElement();
		documentElement.setAttribute("context-path", context.getContextPath());
		documentElement.setAttribute("servlet-path", context.getContextPath() +
				request.getServletPath());

		String queryString = request.getQueryString();
		try {
			documentElement
					.setAttribute("query-string",
								  queryString == null ? "" : URLDecoder.decode(queryString, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
		}

		documentElement.setAttribute("module-path",
									 context.getContextPath() + request.getServletPath()
											 + '/' + moduleName);

		documentElement.setAttribute("module", moduleName);
		if (actionName != null) {
			documentElement.setAttribute("action", actionName);
		}
		documentElement.setAttribute("first-page", firstPage);
		documentElement.setAttribute("first-page-path",
									 context.getContextPath() + request.getServletPath()
											 + '/' + firstPage);

		documentElement.setAttribute("language", session.getLocale());
		documentElement.setAttribute("style", session.getStylesheet());
		documentElement.setAttribute("type", session.getContentType());
		documentElement.setAttribute("logged-in", session.isUserLoggedIn() ? "yes" : "no");

	}

	/**
	 * @param documentElement
	 * @param parameterMap
	 * @param method
	 * @since Iteration3
	 */
	private void responseApplyRequest(final Document document,
									  final Map<String,String[]> parameterMap,
									  final RequestMethod method) {
		Element documentElement = document.getDocumentElement();
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
	}

	/**
	 * @param response
	 * @param document
	 * @param type
	 * @param mimeType
	 * @throws IOException
	 * @throws TemplateManagerException
	 * @throws TransformerException
	 * @since Iteration3
	 */
	private void responsePerform(final HttpServletResponse response, final Document document,
								 final String template, final String type,
								 final String mimeType) throws IOException,
			TemplateManagerException, TransformerException {
		response.setHeader("Be-Bold",
						   "Break the rules, and break them beautifully, deliberately and well");
		response.setCharacterEncoding("UTF-8");
		response.setContentType(type.equals("plain") ? "text/plain" : mimeType);
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
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			Source source = new DOMSource(document);
			Result result = new StreamResult(response.getWriter());
			transformer.transform(source, result);
		}
	}
}
