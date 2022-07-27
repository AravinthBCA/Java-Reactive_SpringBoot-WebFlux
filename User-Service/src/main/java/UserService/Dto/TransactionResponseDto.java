package UserService.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {

	private Integer userId;
	private Integer amount;
	private TransactionStatus status;
	
}
