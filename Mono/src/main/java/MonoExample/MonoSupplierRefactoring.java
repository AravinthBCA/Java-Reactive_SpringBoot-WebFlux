package MonoExample;

import CourseUtil.Util;
import reactor.core.publisher.Mono;

public class MonoSupplierRefactoring {

	private static Mono<String> getName(){
		System.out.println("Entered getName method...");
		return Mono.fromSupplier(() ->{  // this called pipeline
			System.out.println("Generating name...");
			Util.sleepSecond(3);
			return Util.faker().name().firstName();
		}).map(String::toUpperCase);
	}
	
	public static void main(String[] args) {
		getName(); // it build the pipeline
		getName().subscribe(Util.onNext()); //it executing the pipeline (execute the business logic)
		getName();
	}
	
}
