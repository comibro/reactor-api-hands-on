package io.pivotal.literx;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Learn how to create Flux instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html">Flux Javadoc</a>
 */
public class Part01Flux {

//========================================================================================

	Flux<String> emptyFlux() {
		return Flux.empty();
	}

//========================================================================================

	Flux<String> fooBarFluxFromValues() {
		return Flux.just("foo", "bar");
	}

//========================================================================================

	Flux<String> fooBarFluxFromList() {
		return Flux.fromIterable(List.of("foo", "bar"));
	}

//========================================================================================

	Flux<String> errorFlux() {
		return Flux.error(()->new IllegalStateException());
	}

//========================================================================================

	Flux<Long> counter() {
		AtomicLong atomicLong = new AtomicLong(0);
		return Flux.generate((SynchronousSink<Long> synchronousSink) -> synchronousSink.next(atomicLong.getAndIncrement()))
				   .timeout(Duration.ofMillis(100))
				   .takeUntil(n->n==9);
		}

}
