package de.voomdoon.logging.handler;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogEventHandler;
import de.voomdoon.logging.LogLevel;

/**
 * {@link LogEventHandler} to print onto the console.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public class ConsoleLogEventHandler implements LogEventHandler {

	/**
	 * @since 0.1.0
	 */
	private final Date date = new Date();

	/**
	 * @since 0.1.0
	 */
	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	/**
	 * @since 0.1.0
	 */
	@Override
	public void handleLogEvent(LogEvent logEvent) {
		Objects.requireNonNull(logEvent, "logEvent");

		PrintStream stream = getPrintStream(logEvent.getLevel());

		stream.println(toString(logEvent));

		if (logEvent.getError() != null) {
			logEvent.getError().printStackTrace(stream);
		}
	}

	/**
	 * Formats a timestamp as a date {@link String}.
	 *
	 * @param timestamp
	 * @return Date {@link String}.
	 * @since 0.1.0
	 */
	private String formatTimestamp(long timestamp) {
		synchronized (dateFormat) {
			date.setTime(timestamp);

			return dateFormat.format(date);
		}
	}

	/**
	 * DOCME add JavaDoc for method getName
	 *
	 * @param sourceClass
	 * @return
	 * @since 0.1.0
	 */
	private Object getName(Class<?> sourceClass) {
		if (sourceClass == null) {
			return "null";
		}

		return sourceClass.getName();
	}

	/**
	 * @param level
	 *            {@link LogLevel}
	 * @return {@link PrintStream}
	 * @since 0.1.0
	 */
	private PrintStream getPrintStream(LogLevel level) {
		if (level.getPriority() >= LogLevel.ERROR.getPriority()) {
			return System.err;
		}

		return System.out;
	}

	/**
	 * @param logEvent
	 *            {@link LogEvent}
	 * @return {@link String}
	 * @since 0.1.0
	 */
	private String toString(LogEvent logEvent) {
		StringBuilder sb = new StringBuilder();
		sb.append(formatTimestamp(logEvent.getTimestamp()));
		sb.append(" ");
		sb.append(logEvent.getLevel().toString());
		sb.append("\t(");
		sb.append(getName(logEvent.getSourceClass()));
		sb.append("): ");
		sb.append(logEvent.getMessage());

		return sb.toString();
	}
}
