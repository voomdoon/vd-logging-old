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
	 * DOCME add JavaDoc for method getError
	 * 
	 * @return
	 * @since 0.1.0
	 */
	Throwable getError();

	/**
	 * DOCME add JavaDoc for method getLevel
	 * 
	 * @return
	 * @since 0.1.0
	 */
	LogLevel getLevel();

	/**
	 * DOCME add JavaDoc for method getMessage
	 * 
	 * @return
	 * @since 0.1.0
	 */
	Object getMessage();

	/**
	 * DOCME add JavaDoc for method getSourceClass
	 * 
	 * @return
	 * @since 0.1.0
	 */
	Class<?> getSourceClass();

	/**
	 * DOCME add JavaDoc for method getTime
	 * 
	 * @return
	 * @since 0.1.0
	 */
	long getTimestamp();
}
