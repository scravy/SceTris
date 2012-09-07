/* CmdLineArgs.java / 12:50:04 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.fu.junction.converter.StringConverter;

public class CmdLineArgs {

	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public static @interface Arg {
		Class<? extends StringConverter<?>>[] converter() default {};

		Class<?> type();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@Documented
	public static @interface Opt {
		Arg[] args() default {};

		String[] longOpt() default {};

		boolean multiple() default false;

		char[] oneLetter() default {};
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public static @interface Opts {
		Opt[] value();
	}

	@Opts({ @Opt(longOpt = "help", oneLetter = 'h'),
			@Opt(longOpt = "version", oneLetter = 'v'),
			@Opt(longOpt = "width", oneLetter = 'w', args = {
					@Arg(type = int.class)
			}),
			@Opt(longOpt = "height", oneLetter = 'h', args = {
					@Arg(type = int.class)
			}),
			@Opt(oneLetter = 'x', args = {
					@Arg(type = int.class)
			}),
			@Opt(oneLetter = 'y', args = {
					@Arg(type = int.class)
			}),
			@Opt(longOpt = "license") })
	public static class Sample {

	}

	public static CmdLineArgs parse(final String[] $args, final Class<?> $scheme) {
		return new CmdLineArgs($scheme).feed($args);
	}

	public static CmdLineArgs parse(final String[] $args, final Object $scheme) {
		return parse($args, $scheme.getClass());
	}

	private CmdLineArgs(final Class<?> $scheme) {

	}

	private CmdLineArgs feed(final String[] $args) {
		return this;
	}
}
