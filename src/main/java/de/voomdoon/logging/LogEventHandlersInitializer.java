package de.voomdoon.logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.voomdoon.logging.handler.ConsoleLogEventHandler;
import de.voomdoon.logging.root.RootLogger;

/**
 * DOCME
 *
 * @since 0.1.0
 */
class LogEventHandlersInitializer {

	/**
	 * DOCME
	 *
	 * @since 0.1.0
	 */
	private LogManager logManager;

	/**
	 * DOCME
	 *
	 * @since 0.1.0
	 */
	private RootLogger rootLogger;

	/**
	 *
	 * @param rootLogger
	 * @since 0.1.0
	 */
	public LogEventHandlersInitializer(LogManager logManager, RootLogger rootLogger) {
		this.logManager = logManager;
		this.rootLogger = rootLogger;
	}

	/**
	 * @param logManager
	 * @since 0.1.0
	 */
	public void initialize() {
		Set<String> added = addLogEventHandlers();

		if (added.isEmpty()) {
			rootLogger.addLogEventHandler(new ConsoleLogEventHandler());// TESTME
		}
	}

	/**
	 *
	 * @param line
	 * @param headline
	 * @param logManager
	 * @return DOCME
	 * @since 0.1.0
	 */
	private Set<String> addLogEventHandlerByRow(String line, String[] headline) {
		String[] split = line.split("\t");

		if (ignoreLogEventHandlerAtTest(split, headline)) {
			return Collections.emptySet();
		}

		Set<String> result = tryAddLogEventHandler(split[0]);

		if (LoggingInternalUtil.hasTrue(split, "noCount", headline)) {
			return Collections.emptySet();
		}

		return result;// TESTME
	}

	/**
	 * DOCME add JavaDoc for method addLogEventHandlers
	 *
	 * @param logManager
	 *
	 * @return DOCME
	 * @since 0.1.0
	 */
	private Set<String> addLogEventHandlers() {
		Set<String> result = new HashSet<>();

		try (InputStream resource = LogManager.class.getResourceAsStream("/LogEventHandlers.csv")) {
			if (resource == null) {
				throw new IllegalStateException("Failed to get LogEventHandlers.csv!");
			}

			List<String> lines = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines()
					.toList();
			String[] headline = null;

			for (String line : lines) {
				if (headline == null) {
					headline = lines.get(0).split("\t");
				} else if (!line.isEmpty()) {
					result.addAll(addLogEventHandlerByRow(line, headline));
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("Error at 'addLogEventHandlers': " + e.getMessage(), e);
		}

		return result;// TESTME
	}

	/**
	 * @param split
	 * @param headline
	 * @return
	 * @since 0.1.0
	 */
	private boolean ignoreLogEventHandlerAtTest(String[] split, String[] headline) {
		return LoggingInternalUtil.hasTrue(split, "ignoreAtTest", headline) && LoggingInternalUtil.isAtTest();
	}

	/**
	 * DOCME add JavaDoc for method tryAddLogEventHandler
	 *
	 * @param name
	 * @return DOCME
	 * @since 0.1.0
	 */
	private Set<String> tryAddLogEventHandler(String name) {
		LogEventHandler handler;

		try {
			handler = (LogEventHandler) Class.forName(name).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			return Collections.emptySet();
		}

		logManager.addLogEventHandlerInternal(handler);

		return Collections.singleton(handler.getClass().getSimpleName());// TESTME
	}
}
