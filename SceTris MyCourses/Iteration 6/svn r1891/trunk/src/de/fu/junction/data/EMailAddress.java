/* EMailAddress.java / 11:21:42 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.data;

import de.fu.weave.annotation.meta.Author;

/**
 * Representation of an email address (like {@see java.net.URL} for URLs).
 */
@Author("Julian Fleischer")
public class EMailAddress {
	final public static String MAILBOX_REGEX = "^([a-zA-Z0-9\\!\\#\\$\\%\\&\'\\*\\+\\-\\/\\=\\?\\^_`\\{\\|\\}~\\.]+|\"[a-zA-Z0-9\\!\\#\\$\\%\\&\'\\*\\+\\-\\/\\=\\?\\^_`\\{\\|\\}~\\.\\(\\)\\<\\>\\[\\]\\:;@\\\\,]+\")$";
	final public static String HOSTNAME_REGEX = "^([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]{2,}$";

	/**
	 * {@see #getHostname()}
	 */
	final private String $mailbox;

	/**
	 * {@see #getMailbox()}
	 */
	final private String $hostname;

	/**
	 * Constructs an EMailAddress by parsing the given string.
	 * <p>
	 * A lot of information regarding valid emails addresses can be obtained form the site at
	 * {@link http ://www.dominicsayers.com/isemail/}.
	 * 
	 * @param $eMailAddress
	 *            A String representing an email address
	 * @throws MalformedEMailAddressException
	 *             If the String does not represent a valid email address
	 */
	public EMailAddress(String $eMailAddress) throws InvalidSyntaxException {
		$eMailAddress = $eMailAddress.trim();
		int $at = $eMailAddress.lastIndexOf('@');
		if ($at < 0) {
			throw new MalformedEMailAddressException("Must contain an at");
		}
		if ($eMailAddress.length() > 254) {
			throw new MalformedEMailAddressException("email address is too long");
		}

		$hostname = $eMailAddress.substring($at + 1, $eMailAddress.length());
		if (!$hostname.matches(HOSTNAME_REGEX)) {
			throw new MalformedHostnameException("Invalid hostname");
		}

		$mailbox = $eMailAddress.substring(0, $at);
		if (!$mailbox.matches(MAILBOX_REGEX)) {
			throw new MalformedMailboxPartException("Invalid mailbox part");
		}
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		EMailAddress other = (EMailAddress) obj;
		if ($hostname == null) {
			if (other.$hostname != null) {
				return false;
			}
		} else if (!$hostname.equals(other.$hostname)) {
			return false;
		}
		if ($mailbox == null) {
			if (other.$mailbox != null) {
				return false;
			}
		} else if (!$mailbox.equals(other.$mailbox)) {
			return false;
		}
		return true;
	}

	/**
	 * Returns the hostname portion of this email address (part after @)
	 * 
	 * @return The hostname portion of this email address
	 */
	public String getHostname() {
		return $hostname;
	}

	/**
	 * Returns the mailbox portion of this email-address (part before @)
	 * 
	 * @return The mailbox portion of this email address
	 */
	public String getMailbox() {
		return $mailbox;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (($hostname == null) ? 0 : $hostname.hashCode());
		result = prime * result + (($mailbox == null) ? 0 : $mailbox.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return $mailbox + '@' + $hostname;
	}
}
