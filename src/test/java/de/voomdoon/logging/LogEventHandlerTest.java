package de.voomdoon.logging;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import de.voomdoon.logging.test.TestLogEvent;

/**
 * Abstract test class for {@link LogEventHandler}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public abstract class LogEventHandlerTest {

	/**
	 * @since 0.1.0
	 */
	protected LogEventHandler handler;

	/**
	 * DOCME add JavaDoc for constructor LogEventHandlerTest
	 *
	 * @param handler
	 * @since 0.1.0
	 */
	public LogEventHandlerTest(LogEventHandler handler) {
		this.handler = handler;
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent() throws Exception {
		assertDoesNotThrow(
				() -> handler.handleLogEvent(new TestLogEvent().setLevel(LogLevel.INFO).setMessage("test-message")));
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent_error_NullPointerException_handler_null() throws Exception {
		assertThatThrownBy(() -> handler.handleLogEvent(null)).isInstanceOf(NullPointerException.class)
				.hasMessageContaining("logEvent");
	}
}
