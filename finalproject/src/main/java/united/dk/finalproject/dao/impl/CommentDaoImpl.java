package united.dk.finalproject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import united.dk.finalproject.dao.CommentDao;
import united.dk.finalproject.entity.Comment;

@Transactional
@Repository
public class CommentDaoImpl implements CommentDao {
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void insert(Comment comment) {
		// TODO Auto-generated method stub
		entityManager.persist(comment);
	}

	@Override
	public void update(Comment comment) {
		// TODO Auto-generated method stub
		entityManager.merge(comment);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		entityManager.remove(get(id));
	}

	@Override
	public Comment get(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find(Comment.class, id);
	}

	@Override
	public List<Comment> searchByProduct(Long id) {
		String jql = "select c from Comment c join c.product p join c.user u where p.id = :pId";
		return entityManager.createQuery(jql, Comment.class).setParameter("pId", id).getResultList();
	}
}
