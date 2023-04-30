package de.voomdoon.logging.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Supplier;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogEventHandler;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public class LoggingTestUtil {

	/**
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	public static class Out {

		/**
		 * @since 0.1.0
		 */
		private String err;

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

		/**
		 * @return err
		 * @since 0.1.0
		 */
		public String getErr() {
			return err;
		}

		/**
		 * @return out
		 * @since 0.1.0
		 */
		public String getOut() {
			return out;
		}
	}

	/**
	 * @param handlerSupplier
	 *            {@link Supplier} for {@link LogEventHandler}
	 * @param logEvent
	 *            {@link LogEvent}
	 * @return {@link String}
	 * @since 0.1.0
	 */
	public static Out runHandleLogEvent(Supplier<LogEventHandler> handlerSupplier, LogEvent logEvent) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		ByteArrayOutputStream err = new ByteArrayOutputStream();
		System.setErr(new PrintStream(err));

		handlerSupplier.get().handleLogEvent(logEvent);

		return new Out(out, err);
	}
}
