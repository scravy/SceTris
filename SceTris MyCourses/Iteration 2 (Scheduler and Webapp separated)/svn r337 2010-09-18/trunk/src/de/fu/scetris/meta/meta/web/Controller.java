package de.fu.scetris.meta.meta.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import de.fu.scetris.meta.meta.web.annotation.Action;
import de.fu.scetris.meta.meta.web.annotation.ModuleName;


/**
 * @author Julian
 */
public class Controller extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6235035043142302821L;

	/**
	 *
	 */
	private boolean isInitiated = false;
	
	/**
	 *
	 */
	private Exception initException = null;
	
	/**
	 *
	 */
	private Date initTime = null;
	
	/**
	 * 
	 */
	private String firstPage = "start";
	
	/**
	 * 
	 */
	private Map<String,Class<? extends Module>> modules = new TreeMap<String,Class<? extends Module>>();
	
	/**
	 * 
	 */
	private Map<String,Map<String,Method>> moduleActions = new TreeMap<String,Map<String,Method>>();
	
	/**
	 * 
	 */
	private Map<String,Map<String,String>> actionTemplates = new TreeMap<String,Map<String,String>>();
	
	/**
	 * 
	 */
	private Map<String,Transformer> transformers = new TreeMap<String,Transformer>();
	
	/**
	 * 
	 */
	private DocumentBuilder documentBuilder = null;

	
	/**
	 *
	 */
	private boolean myInit() {
		if (!isInitiated) {
			isInitiated = true;
			try {
				if (getInitParameter("first-page") != null) {
					firstPage = getInitParameter("first-page");
				}
				
				// First: create XML tools
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setNamespaceAware(true);
				documentBuilder = factory.newDocumentBuilder();
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				
				// Second: load Modules (as defined per web.xml)
				ClassLoader classLoader = getClass().getClassLoader();
				@SuppressWarnings("unchecked")
				Enumeration<String> initParams = getInitParameterNames();
				while (initParams.hasMoreElements()) {
					String initParamKey = initParams.nextElement();
					if (initParamKey.substring(0, 7).equals("module:")) {
						String initParamValue = getInitParameter(initParamKey);
						Class<?> clazz = classLoader.loadClass(initParamValue);
						
						// check loaded class
						Class<? extends Module> moduleClass = clazz.asSubclass(Module.class);

						String moduleName = null;
						if (moduleClass.isAnnotationPresent(ModuleName.class)) {
							moduleName = moduleClass.getAnnotation(ModuleName.class).value();
							if (!moduleName.equals(initParamKey.substring(7))) {
								throw new Exception("Module "+moduleName+" bad referenced in web.xml ("+initParamKey+")");
							}
						} else {
							throw new Exception("Module does not have a moduleName annotated.");
						}
						Method[] moduleMethods = moduleClass.getMethods();
						Map<String,Method> actions = new TreeMap<String,Method>();
						Map<String,String> templates = new TreeMap<String,String>();
						for (Method method : moduleMethods) {
							if (method.isAnnotationPresent(Action.class)) {
								Action annotation = method.getAnnotation(Action.class);
								loadStylesheet(transformerFactory, annotation.template());
								templates.put(annotation.name(), annotation.template());
								actions.put(annotation.name(), method);
							}
						}
						moduleActions.put(moduleName, actions);
						actionTemplates.put(moduleName, templates);
						modules.put(moduleName, moduleClass);
					}
				}
				if (!modules.containsKey(firstPage)) {
					throw new Exception("Module for firstPage does not exist ("+firstPage+")");
				}
			} catch (Exception e) {
				initException = e;
			}
			initTime = new Date();
		}
		if (initException != null) {
			return false;
		}
		return true;
	}
	
	private void loadStylesheet(TransformerFactory transformerFactory, String templateName) throws Exception {
		if (!transformers.containsKey(templateName)) {
			File templateFile = new File(getServletContext().getRealPath(templateName));
			if (!templateFile.exists()) {
				throw new Exception("Template does not exist: " + templateName);
			}
			Transformer transformer = transformerFactory.newTransformer(new StreamSource(templateFile));
			transformers.put(templateName, transformer);
		}
	}
	
	/**
	 *
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		if (!myInit()) {
			printInitError(response, initException);
		} else {
			try {
				String path = request.getPathInfo();
				if (path == null) {
					response.sendRedirect(request.getRequestURI() + '/');
				} else {
					String[] pathParts = path.split("/+", 4);
					
					if (pathParts.length == 2) {
						if (pathParts[1].trim().length() == 0) {
							response.sendRedirect(request.getRequestURI() + firstPage + '/');
						} else {
							// module, no action => module start page
							response.sendRedirect(request.getRequestURI() + '/');
						}
					} else if (pathParts.length == 3) {
						if (pathParts[2].trim().length() == 0) {
							callModule(request, response, pathParts[1].trim(), "", new String[0]);
						} else {
							// module with action
							callModule(request, response, pathParts[1].trim(), pathParts[2].trim(), new String[0]);
						}
					} else if (pathParts.length == 4) {
						if (pathParts[3].trim().length() == 0) {
							callModule(request, response, pathParts[1].trim(), pathParts[2].trim(), new String[0]);
						} else {
							// module with action and arguments
							callModule(request, response, pathParts[1].trim(), pathParts[2].trim(), pathParts[3].split("/+"));
						}
					} else {
						throw new Exception("Very unusual bug (shouldn’t even be possible to happen).");
					}
				}
			} catch (Exception e) {
				printError(response, e);
			}
		}
	}
	
	/**
	 * 
	 * @param response
	 * @param e
	 */
	private void printError(HttpServletResponse response, Throwable e)
	throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><head><title>...</title></head><body><h1>Hello Servlets.</h1>");
		out.println("<pre>");
		e.printStackTrace(out);
		out.println("</pre>");
		out.println("</body></html>");
	}

	/**
	 * 
	 * @param response
	 * @param e
	 */
	private void printInitError(HttpServletResponse response, Throwable e)
	throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><head><title>...</title></head><body><h1>Hello Servlets.</h1>");
		out.println("<pre>");
		e.printStackTrace(out);
		out.println("</pre>");
		out.println("</body></html>");
	}
	
	/**
	 *
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException {
    	doGet(request, response);
    }
    
    /**
     *
     */
    private void callModule(HttpServletRequest request, HttpServletResponse response,
                            final String moduleName, final String action, String[] args)
    throws Exception {
		if (!modules.containsKey(moduleName)) {
			throw new UnknownModuleException(moduleName);
		}
		if (!moduleActions.get(moduleName).containsKey(action)) {
			throw new UnknownActionException(action);
		}
		Module module = modules.get(moduleName).newInstance();
    	Document output = documentBuilder.newDocument();
    	module.init(this, new Session(request.getSession()), request, output);
    	try {
    		moduleActions.get(moduleName).get(action).invoke(module, (Object[]) args);
    	} catch (IllegalArgumentException e) {
    		throw new ModuleException(e);
    	}
    	Transformer transformer = transformers.get(actionTemplates.get(moduleName).get(action));
    	sendResponse(request, response, transformer, output.getDocumentElement());
    }
    
    /**
     * 
     * @param response
     * @param domNode
     */
    private void sendResponse(HttpServletRequest request, HttpServletResponse response, Transformer transformer, Node domNode) throws Exception {
    	Source source = new DOMSource(domNode);
    	Result result = new StreamResult(response.getWriter());
    	transformer.transform(source, result);
    }

	/**
	 * @return the initTime
	 */
	public Date getInitTime() {
		return initTime;
	}
	
	public Map<String,Class<? extends Module>> getModules() {
		return modules;
	}
}

