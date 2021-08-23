package united.dk.finalproject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import united.dk.finalproject.dao.UserDao;
import united.dk.finalproject.entity.User;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insertUser(User user) {
		entityManager.persist(user);
	}

	@Override
	public void updateUser(User user) {
		entityManager.merge(user);
	}

	@Override
	public void deleteUser(User user) {
		entityManager.remove(user);
	}

	@Override
	public User get(long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public User getByUserName(String userName) {
		String jql = "SELECT u FROM User u WHERE u.username =  :username";
		return entityManager.createQuery(jql, User.class).setParameter("username", userName).getSingleResult();
	}

	@Override
	public List<User> search(String findName, int start, int length) {
		String jql = "select u from User u where name like :name";
		Query query = entityManager.createQuery(jql, User.class);
		query.setParameter("name", "%" + findName + "%");
		query.setFirstResult(start).setMaxResults(length);
		return query.getResultList();
	}

}
