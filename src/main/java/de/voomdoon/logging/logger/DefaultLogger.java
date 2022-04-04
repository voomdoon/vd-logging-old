package de.voomdoon.logging.logger;

import de.voomdoon.logging.LogLevel;
import de.voomdoon.logging.Logger;
import de.voomdoon.logging.root.RootLogger;

/**
 * Default implementation of {@link Logger}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public class DefaultLogger implements Logger {

	/**
	 * @since 0.1.0
	 */
	private RootLogger rootLogger;

	/**
	 * @since 0.1.0
	 */
	private Class<?> sourceClass;

	/**
	 * @param rootLogger
	 *            {@link RootLogger}
	 * @param sourceClass
	 *            source {@link Class}
	 * @since 0.1.0
	 */
	public DefaultLogger(RootLogger rootLogger, Class<?> sourceClass) {
		this.rootLogger = rootLogger;
		this.sourceClass = sourceClass;
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void debug(Object message) {
		log(LogLevel.DEBUG, message, null);
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void debug(Object message, Throwable throwable) {
		log(LogLevel.DEBUG, message, throwable);
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void error(Object message) {
		log(LogLevel.ERROR, message, null);
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void error(Object message, Throwable throwable) {
		log(LogLevel.ERROR, message, throwable);
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void fatal(Object message) {
		log(LogLevel.FATAL, message, null);
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void fatal(Object message, Throwable throwable) {
		log(LogLevel.FATAL, message, throwable);
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void info(Object message) {
		log(LogLevel.INFO, message, null);
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void info(Object message, Throwable throwable) {
		log(LogLevel.INFO, message, throwable);
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public boolean isActive(LogLevel level) {
		// TODO implement isActive
		return true;
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void log(LogLevel level, Object message) {
		log(level, message, null);
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void log(LogLevel level, Object message, Throwable throwable) {
		log(new LogEventImpl(level, message, throwable));
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void trace(Object message) {
		log(LogLevel.TRACE, message, null);
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void trace(Object message, Throwable throwable) {
		log(LogLevel.TRACE, message, throwable);
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void warn(Object message) {
		log(LogLevel.WARN, message, null);
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void warn(Object message, Throwable throwable) {
		log(LogLevel.WARN, message, throwable);
	}

	/**
	 * @param logEventImpl
	 *            {@link LogEventImpl}
	 * @since 0.1.0
	 */
	private void log(LogEventImpl logEventImpl) {
		logEventImpl.setSourceClass(sourceClass);
		rootLogger.log(logEventImpl);
	}
}
