package FluxExample;

import java.time.Duration;

import CourseUtil.Util;
import reactor.core.publisher.Flux;

public class FluxInterval {
	public static void main(String[] args) {
		Flux.interval(Duration.ofSeconds(1))
			.subscribe(Util.onNext());
		
		Util.sleepSecond(5);
	}
}
