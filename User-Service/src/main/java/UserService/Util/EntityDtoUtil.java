package UserService.Util;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import UserService.Dto.TransactionRequestDto;
import UserService.Dto.TransactionResponseDto;
import UserService.Dto.TransactionStatus;
import UserService.Dto.UserDto;
import UserService.Entity.User;
import UserService.Entity.UserTransaction;

public class EntityDtoUtil {
	
	public static UserDto toDto(User user) {
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(user, dto);
		return dto;
	}
	
	public static User toEntity(UserDto dto) {
		User user = new User();
		BeanUtils.copyProperties(dto,user);
		return user;
	}
	
	public static UserTransaction toEntity(TransactionRequestDto tDto) {
		UserTransaction ut = new UserTransaction();
		ut.setUserId(tDto.getUserId());
		ut.setAmount(tDto.getAmount());
		ut.setTransactionDate(LocalDateTime.now());
		return ut;
	}
	
	public static TransactionResponseDto toDto(TransactionRequestDto tDto,TransactionStatus tStatus) {
		TransactionResponseDto dto = new TransactionResponseDto();
		dto.setAmount(tDto.getAmount());
		dto.setUserId(tDto.getUserId());
		dto.setStatus(tStatus);
		return dto;
	}
	
}
