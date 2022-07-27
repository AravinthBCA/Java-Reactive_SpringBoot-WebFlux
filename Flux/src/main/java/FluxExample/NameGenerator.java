package FluxExample;

import java.util.ArrayList;
import java.util.List;

import CourseUtil.Util;
import reactor.core.publisher.Flux;

public class NameGenerator {
	
	public static List<String> listOfNames(int count){
		List<String> list = new ArrayList<>();
		for (int i = 0; i < count ; i++) {
			list.add(getName());
		}
		return list;
	}
	
	public static Flux<String> fluxOfNames(int count){
		return Flux.range(0, count)
					.map(i -> getName());
	}
	
	
	public static String getName() {
		Util.sleepSecond(1);
		return Util.faker().name().firstName();
	}
	
}
