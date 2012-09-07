package de.fu.sandpit;

/**
 * This is a simple HelloWorld-class which is to be crosscutted (we say advised)
 * by the sample aspect HelloAspect.
 *
 * @see HelloAspect
 */
public class HelloWorld {
	/**
	 * Just an ordinary main-method calling a method. Note that we need to
	 * define a method which is not static since AspectJ can not easily
	 * crosscut static methods.
	 */
	public static void main(String... args) {
		new HelloWorld().sayHello();
	}
	
	/**
	 * Print "Hello, World!"
	 */
	public void sayHello() {
		System.out.println("Hello, World!");
	}
}

