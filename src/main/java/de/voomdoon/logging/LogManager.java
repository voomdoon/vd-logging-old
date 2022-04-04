package de.voomdoon.logging;

import java.util.WeakHashMap;

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
	}

	/**
	 * Adds a {@link LogEventHandler}.
	 * 
	 * @param handler
	 *            {@link LogEventHandler}
	 * @since 0.1.0
	 */
	public static void addLogEventHandler(LogEventHandler handler) {
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
	 * @since 0.1.0
	 */
	private LogManager() {
		// nothing to do
	}
}
