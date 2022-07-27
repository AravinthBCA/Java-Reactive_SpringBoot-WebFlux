package Appli.RouterConfig;

import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import Appli.DTO.InputFailedValidationResponse;
import Appli.DTO.InputValidationException;
import reactor.core.publisher.Mono;

@Configuration
public class RouterConfig {

	@Autowired
	private RequestHandler requestHandler;
	
// for example you hit this url ( localhost:8080/router/square/5 ) means it means below method 
// because it start router right then this method invoke ( serverResponseRouterFunction ) this method
// this will validate the remaining url if everything fine means it works otherwise 404 error. 
	@Bean
	public RouterFunction<ServerResponse> highLevelRouter(){
		return RouterFunctions.route()
							  .path("router", this::serverResponseRouterFunction)
							  .build();
	}
	
	
	private RouterFunction<ServerResponse> serverResponseRouterFunction(){
		return RouterFunctions.route()
								  // below first square url check your pathvariable value is 10 to 20 only it call squareHandler method otherwise goto next
// square url it send the badrequest 400 and error msg. Third square url not needed because error input already handled by second square url.
							  .GET("square/{input}",RequestPredicates.path("*/1?").or(RequestPredicates.path("*/20")),requestHandler::squareHandler) 
							  .GET("square/{input}",req -> ServerResponse.badRequest().bodyValue("only 10 to 19 allowed..."))
							  .GET("square/throw/{input}",requestHandler::squareHandlerWithValidation) 
							  .GET("table/{input}",requestHandler::tableHandler) // return list
							  .GET("table-stream/{input}",requestHandler::tableStreamHandler) //return stream
							  .POST("multiply",requestHandler::multiply)
							  .onError(InputValidationException.class, exceptionHandler())
							  .build();
	}
	
	// error handler function is common for all paths
	private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler(){
		return (err, req) -> {
			InputValidationException obj = (InputValidationException) err;
			InputFailedValidationResponse response = new InputFailedValidationResponse();
			response.setErrorCode(obj.getErrorCode());
			response.setMessage(obj.getMessage());
			response.setInput(obj.getInput());
			return ServerResponse.badRequest().bodyValue(response);
		};
	}
}
