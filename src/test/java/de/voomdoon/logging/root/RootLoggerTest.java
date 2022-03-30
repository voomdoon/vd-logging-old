package de.voomdoon.logging.root;

import org.junit.jupiter.api.Test;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogLevel;

/**
 * Abstract class for {@link RootLogger}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public abstract class RootLoggerTest {

	/**
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	private static class TestLogEvent implements LogEvent {

		/**
		 * @since 0.1.0
		 */
		@Override
		public Throwable getError() {
			// TODO implement getError
			throw new UnsupportedOperationException("'getError' not implemented at 'LogEvent'!");
		}

		/**
		 * @since 0.1.0
		 */
		@Override
		public LogLevel getLevel() {
			// TODO implement getLevel
			throw new UnsupportedOperationException("'getLevel' not implemented at 'LogEvent'!");
		}

		/**
		 * @since 0.1.0
		 */
		@Override
		public Object getMessage() {
			// TODO implement getMessage
			throw new UnsupportedOperationException("'getMessage' not implemented at 'LogEvent'!");
		}

		/**
		 * @since 0.1.0
		 */
		@Override
		public Class<?> getSourceClass() {
			// TODO implement getSourceClass
			throw new UnsupportedOperationException("'getSourceClass' not implemented at 'LogEvent'!");
		}

		/**
		 * @since 0.1.0
		 */
		@Override
		public long getTimestamp() {
			// TODO implement getTimestamp
			throw new UnsupportedOperationException("'getTimestamp' not implemented at 'LogEvent'!");
		}
	}

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

	@Test
	void testLog_LogEvent() throws Exception {
		rootLogger.log(new TestLogEvent());
	}
}
