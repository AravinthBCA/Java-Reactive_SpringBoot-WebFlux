package Appli.RouterConfig;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import Appli.DTO.InputFailedValidationResponse;
import Appli.DTO.InputValidationException;
import Appli.DTO.MultiplyRequest;
import Appli.DTO.Response;
import Appli.Service.ReactiveMathService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RequestHandler {

	@Autowired
	private ReactiveMathService mathService;
	
	public Mono<ServerResponse> squareHandler(ServerRequest serverRequest){
		int input = Integer.parseInt(serverRequest.pathVariable("input"));
		Mono<Response> responseMono = this.mathService.findSquare(input);
	 // body used for publisher object and bodyvalue used for normal object...
		return ServerResponse.ok().body(responseMono, Response.class);
	}
	
	// get all events and send as a single list
	public Mono<ServerResponse> tableHandler(ServerRequest serverRequest){
		int input = Integer.parseInt(serverRequest.pathVariable("input"));
		Flux<Response> responseFlux = this.mathService.multiplicationTable(input);
		return ServerResponse.ok().body(responseFlux, Response.class);
	}
	
	// get every events and send as a stream 
	public Mono<ServerResponse> tableStreamHandler(ServerRequest serverRequest){
		int input = Integer.parseInt(serverRequest.pathVariable("input"));
		Flux<Response> responseFlux = this.mathService.multiplicationTable(input);
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(responseFlux, Response.class);
	}
	
	public Mono<ServerResponse> multiply(ServerRequest serverRequest){
		Mono<MultiplyRequest> requestMono = serverRequest.bodyToMono(MultiplyRequest.class);
		System.out.println(serverRequest.headers());
		Mono<Response> mono = this.mathService.multiply(requestMono);
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(mono, Response.class);
	}
	
	public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest serverRequest){
		int input = Integer.parseInt(serverRequest.pathVariable("input"));
		if(input < 10 || input > 20) {
			
			// way 1
//			InputValidationException obj = new InputValidationException(input);
//			InputFailedValidationResponse response = new InputFailedValidationResponse();
//			response.setErrorCode(obj.getErrorCode());
//			response.setMessage(obj.getMessage());
//			response.setInput(input);
//			return ServerResponse.badRequest().bodyValue(response);
			
			// or
			
			// way 2
			return Mono.error(new InputValidationException(input));
		}
		
		Mono<Response> responseMono = this.mathService.findSquare(input);
		return ServerResponse.ok().body(responseMono, Response.class);
	}
	
	
}
