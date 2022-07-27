package FluxExample;

import CourseUtil.Util;
import reactor.core.publisher.Flux;

public class FluxJust {
	public static void main(String[] args) {
		Flux<Object> flux = Flux.just(1,2,3.10,"aravinth",Util.faker().name().firstName());
		
		// below line throw null error
		Flux<Object> flux1 = Flux.just(1,20.10,null,"aravinth",Util.faker().name().firstName());
		
		flux.subscribe( // Util class used from Mono Project, CourseUtil package
				Util.onNext(),
				Util.onError(),
				Util.onComplete()
				);
		System.out.println("--------------------------------------------------------");
		flux1.subscribe( // Util class used from Mono Project, CourseUtil package
				Util.onNext(),
				Util.onError(),
				Util.onComplete()
				);
	}
}
