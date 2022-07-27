package Appli.Assignment.RouterConfig;

import java.util.function.BiFunction;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import Appli.DTO.Response;
import reactor.core.publisher.Mono;

@Service
public class Calculator {
	
	public Mono<ServerResponse> addition(ServerRequest serverRequest){
		return process(serverRequest,(first,second) -> ServerResponse.ok().bodyValue(first + second));
	}
	
	public Mono<ServerResponse> subtraction(ServerRequest serverRequest){
		return process(serverRequest,(first,second) -> ServerResponse.ok().bodyValue(first - second));
	}
	
	public Mono<ServerResponse> multiplication(ServerRequest serverRequest){
		return process(serverRequest,(first,second) -> ServerResponse.ok().bodyValue(first * second));
	}
	
	public Mono<ServerResponse> division(ServerRequest serverRequest){
		return process(serverRequest,(first,second) -> {
			return second !=0 ? ServerResponse.ok().bodyValue(first / second):
					ServerResponse.badRequest().bodyValue("Second can not be 0");
		});
	}
	
	private Mono<ServerResponse> process(ServerRequest request, 
						BiFunction<Integer,Integer,Mono<ServerResponse>> opLogic) {
		int first = getValue(request,"first");
		int second = getValue(request,"second");
		return opLogic.apply(first, second);
	}
	
	private int getValue(ServerRequest request,String key) {
		return Integer.parseInt(request.pathVariable(key));
	}

}
