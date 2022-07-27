package MonoExample;

import CourseUtil.Util;
import reactor.core.publisher.Mono;

public class MonoSubcribe {
	public static void main(String[] args) {
		Mono<String> mono = Mono.just("ball");
		
		Mono<Integer> mono1 = Mono.just("ball").map(i->i.length()/0); // it throw an erro / by zero
		
		mono.subscribe(); // nothing will return
		
		mono.subscribe(
				item -> System.out.println("Received : "+item),
				err  -> System.out.println("Error : "+err.getMessage()),
				()   -> System.out.println("Completed...")
		);
		System.out.println("----------------------------------------");
		mono1.subscribe(
				Util.onNext(),   // this Util class available in CourseUtil package 
				Util.onError(),
				Util.onComplete()
		);
	}
}
