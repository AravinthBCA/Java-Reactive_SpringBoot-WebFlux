package UserService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UserService.Dto.UserDto;
import UserService.Service.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("all")
	public Flux<UserDto> all(){
		return this.userService.all();
	}
	
	@GetMapping("{id}")
	public Mono<UserDto> getUserById(@PathVariable int id){
		return this.userService.getUserById(id);
	}
	
	@PostMapping
	public Mono<UserDto> createUser(@RequestBody Mono<UserDto> userDto){
		System.out.println(userDto.toString());
		return this.userService.createUser(userDto);
	}
	
	@PutMapping("{id}")
	public Mono<ResponseEntity<UserDto>> updateUser(@PathVariable int id, @RequestBody Mono<UserDto> userDto){
		return this.userService.updateUser(id, userDto)
							   .map(ResponseEntity::ok)
							   .defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("{id}")
	public Mono<Void> deleteUser(@PathVariable int id){
		return this.userService.deleteUser(id);
	}
}







