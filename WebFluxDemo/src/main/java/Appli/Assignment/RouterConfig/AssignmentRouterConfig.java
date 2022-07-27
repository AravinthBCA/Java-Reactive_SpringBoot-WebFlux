package Appli.Assignment.RouterConfig;

import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import Appli.DTO.InputValidationException;
import reactor.core.publisher.Mono;

@Configuration
public class AssignmentRouterConfig {
	
	@Autowired
	private Calculator requestHandler;
	
	@Bean
	public RouterFunction<ServerResponse> highLevelRouterAssign(){
		return RouterFunctions.route()
							  .path("calc",this::calcResponseFunction)
							  .build();
	}
	
	private RouterFunction<ServerResponse> calcResponseFunction(){
		return RouterFunctions.route()
							  .GET("{first}/{second}",isOperation("+"),requestHandler::addition)
							  .GET("{first}/{second}",isOperation("-"),requestHandler::subtraction)
							  .GET("{first}/{second}",isOperation("*"),requestHandler::multiplication)
							  .GET("{first}/{second}",isOperation("/"),requestHandler::division)
							  .GET("{first}/{second}",req -> ServerResponse.badRequest().bodyValue("OP should be + - / *"))
							  .build();
	}
	
	
	private RequestPredicate isOperation(String operation) {
		return RequestPredicates.headers( headers -> operation.equals(headers.asHttpHeaders()
															                 .toSingleValueMap()
															                 .get("OP")));
	}
}
