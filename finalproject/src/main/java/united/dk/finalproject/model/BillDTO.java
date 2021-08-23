package united.dk.finalproject.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {
	private Long id;
	private UserDTO user;
	private String buyDate;
	private Long priceTotal;
	private Integer discountPercent;
	private String status;
	private String pay;
}
