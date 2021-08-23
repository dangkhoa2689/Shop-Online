package united.dk.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private long id;
	private String name;
	private long price;
	private long quantity;
	private String description;
	private String image;
	private CategoryDTO category;
	private TradeMarkDTO tradeMark;
	private SizeDTO size;
	private SexDTO sex;
	
	
}
