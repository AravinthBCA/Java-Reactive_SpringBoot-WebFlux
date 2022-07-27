package UserService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UserService.Dto.TransactionRequestDto;
import UserService.Dto.TransactionResponseDto;
import UserService.Dto.TransactionStatus;
import UserService.Entity.UserTransaction;
import UserService.Repository.UserRepository;
import UserService.Repository.UserTransactionRepository;
import UserService.Util.EntityDtoUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserTransactionRepository userTRepo;
	
	public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto requestDto){
		return this.userRepo.updateUserBalance(requestDto.getUserId(), requestDto.getAmount())
					 		.filter(Boolean::booleanValue)
					 		.map(b -> EntityDtoUtil.toEntity(requestDto))
					 		.flatMap(this.userTRepo::save)
					 		.map(ut -> EntityDtoUtil.toDto(requestDto, TransactionStatus.APPROVED))
					 		.defaultIfEmpty(EntityDtoUtil.toDto(requestDto, TransactionStatus.DECLINED));
	}
	
	public Flux<UserTransaction> getUserTransactionById(int id){
		return this.userTRepo.findByUserId(id);
	}
	
}
