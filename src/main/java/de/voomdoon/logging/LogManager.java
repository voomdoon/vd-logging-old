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

	/**
	 * @since 0.1.0
	 */
	private static final RootLogger ROOT_LOGGER;

	static {
		ROOT_LOGGER = new SynchronousRootLogger();
		ROOT_LOGGER.addLogEventHandler(new ConsoleLogEventHandler());

		addLogEventHandlers();
	}

	/**
	 * Adds a {@link LogEventHandler}.
	 * 
	 * @param handler
	 *            {@link LogEventHandler}
	 * @since 0.1.0
	 */
	public static void addLogEventHandler(LogEventHandler handler) {
		System.out.println("LogManager: add LogEventHandler " + handler);
		ROOT_LOGGER.addLogEventHandler(handler);
	}

	/**
	 * @param clazz
	 *            {@link Class}
	 * @return {@link Logger}
	 * @since 0.1.0
	 */
	public static Logger getLogger(Class<?> clazz) {
		return CLASS_LOGGERS.computeIfAbsent(clazz, c -> new DefaultLogger(ROOT_LOGGER, clazz));
	}

	/**
	 * Removes a {@link LogEventHandler}.
	 * 
	 * @param handler
	 *            {@link LogEventHandler}
	 * @since 0.1.0
	 */
	public static void removeLogEventHandler(LogEventHandler handler) {
		ROOT_LOGGER.removeLogEventHandler(handler);
	}

	/**
	 * DOCME add JavaDoc for method addLogEventHandlers
	 * 
	 * @since DOCME add inception version number
	 */
	private static void addLogEventHandlers() {
		try {
			Files.readAllLines(Paths.get(LogManager.class.getResource("/LogEventHandlers.csv").toURI()))
					.forEach(line -> {
						if (!line.isEmpty()) {
							tryAddLogEventHandler(line);
						}
					});
		} catch (IOException | URISyntaxException e) {
			// TODO implement error handling
			throw new RuntimeException("Error at 'addLogEventHandlers': " + e.getMessage(), e);
		}
	}

	/**
	 * DOCME add JavaDoc for method tryAddLogEventHandler
	 * 
	 * @param name
	 * @since DOCME add inception version number
	 */
	private static void tryAddLogEventHandler(String name) {
		LogEventHandler handler;

		try {
			handler = (LogEventHandler) Class.forName(name).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			return;
		}

		addLogEventHandler(handler);
	}

	/**
	 * @since 0.1.0
	 */
	private LogManager() {
		// nothing to do
	}
}
