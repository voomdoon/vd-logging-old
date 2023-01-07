package de.voomdoon.logging;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.WeakHashMap;

import de.voomdoon.logging.handler.ConsoleLogEventHandler;
import de.voomdoon.logging.logger.DefaultLogger;
import de.voomdoon.logging.root.RootLogger;
import de.voomdoon.logging.root.SynchronousRootLogger;

/**
 * Manager for logging.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public class LogManager {

	/**
	 * @since 0.1.0
	 */
	private static final WeakHashMap<Class<?>, Logger> CLASS_LOGGERS = new WeakHashMap<>();

	private static final LogManager INSTANCE;

	static {
		INSTANCE = new LogManager();
		INSTANCE.logInitialization();
	}

	/**
	 * Adds a {@link LogEventHandler}.
	 *
	 * @param handler
	 *            {@link LogEventHandler}
	 * @since 0.1.0
	 */
	public static void addLogEventHandler(LogEventHandler handler) {
		INSTANCE.addLogEventHandlerInternal(handler);
	}

	/**
	 * @param clazz
	 *            {@link Class}
	 * @return {@link Logger}
	 * @since 0.1.0
	 */
	public static Logger getLogger(Class<?> clazz) {
		return INSTANCE.getLoggerInternal(clazz);
	}

	/**
	 * Removes a {@link LogEventHandler}.
	 *
	 * @param handler
	 *            {@link LogEventHandler}
	 * @since 0.1.0
	 */
	public static void removeLogEventHandler(LogEventHandler handler) {
		INSTANCE.removeLogEventHandlerInternal(handler);
	}

	/**
	 * @since 0.1.0
	 */
	private final RootLogger rootLogger;

	/**
	 * @since 0.1.0
	 */
	LogManager() {
		rootLogger = new SynchronousRootLogger();

		int count = addLogEventHandlers();

		if (count == 0) {
			rootLogger.addLogEventHandler(new ConsoleLogEventHandler());
		}
	}

	private int addLogEventHandlerByRow(String line, String[] headline) {
		String[] split = line.split("\t");

		if (ignoreLogEventHandlerAtTest(split, headline)) {
			return 0;
		}

		int count = tryAddLogEventHandler(split[0]);

		if (has(split, "noCount", headline)) {
			return 0;
		}

		return count;
	}

	/**
	 * DOCME
	 * 
	 * @param handler
	 * @since DOCME add inception version number
	 */
	private void addLogEventHandlerInternal(LogEventHandler handler) {
		System.out.println("LogManager: addLogEventHandler " + handler);
		rootLogger.addLogEventHandler(handler);
	}

	/**
	 * DOCME add JavaDoc for method addLogEventHandlers
	 *
	 * @return DOCME
	 * @since DOCME add inception version number
	 */
	private int addLogEventHandlers() {
		int count = 0;

		try {
			List<String> lines = Files
					.readAllLines(Paths.get(LogManager.class.getResource("/LogEventHandlers.csv").toURI()));
			String[] headline = null;

			for (String line : lines) {
				if (headline == null) {
					headline = lines.get(0).split("\t");
				} else if (!line.isEmpty()) {
					count += addLogEventHandlerByRow(line, headline);
				}
			}
		} catch (IOException | URISyntaxException e) {
			// TODO implement error handling
			throw new RuntimeException("Error at 'addLogEventHandlers': " + e.getMessage(), e);
		}

		return count;
	}

	/**
	 * @param row
	 * @param value
	 * @return
	 * @since DOCME add inception version number
	 */
	private int getIndex(String[] row, String value) {
		for (int i = 0; i < row.length; i++) {
			if (value.equals(row[i])) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * DOCME
	 * 
	 * @param clazz
	 * @return
	 * @since 0.1.0
	 */
	Logger getLoggerInternal(Class<?> clazz) {
		return CLASS_LOGGERS.computeIfAbsent(clazz, c -> new DefaultLogger(rootLogger, clazz));
	}

	/**
	 * @param split
	 * @param string
	 * @param headline
	 * @return
	 * @since DOCME add inception version number
	 */
	private boolean has(String[] split, String string, String[] headline) {
		int index = getIndex(headline, "noTest");

		return split.length > index && "true".equals(split[index]) && isAtTest();
	}

	/**
	 * @param split
	 * @param headline
	 * @return
	 * @since DOCME add inception version number
	 */
	private boolean ignoreLogEventHandlerAtTest(String[] split, String[] headline) {
		return has(split, "noTest", headline);
	}

	/**
	 * @return
	 * @since DOCME add inception version number
	 */
	private boolean isAtTest() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

		for (StackTraceElement element : stackTrace) {
			if (element.getClassName().startsWith("org.junit.platform.launcher.core")) {
				return true;
			}
		}

		return false;
	}

	private void logInitialization() {
		getLogger(getClass())
				.debug("initialized with " + rootLogger.getLogEventHanderNames().stream().sorted().toList());
	}

	/**
	 * DOCME
	 * 
	 * @param handler
	 * @since DOCME add inception version number
	 */
	private void removeLogEventHandlerInternal(LogEventHandler handler) {
		rootLogger.removeLogEventHandler(handler);
	}

	/**
	 * DOCME add JavaDoc for method tryAddLogEventHandler
	 *
	 * @param name
	 * @return DOCME
	 * @since DOCME add inception version number
	 */
	private int tryAddLogEventHandler(String name) {
		LogEventHandler handler;

		try {
			handler = (LogEventHandler) Class.forName(name).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			return 0;
		}

		addLogEventHandlerInternal(handler);

		return 1;
	}
}
