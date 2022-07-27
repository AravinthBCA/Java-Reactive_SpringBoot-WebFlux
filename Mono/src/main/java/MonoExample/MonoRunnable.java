package MonoExample;

import CourseUtil.Util;
import reactor.core.publisher.Mono;

public class MonoRunnable {
	
	private static Runnable timeConsumingProcess() {
		return () -> {
			Util.sleepSecond(3);
			System.out.println("Operation Completed...");
		};
	}
	
	public static void main(String[] args) {
		Runnable runnable = () -> System.out.println("");
		
		Mono.fromRunnable(timeConsumingProcess())
			.subscribe(Util.onNext(),
					Util.onError(),
					() -> {
						System.out.println("Process is done. Sending emails...");
					});			
	}
		
}
