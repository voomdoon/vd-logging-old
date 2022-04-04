package de.voomdoon.logging;

/**
 * @author André Schulz
 *
 * @since 0.1.0
 */
public interface Logger {

	/**
	 * @param message
	 * @since 0.1.0
	 */
	void debug(Object message);

	/**
	 * @param message
	 * @param throwable
	 * @since 0.1.0
	 */
	void debug(Object message, Throwable throwable);

	/**
	 * @param message
	 * @since 0.1.0
	 */
	void error(Object message);

	/**
	 * @param message
	 * @param throwable
	 * @since 0.1.0
	 */
	void error(Object message, Throwable throwable);

	/**
	 * @param message
	 * @since 0.1.0
	 */
	void fatal(Object message);

	/**
	 * @param message
	 * @param throwable
	 * @since 0.1.0
	 */
	void fatal(Object message, Throwable throwable);

	/**
	 * @param message
	 * @since 0.1.0
	 */
	void info(Object message);

	/**
	 * @param message
	 * @param throwable
	 * @since 0.1.0
	 */
	void info(Object message, Throwable throwable);

	/**
	 * @param level
	 * @return
	 * @since 0.1.0
	 */
	boolean isActive(LogLevel level);

	/**
	 * @param level
	 * @param message
	 * @since 0.1.0
	 */
	void log(LogLevel level, Object message);

	/**
	 * @param level
	 * @param message
	 * @param throwable
	 * @since 0.1.0
	 */
	void log(LogLevel level, Object message, Throwable throwable);

	/**
	 * @param message
	 * @since 0.1.0
	 */
	void trace(Object message);

	/**
	 * @param message
	 * @param throwable
	 * @since 0.1.0
	 */
	void trace(Object message, Throwable throwable);

	/**
	 * @param message
	 * @since 0.1.0
	 */
	void warn(Object message);

	/**
	 * @param message
	 * @param throwable
	 * @since 0.1.0
	 */
	void warn(Object message, Throwable throwable);
}
