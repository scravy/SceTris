/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.frigga;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import de.fu.weave.AnonymousUser;
import de.fu.weave.SuperUser;
import de.fu.weave.User;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.orm.RelationManager;

/**
 * 
 * @author Julian Fleischer
 * @author André Zoufahl
 * @since Iteration2
 */
public class SessionImpl implements de.fu.weave.Session {
	/**
	 * 
	 * @since Iteration2
	 */
	private final HttpSession httpSession;

	/**
	 * 
	 * @since Iteration2
	 */
	private final String stylesheet;

	/**
	 * 
	 * @since Iteration2
	 */
	private final String locale;

	/**
	 * 
	 * @since Iteration2
	 */
	private final String contentType;

	/**
	 * @since Iteration2
	 */
	private final RelationManager entityManager;

	/**
	 * @since Iteration2
	 */
	private User currentUser;

	/**
	 * Creates a new Session object
	 * 
	 * @since Iteration2
	 * @param request
	 */
	SessionImpl(final HttpServletRequest request, final RelationManager entityManager) {
		this.entityManager = entityManager;
		HttpSession theSession = request.getSession(false);
		if (theSession == null) {
			httpSession = request.getSession(true);
			httpSession.setAttribute("isLoggedIn", Boolean.FALSE);
			httpSession.setAttribute("currentUser", new AnonymousUser());
		} else {
			httpSession = theSession;
			User user = (User) httpSession.getAttribute("currentUser");
			Integer userId = (Integer) httpSession.getAttribute("userId");
			if (user == null) {
				if ((userId == null) || (userId < 0)) {
					user = new AnonymousUser();
					httpSession.setAttribute("userId", new Integer(-1));
					httpSession.setAttribute("isLoggedIn", Boolean.FALSE);
				}
				try {
					user = entityManager.fetchUser(userId);
					if (user != null) {
						httpSession.setAttribute("currentUser", user);
						httpSession.setAttribute("userId", new Integer(user.id()));
					} else {
						user = new AnonymousUser();
						httpSession.setAttribute("userId", new Integer(-1));
						httpSession.setAttribute("isLoggedIn", Boolean.FALSE);
					}
				} catch (DatabaseException e) {
					user = new AnonymousUser();
					httpSession.setAttribute("userId", new Integer(-1));
					httpSession.setAttribute("isLoggedIn", Boolean.FALSE);
				}
			}
			currentUser = user;
		}

		String stylesheet = "scetris-green.css";
		if (httpSession.getAttribute("stylesheet") != null) {
			stylesheet = (String) httpSession.getAttribute("stylesheet");
		}
		if (request.getParameter("~stylesheet") != null) {
			stylesheet = request.getParameter("~stylesheet");
		}
		httpSession.setAttribute("stylesheet", stylesheet);
		this.stylesheet = stylesheet;

		String locale = "en";
		if (httpSession.getAttribute("locale") != null) {
			locale = (String) httpSession.getAttribute("locale");
		}
		if (request.getParameter("~locale") != null) {
			locale = request.getParameter("~locale");
		}
		httpSession.setAttribute("locale", locale);
		this.locale = locale;

		String contentType = "html";
		if (httpSession.getAttribute("contentType") != null) {
			contentType = (String) httpSession.getAttribute("contentType");
		}
		if (request.getParameter("~type") != null) {
			contentType = request.getParameter("~type");
		}
		String[] visualContentTypes = { "html", "html5", "xhtml", "xsl" };
		if (Arrays.binarySearch(visualContentTypes, contentType) >= 0) {
			httpSession.setAttribute("contentType", contentType);
		}
		this.contentType = contentType;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	/**
	 * @return the entityManager
	 * @since Iteration2
	 */
	public RelationManager getEntityManager() {
		return entityManager;
	}

	@Override
	public String getLocale() {
		return locale;
	}

	@Override
	public String getStylesheet() {
		return stylesheet;
	}

	@Override
	public User getUser() {
		return currentUser;
	}

	@Override
	public boolean isUserLoggedIn() {
		Boolean loggedIn = (Boolean) httpSession.getAttribute("isLoggedIn");
		if (loggedIn == null) {
			return false;
		}
		return loggedIn;
	}

	/**
	 * setUser will be called by LoginHandler and LogoutHandler. setUser(null) shall set isLoggedIn
	 * to false whereas every other user shall set isLoggedIn to true. If a user is newly logged in
	 * his data has to be fetched form the database, or the database has to be passed to the newly
	 * created User objects so that it can construct itself. Implementation detail. You might use
	 * EntityManager.select(...) for this purpose.
	 */
	@Override
	public void setUser(final User user) {

		if (user == null) {
			currentUser = new AnonymousUser();
			httpSession.setAttribute("isLoggedIn", false);
		} else {
			if (user.isSuperUser()) {
				currentUser = new SuperUser(user);
			} else {
				currentUser = user;
			}
			httpSession.setAttribute("isLoggedIn", true);
		}
		httpSession.setAttribute("currentUser", currentUser);
	}
}
