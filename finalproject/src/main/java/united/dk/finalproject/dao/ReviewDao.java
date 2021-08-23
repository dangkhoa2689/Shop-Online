package united.dk.finalproject.dao;

import java.util.List;

import united.dk.finalproject.entity.Review;
import united.dk.finalproject.model.SearchReviewDTO;


public interface ReviewDao {
	void add(Review review);

	void delete(Review review);

	void edit(Review review);

	Review getById(Long id);

	List<Review> find(Long productId);

	Long count(SearchReviewDTO searchReviewDTO);

	Long coutTotal(SearchReviewDTO searchReviewDTO);
}
