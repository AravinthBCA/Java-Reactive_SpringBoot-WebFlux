package MonoExample;

import java.util.stream.Stream;

public class StreamExample {
	public static void main(String[] args) {
		Stream<Integer> stream = Stream.of(1,3,5)
									.map(i -> {
										try {
											Thread.sleep(1000);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										return i*2;
									});
		stream.forEach(System.out::println);
	}
}
