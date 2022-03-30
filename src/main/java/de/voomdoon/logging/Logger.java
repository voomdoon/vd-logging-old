package de.voomdoon.logging;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public interface Logger {

	/**
	 * DOCME add JavaDoc for method debug
	 * 
	 * @param message
	 * @since 0.1.0
	 */
	void debug(Object message);

	/**
	 * DOCME add JavaDoc for method debug
	 * 
	 * @param message
	 * @param throwable
	 * @since 0.1.0
	 */
	void debug(Object message, Throwable throwable);

	/**
	 * DOCME add JavaDoc for method error
	 * 
	 * @param message
	 * @since 0.1.0
	 */
	void error(Object message);

	/**
	 * DOCME add JavaDoc for method error
	 * 
	 * @param message
	 * @param throwable
	 * @since 0.1.0
	 */
	void error(Object message, Throwable throwable);

	/**
	 * DOCME add JavaDoc for method fatal
	 * 
	 * @param message
	 * @since 0.1.0
	 */
	void fatal(Object message);

	/**
	 * DOCME add JavaDoc for method fatal
	 * 
	 * @param message
	 * @param throwable
	 * @since 0.1.0
	 */
	void fatal(Object message, Throwable throwable);

	/**
	 * DOCME add JavaDoc for method info
	 * 
	 * @param message
	 * @since 0.1.0
	 */
	void info(Object message);

	/**
	 * DOCME add JavaDoc for method info
	 * 
	 * @param message
	 * @param throwable
	 * @since 0.1.0
	 */
	void info(Object message, Throwable throwable);

	/**
	 * DOCME add JavaDoc for method isActive
	 * 
	 * @param level
	 * @return
	 * @since 0.1.0
	 */
	boolean isActive(LogLevel level);

	/**
	 * DOCME add JavaDoc for method log
	 * 
	 * @param level
	 * @param message
	 * @since 0.1.0
	 */
	void log(LogLevel level, Object message);

	/**
	 * DOCME add JavaDoc for method log
	 * 
	 * @param level
	 * @param message
	 * @param throwable
	 * @since 0.1.0
	 */
	void log(LogLevel level, Object message, Throwable throwable);

	/**
	 * DOCME add JavaDoc for method trace
	 * 
	 * @param message
	 * @since 0.1.0
	 */
	void trace(Object message);

	/**
	 * DOCME add JavaDoc for method trace
	 * 
	 * @param message
	 * @param throwable
	 * @since 0.1.0
	 */
	void trace(Object message, Throwable throwable);

	/**
	 * DOCME add JavaDoc for method warn
	 * 
	 * @param message
	 * @since 0.1.0
	 */
	void warn(Object message);

	/**
	 * DOCME add JavaDoc for method warn
	 * 
	 * @param message
	 * @param throwable
	 * @since 0.1.0
	 */
	void warn(Object message, Throwable throwable);
}
