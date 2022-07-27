package FluxExample;

import CourseUtil.Util;
import reactor.core.publisher.Flux;

public class FluxCreate {
	public static void main(String[] args) {
		Flux.create(fluxSink -> {
			fluxSink.next(1);
			fluxSink.next(2);
			fluxSink.complete();
		})
		.subscribe(Util.subscriber());
		System.out.println("------------------------------------------------------");
		Flux.create(fluxSink -> {
			String name = "";
			do {
				name = Util.faker().name().firstName();
				fluxSink.next(name);
			}while(name.charAt(0) != ('A'));
			fluxSink.complete();
		}).subscribe(Util.subscriber());
	}
}
