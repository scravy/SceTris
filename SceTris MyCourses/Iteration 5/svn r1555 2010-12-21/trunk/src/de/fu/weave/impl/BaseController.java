/* GenericController.java / 8:29:56 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import de.fu.weave.ControllerInstantiationException;
import de.fu.weave.RequestMethod;
import de.fu.weave.TemplateManager;
import de.fu.weave.xml.XmlHelper;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public abstract class BaseController extends HttpServlet {

	/**
	 * 
	 * @since Iteration3
	 */
	private static final long serialVersionUID = -8628824711873177862L;

	/**
	 * XXX: The whole of this method is dirty hackery. I really dont know whats up with those
	 * Parameters that they return unparametrized types and why GET-Parameters are fucked up the way
	 * they are. However, this method takes care of them. We need to check how other
	 * ServletContainer handle this (Glassfish e.g.)
	 * 
	 * @param request
	 * @param method
	 * @return
	 * @since Iteration3
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String[]> getRequestParameters(final HttpServletRequest request,
															final RequestMethod method) {
		if (method == RequestMethod.POST) {
			return request.getParameterMap();
		} else {
			// handle GET specially, using request.getQueryString and URLDecoder.decode(, UTF-8)...
			Map<String,String[]> map = request.getParameterMap();
			try {
				for (String key : map.keySet()) {
					String[] values = map.get(key);
					for (int i = 0; i < values.length; i++) {
						values[i] =
								new String(values[i].getBytes(Charset.forName("ISO-8859-1")),
										Charset.forName("UTF-8"));
					}
				}
			} catch (RuntimeException e) {
			}
			return map;
		}
	}

	/**
	 * {@link #getTemplateManager()}
	 * 
	 * @since Iteration2
	 */
	final protected TemplateManager templateManager;

	/**
	 * An XML-Helper (providing methods for XSL-Transformations and DOM-Manipulation)
	 * 
	 * @since Iteration2
	 */
	final protected XmlHelper xmlHelper;

	/**
	 * 
	 * @throws ParserConfigurationException
	 * @since Iteration3
	 */
	public BaseController() throws ControllerInstantiationException {
		super();
		try {
			xmlHelper = new XmlHelper();
			templateManager = new TemplateManager(xmlHelper);
		} catch (Exception e) {
			throw new ControllerInstantiationException(e);
		}
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
	 */
	abstract protected void answerRequest(final HttpServletRequest request,
										  final HttpServletResponse response, final RequestMethod method)
			throws IOException, ServletException;

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
	final public void doGet(final HttpServletRequest request, final HttpServletResponse response)
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
	final public void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws java.io.IOException, javax.servlet.ServletException {
		answerRequest(request, response, RequestMethod.POST);
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

	public XmlHelper getXmlHelper() {
		return xmlHelper;
	}

}
