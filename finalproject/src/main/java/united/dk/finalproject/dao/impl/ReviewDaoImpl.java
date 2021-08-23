package united.dk.finalproject.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;


import united.dk.finalproject.dao.ReviewDao;
import united.dk.finalproject.entity.Product;
import united.dk.finalproject.entity.Review;
import united.dk.finalproject.model.SearchReviewDTO;

@Transactional
@Repository
public class ReviewDaoImpl implements ReviewDao {
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void add(Review review) {
		// TODO Auto-generated method stub
		entityManager.persist(review);
	}

	@Override
	public void delete(Review review) {
		// TODO Auto-generated method stub
		entityManager.merge(review);
	}

	@Override
	public void edit(Review review) {
		// TODO Auto-generated method stub
		entityManager.remove(review);
	}

	@Override
	public Review getById(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find(Review.class, id);
	}

	@Override
	public List<Review> find(Long productId) {
		String jql="select r from Review r join r.user u join r.product p where p.id =:pid";
		return entityManager.createQuery(jql, Review.class).setParameter("pid",productId ).getResultList();
	}

	@Override
	public Long count(SearchReviewDTO searchReviewDTO) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Review> root = criteriaQuery.from(Review.class);

		List<Predicate> predicates = new ArrayList<Predicate>();


		if (searchReviewDTO.getProductId() != null) {
			Join<Review, Product> product = root.join("product");
			predicates.add(criteriaBuilder.equal(product.get("id"), searchReviewDTO.getProductId()));
		}

		criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(root)));
		return typedQuery.getSingleResult();
	}

	@Override
	public Long coutTotal(SearchReviewDTO searchReviewDTO) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Review> root = criteriaQuery.from(Review.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(root)));
		return typedQuery.getSingleResult();
	}
}
