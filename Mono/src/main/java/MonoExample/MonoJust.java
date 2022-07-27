package MonoExample;

import reactor.core.publisher.Mono;

public class MonoJust {
	public static void main(String[] args) {
		// use just only when you have data already
		Mono<Integer> just = Mono.just(1);
		System.out.println(just);
		just.subscribe(i -> System.out.println("Received : "+i));
	}
}
