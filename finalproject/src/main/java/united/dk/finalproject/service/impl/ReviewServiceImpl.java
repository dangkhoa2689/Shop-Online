package united.dk.finalproject.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import united.dk.finalproject.dao.ReviewDao;
import united.dk.finalproject.entity.Product;
import united.dk.finalproject.entity.Review;
import united.dk.finalproject.entity.User;
import united.dk.finalproject.model.ProductDTO;
import united.dk.finalproject.model.ReviewDTO;
import united.dk.finalproject.model.SearchReviewDTO;
import united.dk.finalproject.model.UserDTO;
import united.dk.finalproject.service.ReviewService;
import united.dk.finalproject.utils.DateTimeUtils;

@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDao reviewDao;

	@Override
	public void add(ReviewDTO reviewDTO) {
		// TODO Auto-generated method stub
		Review review = new Review();
		review.setReviewDate(new Date());
		review.setStarNumber(reviewDTO.getStarNumber());
		review.setProduct(new Product(reviewDTO.getProductDTO().getId()));
		User user = new User();
		user.setName(reviewDTO.getUserDTO().getName());
		user.setId(reviewDTO.getUserDTO().getId());
		review.setUser(user);
		reviewDao.add(review);

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Review review = reviewDao.getById(id);
		if (review != null) {
			reviewDao.delete(review);
		}
	}

	@Override
	public void edit(ReviewDTO reviewDTO) {
		// TODO Auto-generated method stub
		Review review = reviewDao.getById(reviewDTO.getId());
		if (review != null) {
			review.setStarNumber(reviewDTO.getStarNumber());
			review.setProduct(new Product(reviewDTO.getProductDTO().getId()));
			User user = new User();
			user.setName(reviewDTO.getUserDTO().getName());
			review.setUser(user);
		}
		reviewDao.edit(review);
	}

	@Override
	public ReviewDTO getById(Long id) {
		Review review = reviewDao.getById(id);
		if (review != null) {
			convert(review);
		}
		return null;
	}

	private ReviewDTO convert(Review review) {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setId(review.getId());
		reviewDTO.setReviewDate(DateTimeUtils.formatDate(review.getReviewDate(), DateTimeUtils.DD_MM_YYYY_HH_MM));
		reviewDTO.setStarNumber(review.getStarNumber());
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(review.getProduct().getId());
		reviewDTO.setProductDTO(productDTO);
		UserDTO userDTO = new UserDTO();
		userDTO.setName(review.getUser().getName());
		reviewDTO.setUserDTO(userDTO);
		return reviewDTO;

	}

	@Override
	public List<ReviewDTO> find(Long id) {
		List<Review> reviews = reviewDao.find(id);
		List<ReviewDTO> reviewDTOs = new ArrayList<ReviewDTO>();
		reviews.forEach(rev -> {
			reviewDTOs.add(convert(rev));
		});
		return reviewDTOs;
	}

	@Override
	public Long count(SearchReviewDTO searchReviewDTO) {
		return reviewDao.count(searchReviewDTO);
	}

	@Override
	public Long coutTotal(SearchReviewDTO searchReviewDTO) {
		return reviewDao.count(searchReviewDTO);
	}

}
