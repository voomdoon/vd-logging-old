package de.voomdoon.logging;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link LogLevel}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
class LogLevelTest {

	/**
	 * @since 0.1.0
	 */
	@Test
	void testGetPriority_relevance() {
		assertThat(LogLevel.ERROR.getPriority()).isGreaterThan(LogLevel.DEBUG.getPriority());
	}

	/**
	 * Test method for {@link LogLevel#getPriority()} to check for unique values.
	 *
	 * @since 0.1.0
	 */
	@Test
	void testGetPriority_unique() {
		Map<Integer, AtomicInteger> counts = new HashMap<>();

		for (LogLevel logLevel : LogLevel.values()) {
			counts.computeIfAbsent(logLevel.getPriority(), p -> new AtomicInteger()).incrementAndGet();
		}

		assertThat(counts).hasSameSizeAs(LogLevel.values());

		for (Entry<Integer, AtomicInteger> entry : counts.entrySet()) {
			assertThat(entry.getValue().get()).as("#priority@" + entry.getKey()).isEqualTo(1);
		}
	}
}
