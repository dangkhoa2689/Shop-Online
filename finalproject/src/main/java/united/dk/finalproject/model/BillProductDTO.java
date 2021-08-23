package united.dk.finalproject.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillProductDTO {
	private Long id;
	private long unitPrice;
	private int quantity;
	private BillDTO bill;
	private ProductDTO product;
}
