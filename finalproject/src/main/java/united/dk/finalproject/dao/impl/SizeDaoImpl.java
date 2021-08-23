package united.dk.finalproject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import united.dk.finalproject.dao.SizeDao;
import united.dk.finalproject.entity.Size;
@Transactional
@Repository
public class SizeDaoImpl implements SizeDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insert(Size size) {
		// TODO Auto-generated method stub
		entityManager.persist(size);
	}

	@Override
	public void update(Size size) {
		// TODO Auto-generated method stub
		entityManager.merge(size);
	}

	@Override
	public void delete(Size size) {
		// TODO Auto-generated method stub
		entityManager.remove(size);
	}

	@Override
	public Size get(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(Size.class, id);
	}

	@Override
	public List<Size> search(String findName) {
		String sql = "Select c form Size c where c.name like :name";
		return entityManager.createQuery(sql, Size.class).setParameter("name", "%" + findName + "%").getResultList();
	}

	@Override
	public List<Size> search(String findName, int start, int length) {
		String sql = "Select c from Size c where c.name like :name";
		return entityManager.createQuery(sql, Size.class).setParameter("name", "%" + findName +"%")
				.setFirstResult(start).setMaxResults(length).getResultList();
	}

	@Override
	public int count(String findName) {
		String sql = "Select count(c) from Size c where c.name like :name";
		return entityManager.createQuery(sql, Integer.class).setParameter("name", "%" + findName +"%").getSingleResult();
	}

}
