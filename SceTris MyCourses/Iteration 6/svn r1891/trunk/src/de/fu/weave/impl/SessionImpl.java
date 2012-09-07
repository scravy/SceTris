/* SessionImpl.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.bakery.orm.java.RelationManager;
import de.fu.weave.AnonymousUser;
import de.fu.weave.Session;
import de.fu.weave.SessionConfig;
import de.fu.weave.SuperUser;
import de.fu.weave.User;

/**
 * 
 * @author Julian Fleischer
 * @author André Zoufahl
 * @since Iteration2
 */
public class SessionImpl implements Session {
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
	private final String lang;

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
	 * @param $request
	 */
	public SessionImpl(final HttpServletRequest $request, final RelationManager $entityManager) {
		SessionConfig config = new SessionConfig();
		entityManager = $entityManager;
		HttpSession theSession = $request.getSession(false);
		User user = null;
		if (theSession == null) {
			httpSession = $request.getSession(true);
			httpSession.setAttribute("isLoggedIn", Boolean.FALSE);
			httpSession.setAttribute("currentUser", user = new AnonymousUser());
		} else {
			httpSession = theSession;
			user = (User) httpSession.getAttribute("currentUser");
			Integer userId = (Integer) httpSession.getAttribute("userId");
			if (user == null) {
				if ((userId == null) || (userId < 0)) {
					user = new AnonymousUser();
					userId = user.id();
					httpSession.setAttribute("userId", userId);
					httpSession.setAttribute("isLoggedIn", Boolean.FALSE);
				}
				try {
					user = $entityManager.fetchUser(userId);
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

		String stylesheet = config.getDefaultStylesheet();
		if (httpSession.getAttribute("stylesheet") != null) {
			stylesheet = (String) httpSession.getAttribute("stylesheet");
		}
		if ($request.getParameter("~stylesheet") != null) {
			stylesheet = $request.getParameter("~stylesheet");
		}
		httpSession.setAttribute("stylesheet", stylesheet);
		this.stylesheet = stylesheet;

		String locale = config.getDefaultLanguage();
		if (httpSession.getAttribute("lang") != null) {
			locale = (String) httpSession.getAttribute("lang");
		} else {
			locale = $request.getLocale().getLanguage();
		}
		if ($request.getParameter("~lang") != null) {
			locale = $request.getParameter("~lang");
		}
		httpSession.setAttribute("lang", locale);
		lang = locale;

		String contentType = "html";
		if (httpSession.getAttribute("contentType") != null) {
			contentType = (String) httpSession.getAttribute("contentType");
		}
		if ($request.getParameter("~type") != null) {
			contentType = $request.getParameter("~type");
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
	 */
	public RelationManager getEntityManager() {
		return entityManager;
	}

	@Override
	public String getLanguage() {
		return lang;
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
	public Object getValue(final String name) {
		return httpSession.getAttribute(name);
	}

	@Override
	public boolean getValue(final String name, final boolean fallback) {
		Object value = getValue(name);
		if (value == null) {
			return fallback;
		}
		return (Boolean) value;
	}

	@Override
	public char getValue(final String name, final char fallback) {
		Object value = getValue(name);
		if (value == null) {
			return fallback;
		}
		return (Character) value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue(final String name, final Class<T> type) {
		return (T) getValue(name);
	}

	@Override
	public double getValue(final String name, final double fallback) {
		Object value = getValue(name);
		if (value == null) {
			return fallback;
		}
		return (Double) value;
	}

	@Override
	public float getValue(final String name, final float fallback) {
		Object value = getValue(name);
		if (value == null) {
			return fallback;
		}
		return (Float) value;
	}

	@Override
	public int getValue(final String name, final int fallback) {
		Object value = getValue(name);
		if (value == null) {
			return fallback;
		}
		return (Integer) value;
	}

	@Override
	public long getValue(final String name, final long fallback) {
		Object value = getValue(name);
		if (value == null) {
			return fallback;
		}
		return (Long) value;
	}

	@Override
	public String getValue(final String name, final String fallback) {
		Object value = getValue(name);
		if (value == null) {
			return fallback;
		}
		return value.toString();
	}

	@Override
	public boolean isUserLoggedIn() {
		Boolean loggedIn = (Boolean) httpSession.getAttribute("isLoggedIn");
		if (loggedIn == null) {
			return false;
		}
		return loggedIn;
	}

	@Override
	public void setUser(final User $user) {

		if ($user == null) {
			currentUser = new AnonymousUser();
			httpSession.setAttribute("isLoggedIn", false);
		} else {
			if ($user.isSuperUser()) {
				currentUser = new SuperUser($user);
			} else {
				currentUser = $user;
			}
			httpSession.setAttribute("isLoggedIn", true);
		}
		httpSession.setAttribute("currentUser", currentUser);
	}

	@Override
	public void setValue(final String $name, final Object $value) {
		httpSession.setAttribute($name, $value);
	}
}
