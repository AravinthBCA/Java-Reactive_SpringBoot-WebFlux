package FluxExample;

import java.time.Duration;
import java.util.stream.Stream;

import CourseUtil.Util;
import reactor.core.publisher.Flux;

public class HotPublisherRefCount {
	// Movie Theater
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
										.refCount(1); // 2 means when two subscriber subscribe the 
//publish event then only movie starts otherwise wait , you can decide how many subscriber subscribe
//the event then only the event starts.
// suppose refcount 1 means some scenario it works like cold publisher
			
		movieStreams.subscribe(Util.subscriber("Sam"));
			
		Util.sleepSecond(5); // without this sleep main thread will finish the program
			
		movieStreams.subscribe(Util.subscriber("John"));
			
		Util.sleepSecond(8);
	}
}
