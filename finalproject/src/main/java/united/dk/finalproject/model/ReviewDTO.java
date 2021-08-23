package united.dk.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
	private long id;
	private int starNumber;
	private String reviewDate;
	private UserDTO userDTO;
	private ProductDTO productDTO;
}
