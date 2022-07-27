package Appli.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Appli.DTO.MultiplyRequest;
import Appli.DTO.Response;
import Appli.Service.ReactiveMathService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {
	
	@Autowired
	private ReactiveMathService reactiveMathService;
	
	@GetMapping("square/{input}")
	public Mono<Response> findSquare(@PathVariable int input) {
		return this.reactiveMathService.findSquare(input);
	}
	
	@GetMapping("table/{input}")
	public Flux<Response> multiplicationTable(@PathVariable int input) {
		
		return this.reactiveMathService.multiplicationTable(input);
	}
	
	@GetMapping(value="table/{input}/stream",produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Response> multiplicationTableStream(@PathVariable int input) {
		return this.reactiveMathService.multiplicationTable(input);
	}
	
	@PostMapping("multiply")
	public Mono<Response> multiply(@RequestBody Mono<MultiplyRequest> dto,
								   @RequestHeader Map<String, String> headers){
		System.out.println(headers);
		return this.reactiveMathService.multiply(dto);
	}
}
