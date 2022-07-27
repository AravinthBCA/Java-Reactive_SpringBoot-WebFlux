package Appli.Test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Assignment extends BaseTest{

	private static final String FORMAT="%d %s %d = %s";
	private static final int A = 10;
	
	@Autowired
	private WebClient webClient;
	
	// we know the A value is 10 and don't know B value and operator, every B value perform with all operator
	
	@Test
	public void test() {
		// B value is 1 to 5, first time 1 goes and perform with all operator ( +, -, *, / ) then next and continue like that
		Flux<String> flux = Flux.range(1, 5) 
								.flatMap(b -> Flux.just("+","-","*","/") 
												  .flatMap(op -> send(b,op)))
								.doOnNext(System.out::println);
		
		StepVerifier.create(flux)
					.expectNextCount(20)
					.verifyComplete();
	}
	
	private Mono<String> send(int b, String op) {
		return this.webClient
				   .get()
				   .uri("calc/{a}/{b}",A,b)
				   .headers(h -> h.set("OP", op))
				   .retrieve()
				   .bodyToMono(String.class)
				   .map(v -> String.format(FORMAT, A, op, b, v));
	}
}
