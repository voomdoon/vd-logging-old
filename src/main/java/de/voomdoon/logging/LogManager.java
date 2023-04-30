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

		new LogEventHandlersInitializer(this, rootLogger).initialize();
	}

	/**
	 * DOCME
	 *
	 * @param handler
	 * @since 0.1.0
	 */
	void addLogEventHandlerInternal(LogEventHandler handler) {
		System.out.println("LogManager: addLogEventHandler " + handler);
		rootLogger.addLogEventHandler(handler);
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

	private void logInitialization() {
		getLogger(getClass())
				.debug("initialized logging with " + rootLogger.getLogEventHanderNames().stream().sorted().toList());
	}

	/**
	 * DOCME
	 *
	 * @param handler
	 * @since 0.1.0
	 */
	private void removeLogEventHandlerInternal(LogEventHandler handler) {
		rootLogger.removeLogEventHandler(handler);
	}
}
