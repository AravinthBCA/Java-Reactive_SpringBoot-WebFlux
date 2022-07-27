package UserService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UserService.Dto.UserDto;
import UserService.Repository.UserRepository;
import UserService.Util.EntityDtoUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public Flux<UserDto> all(){
		return this.userRepo.findAll()
					   .map(EntityDtoUtil::toDto);
	}
	
	public Mono<UserDto> getUserById(final int userId){
		return this.userRepo.findById(userId)
							.map(EntityDtoUtil::toDto);
	}
	
	public Mono<UserDto> createUser(Mono<UserDto> userDtoMono){
		return userDtoMono.map(EntityDtoUtil::toEntity)
						  .flatMap(this.userRepo::save)
						  .map(EntityDtoUtil::toDto);
	}
	
	public Mono<UserDto> updateUser(int id, Mono<UserDto> userDto){
		return this.userRepo.findById(id)
							.flatMap(u -> userDto.map(EntityDtoUtil::toEntity)
							 			  		     .doOnNext(e -> e.setId(id)))
							.flatMap(this.userRepo::save)
					 		.map(EntityDtoUtil::toDto);
	}
	
	public Mono<Void> deleteUser(int id) {
		return this.userRepo.deleteById(id);
	}
	
}
