package FluxExample;

import java.time.Duration;
import java.util.stream.Stream;

import CourseUtil.Util;
import reactor.core.publisher.Flux;

public class HotPublisherCache {
	public static Stream<String> movie(){
		System.out.println("Movie Started...");
		return Stream.of("Scene -> 1",
					"Scene -> 2","Scene -> 3",
					"Scene -> 4","Scene -> 5");
	}
	public static void main(String[] args) {
		Flux<String> movieStreams = Flux.fromStream(() -> movie())
										.delayElements(Duration.ofSeconds(1))
//										.cache();  // it store all event
										.cache(2); // it store last 2 event
		movieStreams.subscribe(Util.subscriber("Sam"));
		Util.sleepSecond(6); // without this sleep main thread will finish the program
		System.out.println("Mike going to join...");
		movieStreams.subscribe(Util.subscriber("John"));
		Util.sleepSecond(10);
	}
}
