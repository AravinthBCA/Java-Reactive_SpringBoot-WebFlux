package FluxExample;

import CourseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxToMono {
	public static void main(String[] args) {
		
		Mono<Integer> mono = Mono.just(1);
		
		Flux<Integer> flux = Flux.from(mono); // Mono to Flux
		flux.subscribe( Util.onNext() );
		
		System.out.println("-------------------------------------------------");
		
		flux.range(1, 10)   // Flux to Mono
			.filter(i -> i>3)
			.next()            // next() allow one time only after that it goes onComplete method.
			.subscribe( Util.onNext(), Util.onError(), Util.onComplete());
		
	}
}
