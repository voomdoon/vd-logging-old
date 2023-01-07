package de.voomdoon.logging.logger;

import de.voomdoon.logging.LoggerTest;

/**
 * {@link LoggerTest} for {@link DefaultLogger}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public class DefaultLoggerTest extends LoggerTest {

	/**
	 * DOCME add JavaDoc for constructor DefaultLoggerTest
	 *
	 * @since 0.1.0
	 */
	public DefaultLoggerTest() {
		super(new DefaultLogger(ROOT_LOGGER, DefaultLoggerTest.class));
	}
}
