package FluxExample;

import CourseUtil.Util;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

public class FluxRange {
	public static void main(String[] args) {
		Flux.range(1, 5)
			.subscribe( Util.onNext() );
		
		Flux.range(1, 10).map(i -> Util.faker().name().firstName())
			.subscribe(
					Util.onNext()
			);
	}
}
