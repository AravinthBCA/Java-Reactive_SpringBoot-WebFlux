package FluxExample;

import java.util.Arrays;
import java.util.List;

import CourseUtil.Util;
import reactor.core.publisher.Flux;

public class FluxArrayorList {
	public static void main(String[] args) {
		
		List<String> strings = Arrays.asList("a","b","c");
		
		Integer[] arr = { 1,2,3};
		
		Flux.fromIterable(strings)
				.subscribe(Util.onNext());
		
		Flux.fromArray(arr)
		.subscribe(Util.onNext());
	}
}
