package united.dk.finalproject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import united.dk.finalproject.dao.TradeMarkDao;
import united.dk.finalproject.entity.Category;
import united.dk.finalproject.entity.TradeMark;
@Transactional
@Repository
public class TradeMarkDaoImpl implements TradeMarkDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insert(TradeMark tradeMark) {
		// TODO Auto-generated method stub
		entityManager.persist(tradeMark);
	}

	@Override
	public void update(TradeMark tradeMark) {
		// TODO Auto-generated method stub
		entityManager.merge(tradeMark);
	}

	@Override
	public void delete(TradeMark tradeMark) {
		// TODO Auto-generated method stub
		entityManager.remove(tradeMark);
	}

	@Override
	public TradeMark get(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(TradeMark.class, id);
	}

	@Override
	public List<TradeMark> search(String findName) {
		String jql = "SELECT t FROM TradeMark t WHERE t.name =  :name";
		return entityManager.createQuery(jql, TradeMark.class).setParameter("name", "%" + findName + "%").getResultList();
	}

	@Override
	public List<TradeMark> search(String findName, int offset, int maxPerPage) {
		String jql = "select t from TradeMark t where t.name like :name";
		return entityManager.createQuery(jql, TradeMark.class).setParameter("name", "%" + findName + "%")
				.setFirstResult(offset).setMaxResults(maxPerPage).getResultList();
	}

	@Override
	public int count(String findName) {
		String jql = "select count(t) from TradeMark t where t.name like :name";
		return entityManager.createQuery(jql, Integer.class).setParameter("name", "%" + findName + "%")
				.getSingleResult();
	}

}
