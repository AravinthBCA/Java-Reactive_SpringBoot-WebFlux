package MonoExample;

import java.util.concurrent.CompletableFuture;

import CourseUtil.Util;
import reactor.core.publisher.Mono;

public class MonoFuture {
	
	private static CompletableFuture<String> getName(){
		return CompletableFuture.supplyAsync(() -> Util.faker().name().firstName());
	}
	
	public static void main(String[] args) {
		Mono.fromFuture(getName())
			.subscribe(Util.onNext());
				
//		Util.sleepSecond(1); // if not show the output only use this ( it block the main thread )
	}
}
