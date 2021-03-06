package united.dk.finalproject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import united.dk.finalproject.dao.CategoryDao;
import united.dk.finalproject.entity.Category;
import united.dk.finalproject.entity.User;
@Transactional
@Repository
public class CategoryDaoImpl implements CategoryDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insert(Category category) {
		// TODO Auto-generated method stub
		entityManager.persist(category);
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		entityManager.merge(category);
	}

	@Override
	public void delete(Category category) {
		// TODO Auto-generated method stub
		entityManager.remove(category);
	}

	@Override
	public Category get(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find(Category.class, id);
	}

	@Override
	public List<Category> search(String findName) {
		String jql = "SELECT c FROM Category c WHERE c.name =  :name";
		return entityManager.createQuery(jql, Category.class).setParameter("name", "%" + findName + "%").getResultList();
	}

	@Override
	public List<Category> search(String findName, int start, int length) {
		String jql = "select c from Category c where c.name like :name";
		return entityManager.createQuery(jql, Category.class).setParameter("name", "%" + findName + "%")
				.setFirstResult(start).setMaxResults(length).getResultList();
	}

	@Override
	public int count(String findName) {
		String jql = "select count(c) from Category c where c.name like :name";
		return entityManager.createQuery(jql, Integer.class).setParameter("name", "%" + findName + "%")
				.getSingleResult();
	}
	
}
