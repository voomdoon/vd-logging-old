package de.voomdoon.logging;

/**
 * @since DOCME add inception version number
 */
class LoggingInternalUtil {

	/**
	 * @param row
	 * @param value
	 * @return
	 * @since DOCME add inception version number
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
	 * @since DOCME add inception version number
	 */
	static boolean has(String[] split, String value, String[] headline) {
		int index = getIndex(headline, value);

		return split.length > index && "true".equals(split[index]);
	}

	/**
	 * @return
	 * @since DOCME add inception version number
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
	 * @since DOCME add inception version number
	 */
	private LoggingInternalUtil() {
		// nothing to do
	}
}
