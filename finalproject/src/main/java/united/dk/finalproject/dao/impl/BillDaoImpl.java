package united.dk.finalproject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import united.dk.finalproject.dao.BillDao;
import united.dk.finalproject.entity.Bill;

@Transactional
@Repository	
public class BillDaoImpl implements BillDao{
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void insert(Bill bill) {
		// TODO Auto-generated method stub
		entityManager.persist(bill);
	}

	@Override
	public void update(Bill bill) {
		// TODO Auto-generated method stub
		entityManager.merge(bill);
	}

	@Override
	public void delete(Bill bill) {
		// TODO Auto-generated method stub
		entityManager.remove(bill);
	}

	@Override
	public Bill get(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find(Bill.class, id);
	}

	@Override
	public List<Bill> search(String findName, int start, int length) {
		String sql = " Select b from Bill b join b.buyer u where u.name like :uname";
		return entityManager.createQuery(sql, Bill.class).setParameter("uname", "%" + findName +"%").setFirstResult(start).setMaxResults(length)
				.getResultList();
	}

	@Override
	public List<Bill> searchByBuyerId(Long buyerId, int start, int length) {
		String sql = "Select b from Bill b join b.buyer u where u.id =:buyerId ";
		return entityManager.createQuery(sql, Bill.class).setParameter("buyerId", buyerId).setFirstResult(start).setMaxResults(length)
				.getResultList();
	}
}
