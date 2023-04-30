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
	 * @param level
	 *            {@link LogLevel}
	 * @param message
	 *            {@link Object}
	 * @since 0.1.0
	 */
	public LogEventImpl(LogLevel level, Object message) {
		this.level = level;
		this.message = message;

		timestamp = System.currentTimeMillis();
	}

	/**
	 * @param level
	 *            {@link LogLevel}
	 * @param message
	 *            {@link Object}
	 * @param error
	 *            optional {@link Throwable}
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
	 * @param sourceClass
	 *            {@link Class}
	 * @since 0.1.0
	 */
	public void setSourceClass(Class<?> sourceClass) {
		this.sourceClass = sourceClass;
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LogEventImpl(timestamp: ");
		builder.append(timestamp);
		builder.append(", level: ");
		builder.append(level);
		builder.append(", message: ");
		builder.append(message);
		builder.append(", sourceClass: ");
		builder.append(sourceClass);
		builder.append(", error: ");
		builder.append(error);
		builder.append(")");
		return builder.toString();
	}
}
