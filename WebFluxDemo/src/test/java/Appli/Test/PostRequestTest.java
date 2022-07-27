package Appli.Test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import Appli.DTO.MultiplyRequest;
import Appli.DTO.Response;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class PostRequestTest extends BaseTest{
	
	@Autowired
	private WebClient webClient;

	@Test
	public void postTest() {
		Mono<Response> responseMono = this.webClient
										  .post()
										  .uri("reactive-math/multiply")
										  .bodyValue(buildRequestDto(5,2))
										  .retrieve()
										  .bodyToMono(Response.class)
										  .doOnNext(System.out::println);
		StepVerifier.create(responseMono)
			        .expectNextCount(1)
			        .verifyComplete();
	}
	
	private MultiplyRequest buildRequestDto(int a, int b) {
		MultiplyRequest dto = new MultiplyRequest();
		dto.setFirst(a);
		dto.setSecond(b);
		return dto;
	}
}
