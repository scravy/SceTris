/**
 * Provides an implementation of a client-server architecture in order to
 * realize the interprocess communication between Scheduler and Web Interface.
 * Basically the client instantiates a command object and sends it via sockets
 * to the server. The server uses a dispatcher to queue the incoming commands
 * and executes them when being in a ready state.
 * <p>
 * The Command pattern is applied in order to decouple the Web Client which
 * invokes the commands from the Scheduler which knows how to perform the
 * command.
 * <p>
 * New commands can be added by implementing the Command interface.
 * <p>
 * Macro commands can be added by using the Composite pattern.
 * 
 * @since Iteration3
 */
package de.fu.scetris.ipc;

