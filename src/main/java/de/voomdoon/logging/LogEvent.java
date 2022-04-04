package de.voomdoon.logging;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public interface LogEvent {

	/**
	 * @return {@link Throwable} or {@code null}
	 * @since 0.1.0
	 */
	Throwable getError();

	/**
	 * @return {@link LogLevel}
	 * @since 0.1.0
	 */
	LogLevel getLevel();

	/**
	 * @return {@link Object}
	 * @since 0.1.0
	 */
	Object getMessage();

	/**
	 * @return {@link Class}
	 * @since 0.1.0
	 */
	Class<?> getSourceClass();

	/**
	 * @return long
	 * @since 0.1.0
	 */
	long getTimestamp();
}
