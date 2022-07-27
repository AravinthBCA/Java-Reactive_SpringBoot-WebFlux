package Appli.Test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import Appli.DTO.Response;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class GetMultiResponseTest extends BaseTest{

	@Autowired
	private WebClient webClient;
	
	@Test
	public void fluxTest() {
		Flux<Response> responseFlux = this.webClient
										  .get()
										  .uri("reactive-math/table/{input}",5)
									      .retrieve()
										  .bodyToFlux(Response.class) 
										  .doOnNext(System.out::println);
		
		// stepverifier is used to verify you result of above flux
		StepVerifier.create(responseFlux)
					.expectNextCount(10)
					.verifyComplete();
	}
	
	@Test
	public void fluxStreamTest() {
		Flux<Response> responseFlux = this.webClient
										  .get()
										  .uri("reactive-math/table/{input}/stream",5)
									      .retrieve()
										  .bodyToFlux(Response.class) 
										  .doOnNext(System.out::println);
		
		// stepverifier is used to verify you result of above flux
		StepVerifier.create(responseFlux)
					.expectNextCount(10)
					.verifyComplete();
	}
	
}
