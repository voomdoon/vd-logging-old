package de.voomdoon.logging.handler;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.PrintStream;
import java.util.TimeZone;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.voomdoon.logging.LogEventHandlerTest;
import de.voomdoon.logging.LogLevel;
import de.voomdoon.logging.test.LoggingTestUtil;
import de.voomdoon.logging.test.LoggingTestUtil.Out;
import de.voomdoon.logging.test.TestLogEvent;

/**
 * {@link LogEventHandlerTest} for {@link ConsoleLogEventHandler}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
class ConsoleLogEventHandlerTest extends LogEventHandlerTest {

	/**
	 * @since 0.1.0
	 */
	private static TimeZone timeZoneOriginal;

	/**
	 * @since 0.1.0
	 */
	@AfterAll
	public static void deinit() {
		TimeZone.setDefault(timeZoneOriginal);
	}

	/**
	 * @since 0.1.0
	 */
	@BeforeAll
	public static void init() {
		timeZoneOriginal = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	/**
	 * @since 0.1.0
	 */
	private PrintStream outOriginal;

	/**
	 * @since 0.1.0
	 */
	public ConsoleLogEventHandlerTest() {
		super(new ConsoleLogEventHandler());
	}

	/**
	 * @since 0.1.0
	 */
	@BeforeEach
	void beforeEach() {
		outOriginal = System.out;
	}

	/**
	 * @since 0.1.0
	 */
	@AfterEach
	void afterEach() {
		System.setOut(outOriginal);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent_error_class() throws Exception {
		Out out = LoggingTestUtil.runHandleLogEvent(() -> handler,
				new TestLogEvent().setError(new RuntimeException("test-error")));

		assertThat(out.getOut()).contains("RuntimeException");
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent_error_message() throws Exception {
		Out out = LoggingTestUtil.runHandleLogEvent(() -> handler,
				new TestLogEvent().setError(new RuntimeException("test-error")));

		assertThat(out.getOut()).contains("test-error");
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent_level() throws Exception {
		Out out = LoggingTestUtil.runHandleLogEvent(() -> handler, new TestLogEvent().setLevel(LogLevel.INFO));

		assertThat(out.getOut()).contains("INFO");
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent_level_ERROR() throws Exception {
		Out out = LoggingTestUtil.runHandleLogEvent(() -> handler, new TestLogEvent().setLevel(LogLevel.ERROR));

		assertThat(out.getErr()).contains("ERROR");
		assertThat(out.getOut()).isEmpty();
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent_message_String() throws Exception {
		Out out = LoggingTestUtil.runHandleLogEvent(() -> handler, new TestLogEvent().setMessage("test-message"));

		assertThat(out.getOut()).contains("test-message");
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent_sourceClass() throws Exception {
		Out out = LoggingTestUtil.runHandleLogEvent(() -> handler,
				new TestLogEvent().setSourceClass(ConsoleLogEventHandlerTest.class));

		assertThat(out.getOut()).contains("ConsoleLogEventHandlerTest");
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent_timestamp() throws Exception {
		Out out = LoggingTestUtil.runHandleLogEvent(() -> handler, new TestLogEvent().setTimestamp(1641038400000L));

		assertThat(out.getOut()).contains("2022-01-01 12:00:00.000");
	}
}
