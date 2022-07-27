package FluxExample;

import java.util.List;
import java.util.stream.Stream;

import CourseUtil.Util;
import reactor.core.publisher.Flux;

public class FluxStream {
	public static void main(String[] args) {
		List<Integer> list = List.of(1,2,3);
		Stream<Integer> stream = list.stream();
		
		// stream is used for one time operation you can't use more times
//		stream.forEach(System.out::println);
//		stream.forEach(System.out::println); // throw an error
		
		// this fromStream you can't use more times it throws error
		Flux<Integer> flux = Flux.fromStream(stream); 
		flux.subscribe(
			Util.onNext(),
			Util.onError(),
			Util.onComplete()
		);
		
		flux.subscribe(
			Util.onNext(),
			Util.onError(),
			Util.onComplete()
		);
		
		System.out.println("-------------------------------------------------------------");
		// solve this problem
		Flux<Integer> flux1 = Flux.fromStream(() -> list.stream()); 
		flux1.subscribe(
			Util.onNext(),
			Util.onError(),
			Util.onComplete()
		);
		
		flux1.subscribe(
			Util.onNext(),
			Util.onError(),
			Util.onComplete()
	   );
	}
}
