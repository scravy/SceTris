/* FriggController.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.frigg;

import static de.fu.weave.xml.Namespaces.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.fu.junction.Tuple;
import de.fu.junction.annotation.meta.Author;
import de.fu.weave.AccessDeniedException;
import de.fu.weave.Controller;
import de.fu.weave.ControllerInstantiationException;
import de.fu.weave.Module;
import de.fu.weave.ModuleException;
import de.fu.weave.ModuleNotLoadedException;
import de.fu.weave.OutputConverter;
import de.fu.weave.RequestMethod;
import de.fu.weave.Session;
import de.fu.weave.TemplateManagerException;
import de.fu.weave.UnknownActionException;
import de.fu.weave.UnsupportedResultTypeException;
import de.fu.weave.impl.BaseController;
import de.fu.weave.impl.ConnectionManagerImpl;
import de.fu.weave.impl.ControllerException;
import de.fu.weave.orm.ConnectionManager;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.orm.RelationManager;
import de.fu.weave.reflect.ActionReflector;
import de.fu.weave.reflect.ModuleReflector;
import de.fu.weave.util.Config;
import de.fu.weave.xml.PrintableException;

/**
 * 
 */
@Author("Julian Fleischer")
abstract public class FriggController<R extends RelationManager> extends BaseController implements
		Controller<R> {

	/**
	 * #{@link java.io.Serializable}
	 */
	private static final long serialVersionUID = 6235035043142302821L;

	/**
	 * 
	 * @param $type
	 * @return
	 */
	public static String getMimeType(final String $type) {
		String $mimeType = "text/html";

		if ($type.equals("xml") || $type.equals("xsl")) $mimeType = "text/xml";
		if ($type.equals("xhtml")) $mimeType = "application/xhtml+xml";
		return $mimeType;
	}

	/**
	 * A Map of OutputConverters for certain Output-Types.
	 */
	final private Map<String,OutputConverter> outputConverters = new HashMap<String,OutputConverter>();

	/**
	 * The first Module to load.
	 */
	final private Tuple<String,Class<? extends Module<R>>> startModule;

	/**
	 * {@link #getLoadedModules()}
	 */
	final private Map<String,ModuleReflector<? extends Module<R>>> loadedModules =
			new HashMap<String,ModuleReflector<? extends Module<R>>>();

	/**
	 * {@link #getConnectionManager()}
	 */
	final private ConnectionManager connectionManager;

	/**
	 * 
	 */
	private final String firstPage;

	/**
	 * The context the Servlet lives in.
	 * <p>
	 * This field is set during initialization.
	 */
	private ServletContext $context;

	/**
	 * Hols a key-value map storing configuration items.
	 * <p>
	 * Typically these will come from web.xml in the web applications WEB-INF directory.
	 */
	private final Map<String,String> $config = new HashMap<String,String>();

	/**
	 * The RelationManager which is used by this Controller.
	 */
	private R relationManager = null;

	/**
	 * 
	 */
	private final Tuple<String,Class<? extends Module<R>>>[] initiallyLoadedModules;

	/**
	 * 
	 */
	final private Class<R> relationManagerClass;

	private boolean $lazy = false;

	public FriggController(final Class<R> entityManagerClass,
			final Tuple<String,Class<? extends Module<R>>> firstModule,
			final Tuple<String,Class<? extends Module<R>>>... modulesToBeLoaded)
			throws ControllerInstantiationException {
		super();
		Logger.getLogger(org.apache.fop.render.ImageHandlerRegistry.class).setLevel(Level.OFF);
		Logger.getLogger(org.apache.fop.render.XMLHandlerRegistry.class).setLevel(Level.OFF);
		Logger.getLogger(org.apache.xmlgraphics.image.loader.spi.ImageImplRegistry.class).setLevel(
			Level.OFF);
		Logger.getLogger(org.apache.fop.util.ContentHandlerFactoryRegistry.class).setLevel(Level.OFF);
		Logger.getLogger(org.apache.fop.render.RendererFactory.class).setLevel(Level.OFF);
		try {
			connectionManager = new ConnectionManagerImpl(new Config($config));

			firstPage = firstModule.fst;
			startModule = firstModule;
			initiallyLoadedModules = modulesToBeLoaded;
			relationManagerClass = entityManagerClass;
		} catch (Exception e) {
			throw new ControllerInstantiationException(e);
		}
	}

	/**
	 * Override this if you want
	 * 
	 * @param module
	 * @param action
	 * @throws Exception
	 */
	protected void afterAction(final Module<R> module, final ActionReflector<? extends Module<R>> action)
			throws Exception {
		// skip
	}

	/**
	 * Override this if you want
	 * 
	 * @param servletConfig
	 * @throws Exception
	 */
	protected void afterInitialization(final ServletConfig servletConfig) throws Exception {
		// skip
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
				FriggErrorHandler<R> errorHandler = new FriggErrorHandler<R>(this);
				errorHandler.setException(new PrintableException(servletException));

				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/xml");

				String stylesheetDefinition =
						"<?xml-stylesheet type=\"text/xsl\" href=\"" + $context.getContextPath()
								+ "/" + $config.get("dir:public-xsl") + "/error.xsl\"?>\n";

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

		if (path == null)
			redirect = request.getRequestURI() + '/' + firstPage + '/';
		else {
			String[] pathParts = path.split("/+", 4);
			for (int i = 0; i < pathParts.length; i++)
				pathParts[i] = pathParts[i].trim();

			if (pathParts.length == 2) {
				if (pathParts[1].length() == 0)
					redirect = requestURI + firstPage + '/';
				else
					redirect = requestURI + '/';
			} else if (pathParts.length >= 3) {
				moduleName = pathParts[1];
				actionName = pathParts[2];
				if (pathParts.length > 3) params = pathParts[3].split("/+");
			}
		}

		if (redirect != null)
			response.sendRedirect(redirect);
		else {
			Session session = new de.fu.weave.impl.SessionImpl(request, relationManager);
			String template = null;
			Document document = null;

			Map<String,String[]> parameterMap = getRequestParameters(request, method);

			try {
				Module<R> modObj;
				try {
					if (moduleName.charAt(0) == '~') {
						String specialActionName = moduleName.substring(1);
						SpecialAction<R> specialAction = null;
						if ("login".equals(specialActionName)) {
							specialAction = new FriggLoginHandler<R>(this);
							template = "login.xsl";
						} else if ("logout".equals(specialActionName)) {
							specialAction = new FriggLogoutHandler<R>(this);
							template = "logout.xsl";
						} else
							throw new Exception("Unknown Special Action");
						modObj = specialAction;
						specialAction.setRelationManager(relationManager);
						specialAction.setSession(session);
						try {
							specialAction.doIt(parameterMap);
						} catch (SpecialActionException e) {
							specialAction.setException(new PrintableException(e));
						}
					} else {

						if (!isModuleLoaded(moduleName))
							throw new ModuleNotLoadedException("request module " + moduleName
									+ " is not loaded. Loaded are: "
									+ Arrays.toString(loadedModules.keySet().toArray()));
						ModuleReflector<? extends Module<R>> module = getLoadedModule(moduleName);

						modObj = module.newInstance(this);
						ActionReflector<? extends Module<R>> action = null;

						modObj.setRelationManager(relationManager);
						modObj.setSession(session);

						try {
							if (!module.hasAction(actionName)) {
								action = module.getAction("");
								throw new UnknownActionException("The action \"" + actionName
										+ "\" is not known by the module \"" + moduleName + "\".");
							} else {
								action = module.getAction(actionName);
								template = action.getTemplateName();
							}
							if (action.isPrivilegeRequired())
								if (!action.isUserAllowedToExecute(session.getUser()))
									throw new AccessDeniedException(
											"You lack the privilege to show this page.");

							beforeAction(modObj, action);
							if ((method == RequestMethod.POST) && action.hasCommitAction()) {
								modObj.invokeCommitAction(actionName, params, parameterMap);
								response.sendRedirect($context.getContextPath()
										+ request.getServletPath()
										+ '/' + moduleName + '/' + action.getAfterCommit());
								return;
							} else
								modObj.invokeAction(actionName, params, parameterMap);
							afterAction(modObj, action);
						} catch (Exception e) {
							if (!action.isExceptionHandled(e)) throw e;
							modObj.setException(new PrintableException(e));
						}

					}
				} catch (DatabaseException e) {
					connectionManager.validate();
					throw e;
				}
				modObj.put("user", session.getUser());
				document = modObj.getDocument();

				String type = modObj.forcesType() ? modObj.getType() : session.getContentType();
				String mimeType = modObj.forcesMimeType() ? modObj.getMimeType() : getMimeType(type);

				if (modObj.hasRedirect()) {
					String target;
					if (modObj.getRedirect() instanceof URL)
						target = ((URL) modObj.getRedirect()).toString();
					else
						target = $context.getContextPath() + request.getServletPath() + '/'
								+ modObj.getRedirect();
					response.sendRedirect(target);
				} else if (modObj.hasBinaryData()) {
					if (modObj.forcesMimeType()) response.setContentType(modObj.getMimeType());
					String filename = modObj.getFilename();
					if (filename != null)
						response.setHeader("Content-Disposition", "attachment; filename=" + filename);
					InputStream data = modObj.getBinaryData();
					int c;
					while ((c = data.read()) >= 0)
						response.getOutputStream().write(c);
				} else {
					responseApplyAttributes(document, session, request, $context, moduleName,
											actionName);
					responseApplyRequest(document, parameterMap, method);
					responsePerform(response, document, template, type, mimeType);
				}
			} catch (IOException e) {
				throw e;
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}

	/**
	 * Override this if you want
	 * 
	 * @param module
	 * @param action
	 * @throws Exception
	 * @since Iteration4
	 */
	protected void beforeAction(final Module<R> module, final ActionReflector<? extends Module<R>> action)
			throws Exception {
		// skip
	}

	/**
	 * Override this if you want
	 * 
	 * @param servletConfig
	 * @throws Exception
	 * @since Iteration4
	 */
	protected void beforeInitialization(final ServletConfig servletConfig) throws Exception {
		// skip
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

	@Override
	public ServletContext getContext() {
		return $context;
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
	 */
	@Override
	public void init(final ServletConfig $servletConfig) throws ServletException {
		try {
			$logger.info("Starting up.");
			beforeInitialization($servletConfig);
			$context = $servletConfig.getServletContext();
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
				throw new ControllerException("Entity Manager object could not be instantiated.");
			} catch (InvocationTargetException e) {
				throw new ControllerException("Entity Manager failed to startup.");
			}

			$config.put("dir:xsl", "xsl");
			$config.put("dir:public-xsl", "xsl");

			String baseDir = $config.get("dir:xsl");
			String realBaseDir = $context.getRealPath(baseDir);
			if (realBaseDir == null) realBaseDir = baseDir;
			templateManager.setBaseDir(realBaseDir);
			if ($lazy)
				$logger.info("Lazyload, templates will be dynamically loaded at runtime");
			loadModules();
			connectionManager.connect();
			$logger.info("Connected.");
			afterInitialization($servletConfig);
		} catch (ServletException $exc) {
			throw $exc;
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	public boolean isModuleLoaded(final String module) {
		return loadedModules.containsKey(module);
	}

	@Override
	public String loadModule(final Class<? extends Module<R>> $class) throws ModuleException {
		ModuleReflector<? extends Module<R>> module = ModuleReflector.forClass($class);
		String $moduleName = module.getName().intern();
		return loadModule($moduleName, $class);
	}

	public String loadModule(final String $moduleName, final Class<? extends Module<R>> $class)
			throws ModuleException {
		ModuleReflector<? extends Module<R>> $module = ModuleReflector.forClass($class);

		$logger.debug("Loading Module " + $moduleName);
		try {
			for (ActionReflector<? extends Module<R>> actionMethod : $module.getActions()) {
				$logger.debug("Loading Template " + actionMethod.getTemplateName());
				if (!$lazy)
					templateManager.loadTemplate(actionMethod.getTemplateName());
			}
			loadedModules.put($moduleName, $module);
		} catch (TemplateManagerException $exc) {
			throw new ModuleException($exc);
		}
		return $moduleName;
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
		loadModule(firstPage, startModule.snd);
		for (Tuple<String,Class<? extends Module<R>>> module : initiallyLoadedModules)
			loadModule(module.fst, module.snd);
	}

	@Override
	public boolean loadOutputConverter(final Class<? extends OutputConverter> converter,
									   final String name) {
		try {
			outputConverters.put(name, converter.newInstance());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param filename
	 */
	public boolean loadProperties(final String filename, final String prefix) {
		File file = new File(filename);
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(file));

			String theKey;
			for (String key : props.stringPropertyNames())
				if (key.startsWith(prefix)) {
					theKey = key.substring(prefix.length());
					if (theKey.equals("webapp.db.database"))
						$config.put("name", props.getProperty(key));
					else if (theKey.equals("webapp.db.username"))
						$config.put("user", props.getProperty(key));
					else if (theKey.equals("webapp.db.password"))
						$config.put("password", props.getProperty(key));
					else if (theKey.equals("webapp.db.hostname"))
						$config.put("host", props.getProperty(key));
					else if (theKey.equals("webapp.db.port"))
						$config.put("port", props.getProperty(key));
					else if (theKey.equals("templates.lazyload") && props.getProperty(key).equals("yes"))
						$lazy = true;
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
		if (actionName != null) documentElement.setAttribute("action", actionName);
		documentElement.setAttribute("first-page", firstPage);
		documentElement.setAttribute("first-page-path",
									 context.getContextPath() + request.getServletPath()
											 + '/' + firstPage);

		documentElement.setAttribute("lang", session.getLanguage());
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
	 * @throws UnsupportedResultTypeException
	 * @since Iteration3
	 */
	private void responsePerform(final HttpServletResponse response, final Document document,
								 final String template, final String type,
								 final String mimeType) throws IOException,
			TemplateManagerException, TransformerException, UnsupportedResultTypeException {
		response.setHeader("Be-Bold",
						   "Break the rules, and break them beautifully, deliberately and well");

		final Transformer transformer = templateManager.getTemplate(template);
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
		if (outputConverters.containsKey(type)) { // custom
			OutputConverter converter = outputConverters.get(type);
			response.setContentType(converter.getMimeType());
			String charset = converter.getCharacterEncoding();
			if (charset != null) response.setCharacterEncoding(charset);
			Source source = new DOMSource(document);
			if (converter.isAppliedAfterNativeTemplate()) {
				final PipedOutputStream stream = new PipedOutputStream();
				final StreamResult intermediateResult = new StreamResult(stream);
				final Source intermediateSource = source;
				source = new StreamSource(new PipedInputStream(stream));
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							transformer.transform(intermediateSource, intermediateResult);
						} catch (Exception e) {
						} finally {
							try {
								stream.close();
							} catch (Exception e) {
							}
						}
					}
				}).start();
			}
			converter.convert(source, new StreamResult(response.getOutputStream()));
		} else {
			response.setContentType(mimeType);
			response.setCharacterEncoding("UTF-8");
			if (type.equals("xml"))
				xmlHelper.newXMLWriter(response.getWriter()).write(document);
			else if (type.equals("xsl")) { // xml with stylesheet-PI
				String stylesheetDefinition =
					"<?xml-stylesheet type=\"text/xsl\" href=\""
							+ $context.getContextPath() + "/" + $config.get("dir:public-xsl")
							+ "/" + template + "\"?>\n";
				xmlHelper.newXMLWriter(response.getWriter()).write(document, stylesheetDefinition);
			} else { // xhtml or html
				transformer.setOutputProperty(OutputKeys.METHOD, type.equals("xhtml") ? "xml" : "html");
				transformer.setOutputProperty(OutputKeys.MEDIA_TYPE, mimeType);
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
												type.equals("xhtml") ? "no" : "yes");
				Source source = new DOMSource(document);
				Result result = new StreamResult(response.getOutputStream());
				transformer.transform(source, result);
			}
		}
	}

	/**
	 * 
	 * @param name
	 * @since Iteration4
	 */
	public void unloadOutputConverter(final String name) {
		outputConverters.remove(name);
	}
}
