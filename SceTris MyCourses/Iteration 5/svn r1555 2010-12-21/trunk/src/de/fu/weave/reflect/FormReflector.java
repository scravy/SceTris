package de.fu.weave.reflect;

import static de.fu.weave.xml.Namespaces.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.fu.junction.Types;
import de.fu.junction.converter.StringConverter;
import de.fu.junction.converter.StringConverterFactory;
import de.fu.junction.data.InvalidSyntaxException;
import de.fu.junction.data.Password;
import de.fu.junction.data.Range;
import de.fu.weave.Form;
import de.fu.weave.Form.ActiveValidator;
import de.fu.weave.Form.Alternatives;
import de.fu.weave.Form.Control;
import de.fu.weave.Form.Converter;
import de.fu.weave.Form.FormControl;
import de.fu.weave.Form.Match;
import de.fu.weave.Form.Max;
import de.fu.weave.Form.Min;
import de.fu.weave.Form.Only;
import de.fu.weave.Form.Pos;
import de.fu.weave.Form.Required;
import de.fu.weave.Form.Step;
import de.fu.weave.Form.Validator;
import de.fu.weave.ParamReflector;

/**
 * 
 * 
 * @param <F>
 *            The Form that this Reflector reflects
 */
public class FormReflector<F extends Form> implements ParamReflector {

	/**
	 * Holds information about a form field.
	 */
	class FieldReflector {
		final Double $min;
		final Double $max;
		final Double $step;
		final String $match;
		final String $only;
		final Double $pos;
		final StringConverter<?> $converter;
		final Control $control;
		final Field $alternatives;
		final boolean $isRequired;
		final Class<? extends de.fu.weave.Validator> $validatorClass;
		final Field $activeValidator;

		final Field $field;
		final Class<?> $type;
		final String $name;

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

			if ($field.isAnnotationPresent(Converter.class)) {
				Class<? extends StringConverter<?>> $converterClass;
				Constructor<? extends StringConverter<?>> $constructor;

				StringConverter<?> $c;
				$converterClass = $field.getAnnotation(Converter.class).value();
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
			} else if (Range.class.isAssignableFrom($type)) {
				$converter = $converterFactory.newConverter(double.class);
			} else {
				try {
					$converter = $converterFactory.newConverter($type);
				} catch (RuntimeException $exc) {
					throw new FormReflectorException($exc.getCause());
				}
			}
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
					$control = Control.ALTERNATIVES;
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
			Class<? extends de.fu.weave.Validator> $tmpValidatorClass = null;
			if ($field.isAnnotationPresent(Validator.class)) {
				$tmpValidatorClass = $field.getAnnotation(Validator.class).value();
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
				$field.set($form, $value);
			} catch (Exception $exc) {
				throw new RuntimeException();
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
	final Map<String,FieldReflector> $formFields = new TreeMap<String,FieldReflector>();

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
	FormReflector(final Class<F> $formType) throws FormReflectorException {
		this.$formType = $formType;
		Field[] $fields = $formType.getFields();

		for (Field $field : $fields) {
			if ($field.isAnnotationPresent(Form.Field.class)) {
				FieldReflector $reflector = new FieldReflector($field);
				$formFields.put($reflector.$name, $reflector);
			}
		}
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
	 * @throws InvalidSyntaxException
	 *             If the value could not be converted to the type of the field
	 */
	public void setField(final F $form, final String $name, final Object $value)
		throws InvalidSyntaxException {
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
			return;
		}
		if (Types.isArray($value) && !$formField.$type.isArray()) {
			Object[] $arrayValue = (Object[]) $value;
			if ($arrayValue.length < 1) {
				return;
			}
			$realValue = $arrayValue[0];
		}
		if ($realValue instanceof String) {
			Object $parsedValue = $formField.$converter.convert((String) $realValue);
			if ($parsedValue == null) {
				// the value could not be converted, which means that an incorrect value was given
				throw new InvalidSyntaxException((String) $realValue);
			}
			$formField.setValue($form, $parsedValue);
		}
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
		Document $d = $e.getOwnerDocument();

		$e.setAttribute("formType", $form.getClass().getCanonicalName());
		Map<String,Exception> $messages = $form.getMessages();

		FieldReflector $reflector;
		for (String $fieldName : $formFields.keySet()) {
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
			if ($messages.containsKey($fieldName)) {
				Exception $message = $messages.get($fieldName);
				$f.setAttribute("error-type", $message.getClass().getSimpleName());
				$f.setAttribute("error-code", $message.getMessage());
			}
			if ($reflector.hasAlternatives()) {
				Object $alternatives = $reflector.getAlternatives($form);
				if ($alternatives instanceof Map) {
					Map<?,?> $options = (Map<?,?>) $alternatives;
					for (Entry<?,?> $en : $options.entrySet()) {
						Element $n = $d.createElementNS(XMLNS_FORM, "o");
						$n.setAttribute("k", $en.getKey().toString());
						$n.setAttribute("v", $en.getValue().toString());
						$f.appendChild($n);
					}
				} else if (($alternatives instanceof Iterable<?>)) {
					Iterable<?> $options = (Iterable<?>) $alternatives;
					for (Object $o : $options) {
						Element $n = $d.createElementNS(XMLNS_FORM, "o");
						$n.setAttribute("v", $o.toString());
						$f.appendChild($n);
					}
				} else if (Types.isArray($alternatives)) {
					Object[] $options = (Object[]) $alternatives;
					for (Object $o : $options) {
						Element $n = $d.createElementNS(XMLNS_FORM, "o");
						$n.setAttribute("v", $o.toString());
						$f.appendChild($n);
					}
				}
			}
			if ($reflector.hasValue($form)) {
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
	public <V extends de.fu.weave.Validator> void validate(final Form $form, final String $fieldName)
		throws ValidationException {
		FieldReflector $reflector = $formFields.get($fieldName);
		V $validator = null;
		try {
			if ($reflector.$activeValidator != null) {
				$validator = (V) $reflector.$activeValidator.get($form);
			}
		} catch (Exception $exc) {
			// ignore silently
		}
		try {
			if ($validator == null) {
				$validator = (V) $reflector.$validatorClass.newInstance();
			}
		} catch (Exception $exc) {
			// ignore silently
		}
		if ($validator != null) {
			$validator.check($reflector.getValue($form));
		}
	}
}
