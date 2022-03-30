package de.voomdoon.logging;

import java.util.WeakHashMap;

import de.voomdoon.logging.logger.DefaultLogger;
import de.voomdoon.logging.root.RootLogger;

/**
 * DOCME add JavaDoc for
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
		ROOT_LOGGER = null;
	}

	/**
	 * DOCME add JavaDoc for method getLogger
	 * 
	 * @param clazz
	 *            {@link Class}
	 * @return {@link Logger}
	 * @since 0.1.0
	 */
	public static Logger getLogger(Class<?> clazz) {
		return CLASS_LOGGERS.computeIfAbsent(clazz, c -> new DefaultLogger(ROOT_LOGGER, clazz));
	}

	/**
	 * @since 0.1.0
	 */
	private LogManager() {
		// nothing to do
	}
}
