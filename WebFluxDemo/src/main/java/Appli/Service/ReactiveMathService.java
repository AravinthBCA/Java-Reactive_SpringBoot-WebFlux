package Appli.Service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import Appli.DTO.MultiplyRequest;
import Appli.DTO.Response;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveMathService {
	
	public Mono<Response> findSquare(int input){
		return Mono.fromSupplier(() -> input * input)
				   .map(Response::new);
	}
	
	public Flux<Response> multiplicationTable(int input) {
		return Flux.range(1,10)
//				   .doOnNext(i -> SleepUtil.sleepSeconds(1)) // this is blocking sleep
				   .delayElements(Duration.ofSeconds(1)) // non-blocking sleep
				   .doOnNext(i -> System.out.println("Reactive-Math-Service processing: "+i))
				   .map(i -> new Response(i * input));
	}
	
	public Mono<Response> multiply(Mono<MultiplyRequest> dto){
		return dto.map(i -> i.getFirst() * i.getSecond())
				.map(Response::new);
	}
}
