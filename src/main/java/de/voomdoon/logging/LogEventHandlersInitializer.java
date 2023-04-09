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
 * @since DOCME add inception version number
 */
class LogEventHandlersInitializer {

	/**
	 * DOCME
	 *
	 * @since DOCME add inception version number
	 */
	private LogManager logManager;

	/**
	 * DOCME
	 *
	 * @since DOCME add inception version number
	 */
	private RootLogger rootLogger;

	/**
	 *
	 * @param rootLogger
	 * @since DOCME add inception version number
	 */
	public LogEventHandlersInitializer(LogManager logManager, RootLogger rootLogger) {
		this.logManager = logManager;
		this.rootLogger = rootLogger;
	}

	/**
	 * @param logManager
	 * @since DOCME add inception version number
	 */
	public void initialize() {
		Set<String> added = addLogEventHandlers();

		if (added.isEmpty()) {
			rootLogger.addLogEventHandler(new ConsoleLogEventHandler());
		}
	}

	/**
	 *
	 * @param line
	 * @param headline
	 * @param logManager
	 * @return DOCME
	 * @since DOCME add inception version number
	 */
	private Set<String> addLogEventHandlerByRow(String line, String[] headline) {
		String[] split = line.split("\t");

		if (ignoreLogEventHandlerAtTest(split, headline)) {
			return Collections.emptySet();
		}

		Set<String> result = tryAddLogEventHandler(split[0]);

		if (LoggingInternalUtil.has(split, "noCount", headline)) {
			return Collections.emptySet();
		}

		return result;
	}

	/**
	 * DOCME add JavaDoc for method addLogEventHandlers
	 *
	 * @param logManager
	 *
	 * @return DOCME
	 * @since DOCME add inception version number
	 */
	private Set<String> addLogEventHandlers() {
		Set<String> result = new HashSet<>();

		try (InputStream resource = LogManager.class.getResourceAsStream("/LogEventHandlers.csv")) {
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

		return result;
	}

	/**
	 * @param split
	 * @param headline
	 * @return
	 * @since DOCME add inception version number
	 */
	private boolean ignoreLogEventHandlerAtTest(String[] split, String[] headline) {
		return LoggingInternalUtil.has(split, "ignoreAtTest", headline) && LoggingInternalUtil.isAtTest();
	}

	/**
	 * DOCME add JavaDoc for method tryAddLogEventHandler
	 *
	 * @param name
	 * @return DOCME
	 * @since DOCME add inception version number
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

		return Collections.singleton(handler.getClass().getSimpleName());
	}
}
