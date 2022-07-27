package FluxExample;

import CourseUtil.Util;
import reactor.core.publisher.Flux;

public class FluxSubscriber {
	public static void main(String[] args) {
		Flux<Integer> just = Flux.just(1,2,3);
		
		just.subscribe(
				Util.onNext()
		);
		
		just.subscribe(
				i -> System.out.println("Sub 2 : "+i)
		);
	}
}
