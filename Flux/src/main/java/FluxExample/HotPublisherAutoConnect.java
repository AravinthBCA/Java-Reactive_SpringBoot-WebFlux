package FluxExample;

import java.time.Duration;
import java.util.stream.Stream;

import CourseUtil.Util;
import reactor.core.publisher.Flux;

public class HotPublisherAutoConnect {
	public static Stream<String> movie(){
		System.out.println("Movie Started...");
		return Stream.of("Scene -> 1",
					"Scene -> 2","Scene -> 3",
					"Scene -> 4","Scene -> 5");
	}
	public static void main(String[] args) {
		Flux<String> movieStreams = Flux.fromStream(() -> movie())
										.delayElements(Duration.ofSeconds(1))
										.publish()
										.autoConnect(0); 
		movieStreams.subscribe(Util.subscriber("Sam"));
		Util.sleepSecond(5); // without this sleep main thread will finish the program
		System.out.println("Mike going to join...");
		movieStreams.subscribe(Util.subscriber("John"));
		Util.sleepSecond(20);
	}
}
