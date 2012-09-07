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

import de.fu.junction.Tuple;
import de.fu.junction.annotation.meta.Author;
import de.fu.junction.converter.StringConverter;
import de.fu.weave.orm.RelationManager;

/**
 * All data in weave is passed to the application via Forms.
 */
@Author("Julian Fleischer")
public interface Form extends Serializable {

    /**
     * An annotation to declare another member variable as Converter-Object for this value.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface ActiveConverter {
        /**
         * 
         * @return
         */
        String value();
    }

    /**
	 * 
	 */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface ActiveValidator {
        /**
         * 
         * @return
         */
        String value();
    }

    /**
	 * 
	 */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Alternatives {
        String value();
    }

    /**
	 * 
	 */
    public static enum Control {
        /**
		 * 
		 */
        ALTERNATIVES {
            @Override
            public String toString() {
                return "input-alternatives";
            }
        },
        /**
		 * 
		 */
        OPTIONS {
            @Override
            public String toString() {
                return "input-options";
            }
        },
        /**
		 * 
		 */
        DATE {
            @Override
            public String toString() {
                return "input-date";
            }
        },
        /**
		 * 
		 */
        CHECKBOX {
            @Override
            public String toString() {
                return "input-checkbox";
            }
        },
        /**
		 * 
		 */
        TEXTAREA {
            @Override
            public String toString() {
                return "input-textarea";
            }
        },
        /**
		 * 
		 */
        TEXT {
            @Override
            public String toString() {
                return "input-text";
            }
        },
        /**
		 * 
		 */
        NUMERIC {
            @Override
            public String toString() {
                return "input-numeric";
            }
        },
        /**
		 * 
		 */
        HIDDEN {
            @Override
            public String toString() {
                return "input-hidden";
            }
        },
        /**
		 * 
		 */
        PASSWORD {
            @Override
            public String toString() {
                return "input-password";
            }
        },
        /**
         * 
         */
        NONE {
            @Override
            public String toString() {
                return "none";
            }
        }
    }

    /**
	 * 
	 */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface ConverterClass {
        Class<? extends StringConverter<?>> value();
    }

    /**
 * 
 */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Field {
        String value() default "";
    }

    /**
	 * 
	 */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface FormControl {
        Form.Control value();
    }

    /**
	 * 
	 */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Match {
        String value();
    }

    /**
	 * 
	 */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Max {
        double value();
    }

    /**
	 * 
	 */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Min {
        double value();
    }

    /**
	 * 
	 */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Multiple {

    }

    /**
	 * 
	 */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Only {
        String value();
    }

    /**
	 * 
	 */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Pos {
        double value();
    }

    /**
	 * 
	 */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Repeat {
        int value() default 2;
    }

    /**
	 * 
	 */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Required {

    }

    /**
	 * 
	 */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Step {
        double value();
    }

    /**
	 * 
	 */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface ValidatorClass {
        Class<? extends de.fu.weave.Validator<?>> value();
    }

    /**
     * 
     * @param $field
     * @param $malformedValue
     * @param $exc
     */
    public void addMessage(String $field, String $malformedValue, Exception $exc);

    /**
	 * 
	 */
    public void clearMessages();

    /**
     * 
     * @return
     * @throws Exception
     */
    public boolean commit() throws Exception;

    /**
     * 
     * @param $fieldName
     */
    public void disable(String $fieldName);

    /**
     * 
     * @return
     */
    public Map<String,Tuple<String,Exception>> getMessages();

    /**
     * 
     * @return
     */
    public boolean hasMessages();

    /**
     * 
     * @param $fieldName
     */
    public void hide(String $fieldName);

    /**
	 * 
	 */
    public void init();

    /**
     * 
     * @param $fieldName
     * @return
     */
    public boolean isDisabled(String $fieldName);

    /**
     * 
     * @param $fieldName
     * @return
     */
    public boolean isHidden(String $fieldName);

    /**
     * 
     * @return
     */
    public boolean isSuccessfullyCommitted();

    /**
     * 
     * @return
     */
    public RelationManager manager();

    /**
	 * 
	 */
    public void reset();

    /**
     * 
     * @return
     */
    public Session session();

    /**
     * 
     * @param $manager
     */
    public void setRelationManager(RelationManager $manager);

    /**
     * 
     * @param $session
     */
    public void setSession(Session $session);

    /**
     * 
     * @param $user
     */
    public void setUser(User $user);

    /**
     * 
     * @return
     */
    public User user();
}
