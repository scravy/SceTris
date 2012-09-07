/* Form.java / 2:23:40 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import de.fu.junction.converter.StringConverter;

public interface Form extends Serializable {

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface ActiveValidator {
		String value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Alternatives {
		String value();
	}

	public static enum Control {
		ALTERNATIVES {
			@Override
			public String toString() {
				return "input-alternatives";
			}
		},
		DATE {
			@Override
			public String toString() {
				return "input-date";
			}
		},
		CHECKBOX {
			@Override
			public String toString() {
				return "input-checkbox";
			}
		},
		TEXTAREA {
			@Override
			public String toString() {
				return "input-textarea";
			}
		},
		TEXT {
			@Override
			public String toString() {
				return "input-text";
			}
		},
		NUMERIC {
			@Override
			public String toString() {
				return "input-numeric";
			}
		},
		HIDDEN {
			@Override
			public String toString() {
				return "input-hidden";
			}
		},
		PASSWORD {
			@Override
			public String toString() {
				return "input-password";
			}
		}
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Converter {
		Class<? extends StringConverter<?>> value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Field {
		String value() default "";
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface FormControl {
		Form.Control value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Match {
		String value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Max {
		double value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Min {
		double value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Only {
		String value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Pos {
		double value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Repeat {
		int value() default 2;
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Required {

	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Step {
		double value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Validator {
		Class<? extends de.fu.weave.Validator> value();
	}

	public void addMessage(String $field, Exception $exc);

	public void clearMessages();

	public void commit() throws Exception;

	public Map<String,Exception> getMessages();

	public boolean hasMessages();
}
