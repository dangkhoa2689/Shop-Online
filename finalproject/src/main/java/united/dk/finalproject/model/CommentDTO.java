package united.dk.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
	private Long id;
	private String commentDate;
	private String comment;
	private UserDTO userDTO;
	private ProductDTO productDTO;
}
