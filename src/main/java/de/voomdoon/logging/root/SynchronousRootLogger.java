package de.voomdoon.logging.root;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogEventHandler;

/**
 * Synchronous {@link RootLogger}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public class SynchronousRootLogger implements RootLogger {

	/**
	 * @since 0.1.0
	 */
	private List<LogEventHandler> logEventHandlers;

	/**
	 * @since 0.1.0
	 */
	public SynchronousRootLogger() {
		logEventHandlers = new ArrayList<>();
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void addLogEventHandler(LogEventHandler handler) {
		if (!logEventHandlers.contains(Objects.requireNonNull(handler, "handler"))) {
			logEventHandlers.add(handler);
		}
	}

	@Override
	public Set<String> getLogEventHanderNames() {
		return logEventHandlers.stream().map(Object::getClass).map(Class::getSimpleName).collect(Collectors.toSet());
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void log(LogEvent logEvent) {
		for (LogEventHandler logEventHandler : logEventHandlers) {
			logEventHandler.handleLogEvent(logEvent);
		}
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void removeLogEventHandler(LogEventHandler handler) {
		if (!logEventHandlers.contains(Objects.requireNonNull(handler, "handler"))) {
			throw new NoSuchElementException("Argument 'handler' refers to unknown LogEventHandler!");
		}

		logEventHandlers.remove(handler);
	}
}
