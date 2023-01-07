package de.voomdoon.logging.root;

import java.util.Set;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogEventHandler;

/**
 * Handles the logging internally.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public interface RootLogger {

	/**
	 * @param handler
	 *            {@link LogEventHandler}
	 * @since 0.1.0
	 */
	void addLogEventHandler(LogEventHandler handler);

	/**
	 * @return
	 * @since 0.1.0
	 */
	Set<String> getLogEventHanderNames();

	/**
	 * @param logEvent
	 *            {@link LogEvent}
	 * @since 0.1.0
	 */
	void log(LogEvent logEvent);

	/**
	 * @param handler
	 *            {@link LogEventHandler}
	 * @since 0.1.0
	 */
	void removeLogEventHandler(LogEventHandler handler);
}
