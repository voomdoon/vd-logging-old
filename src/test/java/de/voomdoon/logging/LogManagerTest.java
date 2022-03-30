package de.voomdoon.logging;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link LogManager}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public class LogManagerTest {

	/**
	 * Test class for {@link LogManager#getLogger(Class)}.
	 *
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	static class GetLogger_Class_Test {

		/**
		 * @throws Exception
		 * @since 0.1.0
		 */
		@Test
		void test() throws Exception {
			Logger actual = LogManager.getLogger(LogManagerTest.class);

			assertThat(actual).isNotNull();
		}

		/**
		 * @throws Exception
		 * @since 0.1.0
		 */
		@Test
		void test_sameInstance() throws Exception {
			Logger actual1 = LogManager.getLogger(LogManagerTest.class);
			Logger actual2 = LogManager.getLogger(LogManagerTest.class);

			assertThat(actual2).isSameAs(actual1);
		}
	}
}
