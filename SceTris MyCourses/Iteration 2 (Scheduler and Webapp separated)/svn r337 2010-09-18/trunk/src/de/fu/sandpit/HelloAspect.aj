package de.fu.sandpit;

/**
 * This is our first sample aspect.
 * <p>
 * It crosscuts HelloWorld before
 * and after it says "hello". For documentation on AspectJ visit
 * <a href="http://eclipse.org/aspectj/doc/released/progguide/starting.html">Getting started with AspectJ</a>
 */
public aspect HelloAspect {
	/**
	 * This pointcut refers to the execution of the helloWorld-method.
	 */
	pointcut hello():
		execution(void HelloWorld.sayHello());
	
	/**
	 * This advice crosscuts the execution of the code in HelloWorld
	 * <b>before</b> the helloWorld-method is executed and prints
	 * "...before execution..." before <tt>HelloWorld.sayHello()</tt> prints
	 * "Hello, World!"
	 */
	before(): hello() {
		System.out.println("...before execution...");
	}
	
	/**
	 * This advice crosscuts the execution of the code in HelloWorld
	 * <b>after</b> the helloWorld-method is executed and prints
	 * "...after execution..." after <tt>HelloWorld.sayHello()</tt> prints
	 * "Hello, World!"
	 */
	after(): hello() {
		System.out.println("...after execution...");
	}
}
