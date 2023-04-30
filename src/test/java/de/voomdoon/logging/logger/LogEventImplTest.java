package de.voomdoon.logging.logger;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import de.voomdoon.logging.LogLevel;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
class LogEventImplTest {

	/**
	 * DOCME add JavaDoc for LogEventImplTest
	 *
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	@Nested
	class ToStringTest {

		/**
		 * @since 0.1.0
		 */
		private LogEventImpl logEvent = new LogEventImpl(LogLevel.DEBUG, "test-message");

		/**
		 * DOCME add JavaDoc for method test
		 * 
		 * @since 0.1.0
		 */
		@Test
		void test_level() throws Exception {
			String actual = logEvent.toString();

			assertThat(actual).contains("level: DEBUG");
		}

		/**
		 * DOCME add JavaDoc for method test
		 * 
		 * @since 0.1.0
		 */
		@Test
		void test_message() throws Exception {
			String actual = logEvent.toString();

			assertThat(actual).contains("message: test-message");
		}
	}
}
