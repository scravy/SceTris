/* SessionImpl.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.frigg;


import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import de.fu.junction.annotation.meta.Author;
import de.fu.weave.AnonymousUser;
import de.fu.weave.Session;
import de.fu.weave.SessionConfig;
import de.fu.weave.User;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.orm.RelationManager;

/**
 * The frigg Session implementation is a wrapper around {@see HttpSession} that additionally manages
 * login and user authentication.
 */
@Author({ "Julian Fleischer", "André Zoufahl" })
public class FriggSession implements Session {
    /**
	 * 
	 */
    private final HttpSession $httpSession;

    /**
     * 
     */
    private final String $stylesheet;

    /**
     * 
     */
    private final String $lang;

    /**
     * 
     */
    private final String $contentType;

    /**
     * The relation manager associated with the current request
     */
    private final RelationManager $entityManager;

    /**
     * The object representing the current user.
     */
    private User $currentUser;

    /**
     * The remote address (ip address) of the current session
     */
    private final String $remoteAddress;

    /**
     * The remote host (the hostname that is associated with this IP address) of the current session
     */
    private final String $remoteHost;

    /**
     * Creates a new Session object
     * 
     * @param $request
     */
    public FriggSession(final HttpServletRequest $request, final RelationManager $entityManager) {
        SessionConfig $config = new SessionConfig();
        this.$entityManager = $entityManager;
        $remoteAddress = $request.getRemoteAddr();
        $remoteHost = $request.getRemoteHost();
        HttpSession $theSession = $request.getSession(false);
        User $user = null;
        if ($theSession == null) {
            $httpSession = $request.getSession(true);
            $httpSession.setAttribute("isLoggedIn", Boolean.FALSE);
            $user = new AnonymousUser();
            $httpSession.setAttribute("currentUser", $user);
        } else {
            $httpSession = $theSession;
            $user = (User) $httpSession.getAttribute("currentUser");
            Integer $userId = (Integer) $httpSession.getAttribute("userId");
            if ($user == null) {
                if (($userId == null) || ($userId < 0)) {
                    $user = new AnonymousUser();
                    $userId = $user.id();
                    $httpSession.setAttribute("userId", $userId);
                    $httpSession.setAttribute("isLoggedIn", Boolean.FALSE);
                }
                try {
                    $user = $entityManager.fetchUser($userId);
                    if ($user != null) {
                        $httpSession.setAttribute("currentUser", $user);
                        $httpSession.setAttribute("userId", new Integer($user.id()));
                    } else {
                        $user = new AnonymousUser();
                        $httpSession.setAttribute("userId", new Integer(-1));
                        $httpSession.setAttribute("isLoggedIn", Boolean.FALSE);
                    }
                } catch (DatabaseException e) {
                    $user = new AnonymousUser();
                    $httpSession.setAttribute("userId", new Integer(-1));
                    $httpSession.setAttribute("isLoggedIn", Boolean.FALSE);
                } finally {
                    if ($user == null) {
                        $user = new AnonymousUser();
                    }
                }
            }
            $currentUser = $user;
        }

        String $stylesheet = $config.getDefaultStylesheet();
        if ($httpSession.getAttribute("stylesheet") != null) {
            $stylesheet = (String) $httpSession.getAttribute("stylesheet");
        }
        if ($request.getParameter("~stylesheet") != null) {
            $stylesheet = $request.getParameter("~stylesheet");
        }
        $httpSession.setAttribute("stylesheet", $stylesheet);
        this.$stylesheet = $stylesheet;

        String locale = $config.getDefaultLanguage();
        if ($httpSession.getAttribute("lang") != null) {
            locale = (String) $httpSession.getAttribute("lang");
        } else {
            locale = $request.getLocale().getLanguage();
        }
        if ($request.getParameter("~lang") != null) {
            locale = $request.getParameter("~lang");
        }
        $httpSession.setAttribute("lang", locale);
        $lang = locale;

        String contentType = "html";
        if ($httpSession.getAttribute("contentType") != null) {
            contentType = (String) $httpSession.getAttribute("contentType");
        }
        if ($request.getParameter("~type") != null) {
            contentType = $request.getParameter("~type");
        }
        String[] visualContentTypes = { "html", "html5", "xhtml", "xsl" };
        if (Arrays.binarySearch(visualContentTypes, contentType) >= 0) {
            $httpSession.setAttribute("contentType", contentType);
        }
        $contentType = contentType;
    }

    @Override
    public String getContentType() {
        return $contentType;
    }

    /**
     * @return the entityManager
     */
    public RelationManager getEntityManager() {
        return $entityManager;
    }

    @Override
    public String getLanguage() {
        return $lang;
    }

    @Override
    public String getRemoteAddress() {
        return $remoteAddress;
    }

    @Override
    public String getRemoteHost() {
        return $remoteHost;
    }

    @Override
    public String getStylesheet() {
        return $stylesheet;
    }

    @Override
    public User getUser() {
        return $currentUser;
    }

    @Override
    public Object getValue(final String name) {
        return $httpSession.getAttribute(name);
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
        Boolean loggedIn = (Boolean) $httpSession.getAttribute("isLoggedIn");
        if (loggedIn == null) {
            return false;
        }
        return loggedIn;
    }

    @Override
    public void setUser(final User $user) {

        if ($user == null) {
            $currentUser = new AnonymousUser();
            $httpSession.setAttribute("isLoggedIn", false);
        } else {
            $currentUser = $user;
            $httpSession.setAttribute("isLoggedIn", true);
        }

        $httpSession.setAttribute("currentUser", $currentUser);
        $httpSession.setAttribute("userId", $currentUser.id());
    }

    @Override
    public void setValue(final String $name, final Object $value) {
        $httpSession.setAttribute($name, $value);
    }
}
