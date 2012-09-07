package de.fu.weave.reflect;


import static de.fu.weave.xml.Namespaces.*;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.fu.junction.NumberComparator;
import de.fu.junction.Sets;
import de.fu.junction.Tuple;
import de.fu.junction.Types;
import de.fu.junction.annotation.meta.Author;
import de.fu.junction.converter.StringConverter;
import de.fu.junction.converter.StringConverterFactory;
import de.fu.junction.data.InvalidSyntaxException;
import de.fu.junction.data.Password;
import de.fu.weave.Form;
import de.fu.weave.Form.ActiveConverter;
import de.fu.weave.Form.ActiveValidator;
import de.fu.weave.Form.Alternatives;
import de.fu.weave.Form.Control;
import de.fu.weave.Form.ConverterClass;
import de.fu.weave.Form.FormControl;
import de.fu.weave.Form.Match;
import de.fu.weave.Form.Max;
import de.fu.weave.Form.Min;
import de.fu.weave.Form.Multiple;
import de.fu.weave.Form.Only;
import de.fu.weave.Form.Pos;
import de.fu.weave.Form.Required;
import de.fu.weave.Form.Step;
import de.fu.weave.Form.ValidatorClass;
import de.fu.weave.ParamReflector;
import de.fu.weave.Validator;

/**
 * 
 * 
 * @param <F>
 *            The Form that this Reflector reflects
 */
@Author("Julian Fleischer")
public class FormReflector<F extends Form> implements ParamReflector {

    /**
     * Holds information about a form field.
     */
    public class FieldReflector {
        final public Double $min;
        final public Double $max;
        final public Double $step;
        final public String $match;
        final public String $only;
        final public Double $pos;
        final public StringConverter<?> $converter;
        final public Field $activeConverter;
        final public Control $control;
        final public Field $alternatives;
        final boolean $isRequired;
        final boolean $isMultiple;
        final public Class<? extends de.fu.weave.Validator<?>> $validatorClass;
        final public Field $activeValidator;

        final public Field $field;
        final public Class<?> $type;
        final public String $name;

        FieldReflector(final Field $field) throws FormReflectorException {
            Double $tmpStep;
            String $name = $field.getAnnotation(Form.Field.class).value();
            if ($name.equals("")) {
                $name = $field.getName();
            }
            this.$name = $name;
            this.$field = $field;
            $type = $field.getType();

            $min = $field.isAnnotationPresent(Min.class)
                    ? $field.getAnnotation(Min.class).value()
                    : null;
            $max = $field.isAnnotationPresent(Max.class)
                    ? $field.getAnnotation(Max.class).value()
                    : null;
            $match = $field.isAnnotationPresent(Match.class)
                    ? $field.getAnnotation(Match.class).value()
                    : null;
            $only = $field.isAnnotationPresent(Only.class)
                    ? $field.getAnnotation(Only.class).value()
                    : null;
            $tmpStep = $field.isAnnotationPresent(Step.class)
                    ? $field.getAnnotation(Step.class).value()
                    : null;
            $pos = $field.isAnnotationPresent(Pos.class)
                    ? $field.getAnnotation(Pos.class).value()
                    : null;
            $isRequired = $field.isAnnotationPresent(Required.class);
            $isMultiple = $field.isAnnotationPresent(Multiple.class) && $type.isArray();

            if ($field.isAnnotationPresent(ConverterClass.class)) {
                Class<? extends StringConverter<?>> $converterClass;
                Constructor<? extends StringConverter<?>> $constructor;

                StringConverter<?> $c;
                $converterClass = $field.getAnnotation(ConverterClass.class).value();
                try {
                    $constructor = $converterClass.getConstructor();
                    $c = $constructor.newInstance();
                } catch (Exception $e1) {
                    try {
                        $constructor = $converterClass.getConstructor(Class.class);
                        $c = $constructor.newInstance($type);
                    } catch (Exception $e2) {
                        throw new FormReflectorException();
                    }
                }
                $converter = $c;
            } else {
                try {
                    $converter = $converterFactory.newConverter(
                            $type.isArray()
                                    ? $type.getComponentType()
                                    : $type);
                } catch (RuntimeException $exc) {
                    throw new FormReflectorException($exc.getCause());
                }
            }
            Field $tmpActiveConverter = null;
            if ($field.isAnnotationPresent(ActiveConverter.class)) {
                try {
                    $tmpActiveConverter = $formType
                            .getField($field.getAnnotation(ActiveConverter.class).value());
                } catch (NoSuchFieldException $exc) {

                }
            }
            $activeConverter = $tmpActiveConverter;
            if ($field.isAnnotationPresent(Alternatives.class)) {
                try {
                    String $altField = $field.getAnnotation(Alternatives.class).value();
                    $alternatives = $field.getDeclaringClass().getField($altField);
                } catch (NoSuchFieldException $exc) {
                    throw new FormReflectorException($exc);
                }
            } else {
                $alternatives = null;
            }
            if ($field.isAnnotationPresent(FormControl.class)) {
                $control = $field.getAnnotation(FormControl.class).value();
            } else {
                if ($alternatives != null) {
                    if ($isMultiple) {
                        $control = Control.OPTIONS;
                    } else {
                        $control = Control.ALTERNATIVES;
                    }
                } else if (Types.isString($type)) {
                    $control = Control.TEXT;
                } else if (Types.isNumber($type)) {
                    $control = Control.NUMERIC;
                } else if (Types.isBoolean($type)) {
                    $control = Control.CHECKBOX;
                } else if (Date.class.isAssignableFrom($type)) {
                    $control = Control.DATE;
                } else if (Password.class.isAssignableFrom($type)) {
                    $control = Control.PASSWORD;
                } else {
                    $control = Control.TEXT;
                }
            }
            if (Types.isNumber($type)) {
                if ($tmpStep == null) {
                    $step = Types.isIntegral($type) ? 1 : 0.1;
                } else {
                    $step = Types.isIntegral($type) ? (double) $tmpStep.intValue() : $tmpStep;
                }
            } else {
                $step = null;
            }
            Field $tmpActiveValidator = null;
            Class<? extends de.fu.weave.Validator<?>> $tmpValidatorClass = null;
            if ($field.isAnnotationPresent(ValidatorClass.class)) {
                $tmpValidatorClass = $field.getAnnotation(ValidatorClass.class).value();
            }
            if ($field.isAnnotationPresent(ActiveValidator.class)) {
                try {
                    $tmpActiveValidator = $formType
                            .getField($field.getAnnotation(ActiveValidator.class).value());
                } catch (NoSuchFieldException $exc) {

                }
            }
            $activeValidator = $tmpActiveValidator;
            $validatorClass = $tmpValidatorClass;
        }

        /**
         * @return
         */
        public Object getAlternatives(final Form $form) {
            try {
                return $alternatives.get($form);
            } catch (Exception $exc) {
                throw new RuntimeException($exc);
            }
        }

        public Object getValue(final Form $form) {
            try {
                return $field.get($form);
            } catch (Exception $exc) {
                throw new RuntimeException($exc);
            }
        }

        public boolean hasAlternatives() {
            return $alternatives != null;
        }

        public boolean hasValue(final Form $form) {
            try {
                return $field.get($form) != null;
            } catch (Exception $exc) {
                throw new RuntimeException($exc);
            }
        }

        public void setValue(final Form $form, final Object $value) {
            try {
                if (($value != null) || ($field.get($form) == null)) {
                    $field.set($form, $value);
                }
            } catch (Exception $exc) {
                throw new RuntimeException($exc);
            }
        }
    }

    /**
     * {@see #FormReflector(Field)}
     */
    final Class<F> $formType;

    /**
	 * 
	 */
    final public Map<String,FieldReflector> $formFields = new TreeMap<String,FieldReflector>();

    final public static StringConverterFactory $converterFactory = new StringConverterFactory();

    public static <F extends Form> FormReflector<F> newInstance(final Class<F> $class)
            throws FormReflectorException {
        return new FormReflector<F>($class);
    }

    /**
     * Holds information about a form.
     * 
     * @param $formType
     */
    public FormReflector(final Class<F> $formType) throws FormReflectorException {
        this.$formType = $formType;
        Field[] $fields = $formType.getFields();
        Set<String> $fieldNames = new HashSet<String>();

        for (Field $field : $fields) {
            $fieldNames.add($field.getName());
        }
        for (String $fieldName : $fieldNames) {
            try {
                Field $field = $formType.getField($fieldName);
                if ($field.isAnnotationPresent(Form.Field.class)) {
                    FieldReflector $reflector = new FieldReflector($field);
                    $formFields.put($reflector.$name, $reflector);
                }
            } catch (SecurityException e) {} catch (NoSuchFieldException e) {}

        }
    }

    /**
     * @param $fieldName
     * @return
     */
    public Object getField(final F $form, final String $fieldName) {
        return $formFields.get($fieldName).getValue($form);
    }

    /**
     * Retrieve the names of the form fields of the reflected form
     * 
     * @return An array containing the names of the form fields of the reflected form
     */
    public String[] getFieldNames() {
        return $formFields.keySet().toArray(new String[0]);
    }

    /**
     * Returns a new form object of this reflectors form type.
     * 
     * @return A new form object.
     */
    public F newInstance() {
        try {
            return $formType.newInstance();
        } catch (Exception $e) {
            throw new RuntimeException($e);
        }
    }

    /**
     * 
     * @param $form
     * @param $name
     * @param $value
     * @return false if there was nothing to convert
     * @throws InvalidSyntaxException
     *             If the value could not be converted to the type of the field
     */
    @SuppressWarnings("unchecked")
    public <C extends StringConverter<?>> boolean setField(final F $form, final String $name,
        final Object $value)
        throws InvalidSyntaxException, RequiredFieldMissingException {
        FieldReflector $formField = $formFields.get($name);
        Object $realValue = $value;
        if ($formField == null) {
            // the form field does not exist
            throw new RuntimeException(new NoSuchFieldException($name));
        }
        if ($value == null) {
            if (!Types.isPrimitive($formField.$type)) {
                // it's not primitive, so we can null it.
                $formField.setValue($form, null);
            }
            // primitive values can not be nulled.
            return false;
        }
        C $converter = (C) $formField.$converter;
        if (($formField.$activeConverter != null)) {
            try {
                $converter = (C) $formField.$activeConverter.get($form);
            } catch (Exception $exc) {

            }
        }

        if (Types.isArray($value)) {
            Object[] $arrayValue = (Object[]) $value;
            if ($formField.$isMultiple) {
                List<Object> $values = new ArrayList<Object>($arrayValue.length);
                for (Object $o : $arrayValue) {
                    String $stringValue = $o.toString();
                    Object $parsedValue = $converter.convert($stringValue);
                    if ($parsedValue != null) {
                        $values.add($parsedValue);
                    }
                }
                Object $valuesArray = Array.newInstance($formField.$type.getComponentType(),
                    $values.size());
                for (int $i = 0; $i < $values.size(); $i++) {
                    Array.set($valuesArray, $i, $values.get($i));
                }
                $formField.setValue($form, $valuesArray);
                return true;
            }
            if ($arrayValue.length < 1) {
                if ($formField.$isRequired) {
                    throw new RequiredFieldMissingException();
                }
                return false;
            }
            $realValue = $arrayValue[0];
        }
        if ($realValue instanceof String) {
            String $stringValue = (String) $realValue;
            if ($stringValue.length() == 0) {
                if ($formField.$isRequired) {
                    throw new RequiredFieldMissingException();
                } else {
                    return false;
                }
            }
            Object $parsedValue;

            $parsedValue = $converter.convert($stringValue);
            if ($parsedValue == null) {
                // the value could not be converted, which means that an incorrect value was
                // given
                throw new InvalidSyntaxException((String) $realValue);
            }
            $formField.setValue($form, $parsedValue);
            return true;
        }
        return false;
    }

    /**
     * Append the Form to an XML element.
     * 
     * @param $form
     *            The Form to append.
     * @param $e
     *            The element that the Form will be appended to.
     */
    public void toXML(final F $form, final Element $e) {
        $form.init();

        Document $d = $e.getOwnerDocument();

        $e.setAttribute("formType", $form.getClass().getCanonicalName());
        Map<String,Tuple<String,Exception>> $messages = $form.getMessages();

        if ($messages.containsKey("")) {
            Exception $exc = $messages.get("").snd;
            if ($exc == null) {
                $e.appendChild($d.createElementNS(XMLNS_FORM, "successfullyCommitted"));
            } else {
                Element $err = (Element) $e.appendChild($d.createElementNS(XMLNS_FORM, "error"));
                Throwable $cause = $exc;
                do {
                    for (StackTraceElement $t : $cause.getStackTrace()) {
                        $err.setAttribute("type", $cause.getClass().getSimpleName());
                        $err.setAttribute("message", $cause.getMessage());
                        Element $trace = (Element) $err.appendChild($d.createElementNS(XMLNS_FORM,
                            "trace"));
                        $trace.setAttribute("class", $t.getClassName());
                        $trace.setAttribute("method", $t.getMethodName());
                        $trace.setAttribute("file", $t.getFileName());
                        $trace.setAttribute("line", Integer.toString($t.getLineNumber()));
                    }
                    if ($cause.getCause() != null) {
                        $err = (Element) $err.appendChild($d.createElementNS(XMLNS_FORM, "cause"));
                    }
                } while (($cause = $cause.getCause()) != null);
            }
        }

        FieldReflector $reflector;
        for (String $fieldName : $formFields.keySet()) {
            if ($form.isHidden($fieldName)) {
                continue;
            }
            $reflector = $formFields.get($fieldName);
            Element $f = $d.createElementNS(XMLNS_FORM, $reflector.$control.toString());
            $f.setAttribute("name", $fieldName);
            if ($reflector.$min != null) {
                Double $min = $reflector.$min;
                $f.setAttribute("min",
                                Types.isNatural($min)
                                        ? Long.toString($min.longValue())
                                        : $min.toString());
            }
            if ($reflector.$max != null) {
                Double $max = $reflector.$max;
                $f.setAttribute("max",
                                Types.isNatural($max)
                                        ? Long.toString($max.longValue())
                                        : $max.toString());
            }
            if ($reflector.$step != null) {
                Double $step = $reflector.$step;
                $f.setAttribute("step",
                                Types.isNatural($step)
                                        ? Long.toString($step.longValue())
                                        : $step.toString());
            }
            if ($reflector.$only != null) {
                $f.setAttribute("only", $reflector.$only.toString());
            }
            if ($reflector.$pos != null) {
                $f.setAttribute("pos", $reflector.$pos.toString());
            }
            $f.setAttribute("disabled", $form.isDisabled($fieldName) ? "yes" : "no");
            $f.setAttribute("required", $reflector.$isRequired ? "yes" : "no");
            if ($messages.containsKey($fieldName)) {
                Exception $message = $messages.get($fieldName).snd;
                $f.setAttribute("value", $messages.get($fieldName).fst);
                $f.setAttribute("error-type", $message.getClass().getSimpleName());
                $f.setAttribute("error-code", $message.getMessage());
            }
            if ($reflector.hasAlternatives()) {
                Object $alternatives = $reflector.getAlternatives($form);
                Set<Object> $selected = Sets.makeSet($reflector.getValue($form));
                if ($selected == null) {
                    $selected = new TreeSet<Object>();
                }
                if ($alternatives instanceof Map) {
                    Map<?,?> $options = (Map<?,?>) $alternatives;
                    for (Entry<?,?> $en : $options.entrySet()) {
                        Element $n = $d.createElementNS(XMLNS_FORM, "o");
                        $n.setAttribute("k", $en.getKey().toString());
                        $n.setAttribute("v", $en.getValue().toString());
                        $n.setAttribute("s", $selected.contains($en.getKey()) ? "1" : "0");
                        $f.appendChild($n);
                    }
                } else if (($alternatives instanceof Iterable<?>)) {
                    Iterable<?> $options = (Iterable<?>) $alternatives;
                    for (Object $o : $options) {
                        Element $n = $d.createElementNS(XMLNS_FORM, "o");
                        String $value;
                        $n.setAttribute("v", $value = $o.toString());
                        $n.setAttribute("s", $selected.contains($value) ? "1" : "0");
                        $f.appendChild($n);
                    }
                } else if (Types.isArray($alternatives)) {
                    Object[] $options = (Object[]) $alternatives;
                    for (Object $o : $options) {
                        Element $n = $d.createElementNS(XMLNS_FORM, "o");
                        String $value;
                        $n.setAttribute("v", $value = $o.toString());
                        $n.setAttribute("s", $selected.contains($value) ? "1" : "0");
                        $f.appendChild($n);

                    }
                }
            }
            if ($reflector.hasValue($form) && !$reflector.$isMultiple) {
                $f.setAttribute("value", $reflector.getValue($form).toString());
            }
            $e.appendChild($f);
        }
    }

    /**
     * @param $form
     * @param $requestParams
     */
    @SuppressWarnings("unchecked")
    public <V extends Validator<?>> void validate(final Form $form, final String $fieldName)
        throws Exception {
        FieldReflector $reflector = $formFields.get($fieldName);
        Object $value = $reflector.getValue($form);

        if (String.class.isAssignableFrom($reflector.$type)) {
            String $realValue = (String) $value;
            if (($reflector.$only != null)) {
                for (int $i = 0; $i < $realValue.length(); $i++) {
                    if ($reflector.$only.indexOf($realValue.charAt($i)) < 0) {
                        throw new ValidationException("invalidChar");
                    }
                }
            }
            if (($reflector.$max != null) && ($realValue.length() > $reflector.$max)) {
                throw new ValidationException("tooLong");
            }
            if (($reflector.$min != null) && ($realValue.length() < $reflector.$min)) {
                throw new ValidationException("tooShort");
            }
            if (($reflector.$match != null) && (!$realValue.matches($reflector.$match))) {
                throw new ValidationException("noMatch");
            }
        } else if (Types.isNumber($reflector.$type)) {
            Number $realValue = (Number) $value;
            NumberComparator $comparator = new NumberComparator();
            if (($reflector.$max != null) && ($comparator.compare($realValue, $reflector.$max) > 0)) {
                throw new ValidationException("tooLarge");
            }
            if (($reflector.$min != null) && ($comparator.compare($realValue, $reflector.$min) < 0)) {
                throw new ValidationException("tooSmall");
            }
        }

        V $validator = null;
        try {
            if ($reflector.$activeValidator != null) {
                $validator = (V) $reflector.$activeValidator.get($form);
            }
        } catch (Exception $exc) {
            throw new RuntimeException($exc);
        }
        try {
            if (($validator == null) && ($reflector.$validatorClass != null)) {
                $validator = (V) $reflector.$validatorClass.newInstance();
            }
        } catch (Exception $exc) {
            throw new RuntimeException($exc);
        }
        if ($validator != null) {
            try {
                Method $m = $validator.getClass().getMethod("check", $value.getClass());
                /*
                 * XXX: This is the first time I used setAccessible() and I really *do not want it*
                 * However, Java left me with no options (Except for throwing it all over and
                 * creating a new design from scratch - but I guess I’d rather go for another
                 * programming language, like Scala).
                 */
                $m.setAccessible(true);
                $m.invoke($validator, $value);
            } catch (InvocationTargetException $exc) {
                throw (Exception) $exc.getCause();
            } catch (Exception $exc) {
                throw $exc;
            }
        }
    }
}
