package UserService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UserService.Dto.TransactionRequestDto;
import UserService.Dto.TransactionResponseDto;
import UserService.Entity.UserTransaction;
import UserService.Service.TransactionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user/transaction")
public class TransactionController {

	@Autowired
	private TransactionService tService;
	
	@PostMapping
	public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> tDto){
		return tDto.flatMap(this.tService::createTransaction);
	}
	
	@GetMapping("{id}")
	public Flux<UserTransaction> getTransactionByUserId(@PathVariable int id){
		return this.tService.getUserTransactionById(id);
	}
}
