package united.dk.finalproject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import united.dk.finalproject.dao.SexDao;
import united.dk.finalproject.entity.Sex;

@Transactional
@Repository
public class SexDaoImpl implements SexDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void add(Sex sex) {
		// TODO Auto-generated method stub
		entityManager.persist(sex);
	}

	@Override
	public void update(Sex sex) {
		// TODO Auto-generated method stub
		entityManager.merge(sex);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		entityManager.remove(getById(id));
	}

	@Override
	public Sex getById(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find(Sex.class, id);
	}

	@Override
	public List<Sex> Search(String name, int start, int length) {
		String sql = "Select c from Sex c where c.name like :name";
		return entityManager.createQuery(sql, Sex.class).setParameter("name", "%" + name + "%")
				.setFirstResult(start).setMaxResults(length).getResultList();
	}

}
