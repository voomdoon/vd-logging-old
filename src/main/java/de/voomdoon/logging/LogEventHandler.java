package de.voomdoon.logging;

/**
 * Handler for {@link LogEvent}s.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public interface LogEventHandler {

	/**
	 * Handles a {@link LogEvent}.
	 *
	 * @param logEvent
	 *            The {@link LogEvent} to handle.
	 * @since 0.1.0
	 */
	public void handleLogEvent(LogEvent logEvent);
}
