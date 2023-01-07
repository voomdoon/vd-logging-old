package de.voomdoon.logging.test;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogLevel;

//TODO use LomBock

/**
 * @author André Schulz
 *
 * @since 0.1.0
 */
public class TestLogEvent implements LogEvent {

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
	 * @since 0.1.0
	 */
	public TestLogEvent() {
		level = LogLevel.DEBUG;
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
	 * DOCME add JavaDoc for method setError
	 *
	 * @param error
	 * @return {@link TestLogEvent}
	 * @since 0.1.0
	 */
	public TestLogEvent setError(Throwable error) {
		this.error = error;

		return this;
	}

	/**
	 * @param level
	 *            {@link LogLevel}
	 * @return {@link TestLogEvent}
	 * @since 0.1.0
	 */
	public TestLogEvent setLevel(LogLevel level) {
		this.level = level;

		return this;
	}

	/**
	 * @param message
	 * @return {@link TestLogEvent}
	 * @since 0.1.0
	 */
	public TestLogEvent setMessage(Object message) {
		this.message = message;

		return this;
	}

	/**
	 * @param sourceClass
	 * @return {@link TestLogEvent}
	 * @since 0.1.0
	 */
	public TestLogEvent setSourceClass(Class<?> sourceClass) {
		this.sourceClass = sourceClass;

		return this;
	}

	/**
	 * DOCME add JavaDoc for method setTimeStamp
	 *
	 * @param timestamp
	 * @return
	 * @since 0.1.0
	 */
	public LogEvent setTimestamp(long timestamp) {
		this.timestamp = timestamp;

		return this;
	}
}