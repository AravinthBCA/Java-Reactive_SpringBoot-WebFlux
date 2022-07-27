package Appli.Test;

import java.net.URI;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class QueryParamsTest extends BaseTest{
	
	@Autowired
	private WebClient webClient;
	
	String queryString = "http://localhost:8080/jobs/search?count={count}&page={page}";
	
	@Test
	public void queryParamsTest() {
		URI uri = UriComponentsBuilder.fromUriString(queryString)
							.build(10,20);
		
		Flux<Integer> flux = this.webClient.get()
					  					   .uri(uri)
					  					   .retrieve()
					  					   .bodyToFlux(Integer.class)
					  					   .doOnNext(System.out::println);
		
		StepVerifier.create(flux)
					.expectNextCount(2)
					.verifyComplete();
							
	}
	
	@Test
	public void queryParamsTest1() {
		
		Map<String,Integer> map = Map.of("count",10,"page",20);
		
		Flux<Integer> flux = this.webClient.get()
//					  					   .uri(b -> b.path("jobs/search").query("count={count}&page={page}").build(10,20))
										   .uri(b -> b.path("jobs/search").query("count={count}&page={page}").build(map))
					  					   .retrieve()
					  					   .bodyToFlux(Integer.class)
					  					   .doOnNext(System.out::println);
		
		StepVerifier.create(flux)
					.expectNextCount(2)
					.verifyComplete();
							
	}
	
}
