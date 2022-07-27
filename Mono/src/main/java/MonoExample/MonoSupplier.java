package MonoExample;

import CourseUtil.Util;
import reactor.core.publisher.Mono;

public class MonoSupplier {
	
	private static String getName() {
		System.out.println("Generating name...");
		return Util.faker().name().fullName();
	}
	
	public static void main(String[] args) {
		// use just only when you have data already
		// without the supplier invoke the publisher method it automatically call the getName
		// method and get the data and store it...
		Mono<String> mono = Mono.just(getName());
		
		// when supplier invoke the publisher then only it call getName method and get the data 
		// and send to subscribe...
		Mono<String> supplier = Mono.fromSupplier(() -> getName());
		supplier.subscribe(
				Util.onNext()
				);
	}
}
