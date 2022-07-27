package Appli.Assignment.RouterConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import Appli.DTO.InputValidationException;
import Appli.DTO.Response;
import reactor.core.publisher.Mono;

// just for sample no use for learning only

@Service
public class AssignmentRequestHandler {
	
//	@Autowired
//	private Calculator cal;
//	
//	public Mono<ServerResponse> operation(ServerRequest serverRequest){
//		String headerValue = serverRequest.headers().firstHeader("OP");
//		int first = Integer.parseInt(serverRequest.pathVariable("first"));
//		int second = Integer.parseInt(serverRequest.pathVariable("second"));
//		if( first ==0 || second ==0 ) {
//			return Mono.error(new InputValidationException(first));
//		}
//		if(headerValue == null) {
//			return ServerResponse.badRequest().bodyValue("Not found any Header Key");
//		}
//		Mono<Response> responseMono = null;
//		switch(headerValue) {
//			case "+":
//				responseMono = cal.addition(first, second);
//				break;
//			case "-":
//				responseMono = cal.subtraction(first, second);
//				break;
//			case "*":
//				responseMono = cal.multiplication(first, second);
//				break;
//			case "/":
//				responseMono = cal.division(first, second);
//				break;
//			default:
//				return ServerResponse.badRequest().bodyValue("Invalid Operator or Header Value");
//		}
//		return ServerResponse.ok().body(responseMono,Response.class);
//	}
}
