package de.voomdoon.logging;

/**
 * @author André Schulz
 *
 * @since 0.1.0
 */
public enum LogLevel {

	/**
	 * @since 0.1.0
	 */
	DEBUG(1),

	/**
	 * @since 0.1.0
	 */
	ERROR(4),

	/**
	 * @since 0.1.0
	 */
	FATAL(5),

	/**
	 * @since 0.1.0
	 */
	INFO(2),

	/**
	 * @since 0.1.0
	 */
	TRACE(0),

	/**
	 * @since 0.1.0
	 */
	WARN(3)

	;

	/**
	 * @since 0.1.0
	 */
	private int priority;

	/**
	 * @param priority
	 * @since 0.1.0
	 */
	private LogLevel(int order) {
		this.priority = order;
	}

	/**
	 * @return priority
	 * @since 0.1.0
	 */
	public int getPriority() {
		return priority;
	}
}
