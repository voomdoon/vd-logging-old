package de.voomdoon.logging.root;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import de.voomdoon.logging.test.TestLogEvent;
import de.voomdoon.logging.test.TestLogEventHandler;

/**
 * Abstract class for {@link RootLogger}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public abstract class RootLoggerTest {

	/**
	 * @since 0.1.0
	 */
	private RootLogger rootLogger;

	/**
	 * @param rootLogger
	 *            {@link RootLogger}
	 * @since 0.1.0
	 */
	public RootLoggerTest(RootLogger rootLogger) {
		this.rootLogger = rootLogger;
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testAddLogEventHandler_same() throws Exception {
		TestLogEventHandler handler = new TestLogEventHandler();

		rootLogger.addLogEventHandler(handler);
		rootLogger.addLogEventHandler(handler);

		rootLogger.log(new TestLogEvent());

		assertThat(handler.getEvents()).hasSize(1);
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testGetLogEventHanderNames_className() throws Exception {
		TestLogEventHandler handler = new TestLogEventHandler();

		rootLogger.addLogEventHandler(handler);

		assertThat(rootLogger.getLogEventHanderNames()).contains(handler.getClass().getSimpleName());
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testGetLogEventHanderNames_empty() throws Exception {
		TestLogEventHandler handler = new TestLogEventHandler();

		rootLogger.addLogEventHandler(handler);
		rootLogger.removeLogEventHandler(handler);

		assertThat(rootLogger.getLogEventHanderNames()).isEmpty();
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testLog_LogEvent() throws Exception {
		rootLogger.log(new TestLogEvent());
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testRemoveLogEventHandler() throws Exception {
		TestLogEventHandler handler = new TestLogEventHandler();

		rootLogger.addLogEventHandler(handler);
		rootLogger.removeLogEventHandler(handler);

		rootLogger.log(new TestLogEvent());

		assertThat(handler.getEvents()).isEmpty();
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testRemoveLogEventHandler_error_NoSuchElementException() throws Exception {
		TestLogEventHandler handler = new TestLogEventHandler();

		assertThatThrownBy(() -> rootLogger.removeLogEventHandler(handler)).isInstanceOf(NoSuchElementException.class)
				.hasMessageContaining("handler");
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testRemoveLogEventHandler_error_NullPointerException() throws Exception {
		try {
			rootLogger.removeLogEventHandler(null);
			fail("Missing 'NullPointerException'!");
		} catch (NullPointerException e) {
			assertThat(e.getMessage()).contains("handler");
		}
	}
}
