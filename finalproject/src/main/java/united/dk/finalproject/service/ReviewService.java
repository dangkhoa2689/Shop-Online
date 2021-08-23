package united.dk.finalproject.service;

import java.util.List;


import united.dk.finalproject.model.ReviewDTO;
import united.dk.finalproject.model.SearchReviewDTO;

public interface ReviewService {
	void add(ReviewDTO reviewDTO);

	void delete(Long id);

	void edit(ReviewDTO reviewDTO);

	ReviewDTO getById(Long id);

	List<ReviewDTO> find(Long id);

	Long count(SearchReviewDTO searchReviewDTO);

	Long coutTotal(SearchReviewDTO searchReviewDTO);
}
