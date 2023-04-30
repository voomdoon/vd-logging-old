package de.voomdoon.logging.test;

import java.util.ArrayList;
import java.util.List;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogEventHandler;

/**
 * @author André Schulz
 *
 * @since 0.1.0
 */
public class TestLogEventHandler2 implements LogEventHandler {

	/**
	 * @since 0.1.0
	 */
	private static final List<TestLogEventHandler2> INSTANCES = new ArrayList<>();

	/**
	 * @return instances
	 * @since 0.1.0
	 */
	public static List<TestLogEventHandler2> getInstances() {
		return INSTANCES;
	}

	/**
	 * @since 0.1.0
	 */
	private List<LogEvent> events;

	/**
	 * @since 0.1.0
	 */
	public TestLogEventHandler2() {
		this(new ArrayList<>());

		INSTANCES.add(this);
	}

	/**
	 * @param events
	 * @since 0.1.0
	 */
	public TestLogEventHandler2(List<LogEvent> events) {
		this.events = events;
	}

	/**
	 * @return events
	 * @since 0.1.0
	 */
	public List<LogEvent> getEvents() {
		return events;
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void handleLogEvent(LogEvent logEvent) {
		getEvents().add(logEvent);
	}
}
