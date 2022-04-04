package de.voomdoon.logging.handler;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.TimeZone;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogEventHandlerTest;
import de.voomdoon.logging.LogLevel;
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
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	public class Out {

		/**
		 * @since 0.1.0
		 */
		public String err;

		/**
		 * @since 0.1.0
		 */
		private String out;

		/**
		 * @param out
		 *            {@link ByteArrayOutputStream}
		 * @param err
		 *            {@link ByteArrayOutputStream}
		 * @since 0.1.0
		 */
		public Out(ByteArrayOutputStream out, ByteArrayOutputStream err) {
			this.out = new String(out.toByteArray());
			this.err = new String(err.toByteArray());
		}
	}

	/**
	 * @since 0.1.0
	 */
	private static TimeZone timeZoneOriginal;

	/**
	 * @since 0.1.0
	 */
	@AfterAll
	static void deinit() {
		TimeZone.setDefault(timeZoneOriginal);
	}

	/**
	 * @since 0.1.0
	 */
	@BeforeAll
	static void init() {
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
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent_error_class() throws Exception {
		Out out = runHandleLogEvent(new TestLogEvent().setError(new RuntimeException("test-error")));

		assertThat(out.out).contains("RuntimeException");
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent_error_message() throws Exception {
		Out out = runHandleLogEvent(new TestLogEvent().setError(new RuntimeException("test-error")));

		assertThat(out.out).contains("test-error");
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent_level() throws Exception {
		Out out = runHandleLogEvent(new TestLogEvent().setLevel(LogLevel.INFO));

		assertThat(out.out).contains("INFO");
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent_level_ERROR() throws Exception {
		Out out = runHandleLogEvent(new TestLogEvent().setLevel(LogLevel.ERROR));

		assertThat(out.err).contains("ERROR");
		assertThat(out.out).isEmpty();
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent_message_String() throws Exception {
		Out out = runHandleLogEvent(new TestLogEvent().setMessage("test-message"));

		assertThat(out.out).contains("test-message");
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent_sourceClass() throws Exception {
		Out out = runHandleLogEvent(new TestLogEvent().setSourceClass(ConsoleLogEventHandlerTest.class));

		assertThat(out.out).contains("ConsoleLogEventHandlerTest");
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent_timestamp() throws Exception {
		Out out = runHandleLogEvent(new TestLogEvent().setTimestamp(1641038400000L));

		assertThat(out.out).contains("2022-01-01 12:00:00.000");
	}

	/**
	 * @param logEvent
	 *            {@link LogEvent}
	 * @return {@link String}
	 * @since 0.1.0
	 */
	private Out runHandleLogEvent(LogEvent logEvent) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		ByteArrayOutputStream err = new ByteArrayOutputStream();
		System.setErr(new PrintStream(err));

		handler.handleLogEvent(logEvent);

		return new Out(out, err);
	}
}
