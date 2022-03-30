package de.voomdoon.logging.logger;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogLevel;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public class LogEventImpl implements LogEvent {

	/**
	 * @since 0.1.0
	 */
	private Throwable error;

	/**
	 * @since 0.1.0
	 */
	private LogLevel level;

	/**
	 * @since 0.1.0
	 */
	private Object message;

	/**
	 * @since 0.1.0
	 */
	private Class<?> sourceClass;

	/**
	 * @since 0.1.0
	 */
	private long timestamp;

	/**
	 * DOCME add JavaDoc for constructor LogEventImpl
	 * 
	 * @param level
	 * @param message
	 * @since 0.1.0
	 */
	public LogEventImpl(LogLevel level, Object message) {
		this.level = level;
		this.message = message;

		timestamp = System.currentTimeMillis();
	}

	/**
	 * DOCME add JavaDoc for constructor LogEventImpl
	 * 
	 * @param message
	 * @param error
	 * @since 0.1.0
	 */
	public LogEventImpl(LogLevel level, Object message, Throwable error) {
		this(level, message);

		this.error = error;
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public Throwable getError() {
		return error;
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public LogLevel getLevel() {
		return level;
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public Object getMessage() {
		return message;
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public Class<?> getSourceClass() {
		return sourceClass;
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * DOCME add JavaDoc for method setSourceClass
	 * 
	 * @param sourceClass
	 * @since 0.1.0
	 */
	public void setSourceClass(Class<?> sourceClass) {
		this.sourceClass = sourceClass;
	}
}
