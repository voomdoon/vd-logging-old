package de.voomdoon.logging;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

		// TODO add ConsoleLogEventHandler only if there is no other
		rootLogger.addLogEventHandler(new ConsoleLogEventHandler());

		addLogEventHandlers();
	}

	private void addLogEventHandlerByRow(String line) {
		String[] split = line.split("\t");

		if (ignoreLogEventHandler(split)) {
			return;
		}

		tryAddLogEventHandler(split[0]);
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
	 * @since DOCME add inception version number
	 */
	private void addLogEventHandlers() {
		try {
			Files.readAllLines(Paths.get(LogManager.class.getResource("/LogEventHandlers.csv").toURI()))
					.forEach(line -> {
						if (!line.isEmpty()) {
							addLogEventHandlerByRow(line);
						}
					});
		} catch (IOException | URISyntaxException e) {
			// TODO implement error handling
			throw new RuntimeException("Error at 'addLogEventHandlers': " + e.getMessage(), e);
		}
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
	 * @return
	 * @since DOCME add inception version number
	 */
	private boolean ignoreLogEventHandler(String[] split) {
		return split.length > 1 && "noTest".equals(split[1]);
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
	 * @since DOCME add inception version number
	 */
	private void tryAddLogEventHandler(String name) {
		LogEventHandler handler;

		try {
			handler = (LogEventHandler) Class.forName(name).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			return;
		}

		addLogEventHandlerInternal(handler);
	}
}
