package FluxExample;

import CourseUtil.Util;
import reactor.core.publisher.Flux;

public class FluxGenerateSink {
	public static void main(String[] args) {
		Flux.generate(synchronousSink -> synchronousSink.next(Util.faker().name().firstName()))
			.map(Object::toString) // convert Object into String
			.handle((s,synchronousSink) -> {
				synchronousSink.next(s);
				if(s.charAt(0)== 'A')
					synchronousSink.complete();
			})
			.subscribe(Util.subscriber());
	}
}
