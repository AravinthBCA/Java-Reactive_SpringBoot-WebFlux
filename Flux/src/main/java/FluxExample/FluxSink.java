package FluxExample;

import CourseUtil.Util;
import reactor.core.publisher.Flux;

public class FluxSink {
	public static void main(String[] args) {
		Flux.range(1,10)
			.handle((integer,synchornousSink) -> {
				if(integer == 7)
					synchornousSink.complete();
				else
					synchornousSink.next(integer);
			}).subscribe(Util.subscriber());
	}
}
