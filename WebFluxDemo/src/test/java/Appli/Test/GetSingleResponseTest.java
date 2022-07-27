package Appli.Test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import Appli.DTO.Response;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class GetSingleResponseTest extends BaseTest{
	
	@Autowired
	private WebClient webClient;
	
	@Test
	public void blockTest() {
		Response response = this.webClient
			.get()
			.uri("reactive-math/square/{input}",5)
			.retrieve()
			.bodyToMono(Response.class) // we get Mono of Response
			.block();
		
		System.out.println(response);
	}
	
	@Test
	public void stepVerifierTest() {
		Mono<Response> responseMono = this.webClient
			.get()
			.uri("reactive-math/square/{input}",5)
			.retrieve()
			.bodyToMono(Response.class); // we get Mono of Response
		
		// stepverifier is used to verify you result of above mono
		StepVerifier.create(responseMono)
					.expectNextMatches(r -> r.getOutput() == 25)
					.verifyComplete();
	}
	
}
