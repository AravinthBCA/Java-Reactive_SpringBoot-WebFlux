package FluxExample;

import java.util.concurrent.atomic.AtomicReference;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import CourseUtil.Util;
import reactor.core.publisher.Flux;

public class FluxSubscription {
	
	public static void main(String[] args) {
		AtomicReference<Subscription> atomicReference = new AtomicReference<>();
		Flux.range(1, 10)
			.log()
			.subscribeWith(new Subscriber<Integer>() {

				@Override
				public void onSubscribe(Subscription s) {
					System.out.println("Received Sub: "+s);
					atomicReference.set(s);
				}

				@Override
				public void onNext(Integer t) {
					System.out.println("OnNext : "+t);
				}

				@Override
				public void onError(Throwable t) {
					System.out.println("OnError : "+t);
				}

				@Override
				public void onComplete() {
					System.out.println("OnComplete...");
				}
			});
		Util.sleepSecond(3);
		atomicReference.get().request(3);
		Util.sleepSecond(3);
		atomicReference.get().request(3);
		Util.sleepSecond(3);
		System.out.println("Going to cancel...");
		atomicReference.get().cancel();
		atomicReference.get().request(3);
	}
}
