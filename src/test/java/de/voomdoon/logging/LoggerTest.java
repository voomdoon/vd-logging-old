package de.voomdoon.logging;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import de.voomdoon.logging.logger.DefaultLoggerTest;
import de.voomdoon.logging.root.RootLogger;

/**
 * Abstract test class for {@link Logger}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public abstract class LoggerTest {

	/**
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	private static class TestRootLogger implements RootLogger {

		/**
		 * @since 0.1.0
		 */
		@Override
		public void addLogEventHandler(LogEventHandler handler) {
			throw new UnsupportedOperationException("'addLogEventHandler' not implemented at 'TestRootLogger'!");
		}

		@Override
		public Set<String> getLogEventHanderNames() {
			throw new UnsupportedOperationException(
					"Method 'getLogEventHanderNames' not implemented at 'TestRootLogger'!");
		}

		/**
		 * @since 0.1.0
		 */
		@Override
		public void log(LogEvent logEvent) {
			EVENTS.add(logEvent);
		}

		/**
		 * @since 0.1.0
		 */
		@Override
		public void removeLogEventHandler(LogEventHandler handler) {
			throw new UnsupportedOperationException("'removeLogEventHandler' not implemented at 'TestRootLogger'!");
		}
	}

	/**
	 * @since 0.1.0
	 */
	private static final List<LogEvent> EVENTS = new ArrayList<>();

	/**
	 * @since 0.1.0
	 */
	protected static TestRootLogger ROOT_LOGGER = new TestRootLogger();

	/**
	 * @since 0.1.0
	 */
	protected Throwable error = new Throwable();

	/**
	 * @since 0.1.0
	 */
	private Logger logger;

	/**
	 * @since 0.1.0
	 */
	protected Object message = new Object();

	/**
	 * @param logger
	 *            {@link Logger}
	 * @since 0.1.0
	 */
	public LoggerTest(Logger logger) {
		this.logger = logger;
	}

	/**
	 * @since 0.1.0
	 */
	@AfterEach
	void afterEach() {
		EVENTS.clear();
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testDebug_Object() throws Exception {
		logger.debug(message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.DEBUG);
		assertThat(EVENTS.get(0).getMessage()).isEqualTo(message);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testDebug_Object_LogEvent_getLevel() throws Exception {
		logger.debug(message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.DEBUG);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testDebug_Object_LogEvent_getSourceClass() throws Exception {
		logger.debug(message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getSourceClass()).isEqualTo(DefaultLoggerTest.class);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testDebug_Object_LogEvent_getTimestamp() throws Exception {
		long before = System.currentTimeMillis();
		logger.debug(message);
		long after = System.currentTimeMillis();

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getTimestamp()).isGreaterThanOrEqualTo(before).isLessThanOrEqualTo(after);
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testDebug_Object_Throwable() throws Exception {
		logger.debug(message, error);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.DEBUG);
		assertThat(EVENTS.get(0).getError()).isEqualTo(error);
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testError_Object() throws Exception {
		logger.error(message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.ERROR);
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testError_Object_Throwable() throws Exception {
		logger.error(message, error);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.ERROR);
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testFatal_Object() throws Exception {
		logger.fatal(message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.FATAL);
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testFatal_Object_Throwable() throws Exception {
		logger.fatal(message, error);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.FATAL);
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testInfo_Object() throws Exception {
		logger.info(message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.INFO);
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testInfo_Object_Throwable() throws Exception {
		logger.info(message, error);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.INFO);
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testIsActive() throws Exception {
		for (LogLevel level : LogLevel.values()) {
			logger.isActive(level);
		}
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testLog_LogLevel_Object() throws Exception {
		logger.log(LogLevel.INFO, message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.INFO);
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testLog_LogLevel_Object_Throwable() throws Exception {
		logger.log(LogLevel.INFO, message, error);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.INFO);
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testTrace_Object() throws Exception {
		logger.trace(message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.TRACE);
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testTrace_Object_Throwable() throws Exception {
		logger.trace(message, error);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.TRACE);
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testWarn_Object() throws Exception {
		logger.warn(message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.WARN);
	}

	/**
	 * @throws Exception
	 * @since 0.1.0
	 */
	@Test
	void testWarn_Object_Throwable() throws Exception {
		logger.warn(message, error);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.WARN);
	}
}
