package de.voomdoon.logging;

/**
 * @since 0.1.0
 */
class LoggingInternalUtil {

	/**
	 * @param row
	 * @param value
	 * @return
	 * @since 0.1.0
	 */
	private static int getIndex(String[] row, String value) {
		for (int i = 0; i < row.length; i++) {
			if (value.equals(row[i])) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * @param split
	 * @param value
	 * @param headline
	 * @return
	 * @since 0.1.0
	 */
	static boolean has(String[] split, String value, String[] headline) {
		int index = getIndex(headline, value);

		return split.length > index && "true".equals(split[index]);
	}

	/**
	 * @return
	 * @since 0.1.0
	 */
	static boolean isAtTest() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

		for (StackTraceElement element : stackTrace) {
			if (element.getClassName().startsWith("org.junit.platform.launcher.core")) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @since 0.1.0
	 */
	private LoggingInternalUtil() {
		// nothing to do
	}
}
