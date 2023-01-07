package de.voomdoon.logging;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.voomdoon.logging.test.TestLogEventHandler;

/**
 * Test class for {@link LogManager}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
class LogManagerTest {

	/**
	 * Test method for {@link LogManager#addLogEventHandler(LogEventHandler)}.
	 *
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	static class AddLogEventHandler {

		/**
		 * @throws Exception
		 * @since 0.1.0
		 */
		@Test
		void test_error_NullPointerException_handler_null() throws Exception {
			assertThatThrownBy(() -> LogManager.addLogEventHandler(null)).isInstanceOf(NullPointerException.class)
					.hasMessageContaining("handler");
		}

		/**
		 * @throws Exception
		 * @since 0.1.0
		 */
		@Test
		void test_one() throws Exception {
			TestLogEventHandler handler = new TestLogEventHandler();

			LogManager.addLogEventHandler(handler);

			Logger logger = LogManager.getLogger(getClass());
			logger.info("test");

			assertThat(handler.getEvents()).hasSize(1);
		}

		/**
		 * @throws Exception
		 * @since 0.1.0
		 */
		@Test
		void test_same() throws Exception {
			TestLogEventHandler handler = new TestLogEventHandler();

			LogManager.addLogEventHandler(handler);
			LogManager.addLogEventHandler(handler);

			Logger logger = LogManager.getLogger(getClass());
			logger.info("test");

			assertThat(handler.getEvents()).hasSize(1);
		}

		/**
		 * @throws Exception
		 * @since 0.1.0
		 */
		@Test
		void test_two() throws Exception {
			TestLogEventHandler handler1 = new TestLogEventHandler();
			TestLogEventHandler handler2 = new TestLogEventHandler();

			LogManager.addLogEventHandler(handler1);
			LogManager.addLogEventHandler(handler2);

			Logger logger = LogManager.getLogger(getClass());
			logger.info("test");

			assertThat(handler1.getEvents()).hasSize(1);
			assertThat(handler2.getEvents()).hasSize(1);
		}
	}

	/**
	 * Test class for {@link LogManager#getLogger(Class)}.
	 *
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	static class GetLogger_Class_Test {

		/**
		 * @since 0.1.0
		 */
		private PrintStream outOriginal;

		/**
		 * @since 0.1.0
		 */
		@BeforeEach
		void setUp() {
			outOriginal = System.out;
		}

		/**
		 * @since 0.1.0
		 */
		@AfterEach
		void tearDown() {
			System.setOut(outOriginal);
		}

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
		void test_logToConsole() throws Exception {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			System.setOut(new PrintStream(out));

			Logger logger = LogManager.getLogger(getClass());
			logger.info("test-message");

			assertThat(new String(out.toByteArray())).contains("test-message");
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

	/**
	 * Test method for {@link LogManager#removeLogEventHandler(LogEventHandler)}.
	 *
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	static class RemoveLogEventHandler {

		/**
		 * @throws Exception
		 * @since 0.1.0
		 */
		@Test
		void test() throws Exception {
			TestLogEventHandler handler = new TestLogEventHandler();

			LogManager.addLogEventHandler(handler);
			LogManager.removeLogEventHandler(handler);

			Logger logger = LogManager.getLogger(getClass());
			logger.info("test");

			assertThat(handler.getEvents()).isEmpty();
		}

		/**
		 * @throws Exception
		 * @since 0.1.0
		 */
		@Test
		void test_error_NoSuchElementException() throws Exception {
			TestLogEventHandler handler = new TestLogEventHandler();

			assertThatThrownBy(() -> LogManager.removeLogEventHandler(handler))
					.isInstanceOf(NoSuchElementException.class).hasMessageContaining("handler");
		}

		/**
		 * @throws Exception
		 * @since 0.1.0
		 */
		@Test
		void test_error_NullPointerException() throws Exception {
			assertThatThrownBy(() -> LogManager.removeLogEventHandler(null)).isInstanceOf(NullPointerException.class)
					.hasMessageContaining("handler");
		}
	}

	/**
	 * DOCME add JavaDoc for method test_initialization
	 *
	 * @throws Exception
	 * @since DOCME add inception version number
	 */
	@Test
	void test_initialization_addHandlers() throws Exception {
		LogManager.getLogger(getClass()).info("test-message");

		assertThat(TestLogEventHandler.getInstances()).isNotEmpty();
	}
}
