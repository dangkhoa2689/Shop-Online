package united.dk.finalproject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import united.dk.finalproject.dao.BillProductDao;
import united.dk.finalproject.entity.BillProduct;

@Transactional
@Repository
public class BillDaoProductImpl implements BillProductDao{
	
	@PersistenceContext
	EntityManager entityManager;
	@Override
	public void insert(BillProduct billProduct) {
		// TODO Auto-generated method stub
		entityManager.persist(billProduct);
	}

	@Override
	public void update(BillProduct billProduct) {
		// TODO Auto-generated method stub
		entityManager.merge(billProduct);
	}

	@Override
	public void delete(BillProduct billProduct) {
		// TODO Auto-generated method stub
		entityManager.remove(billProduct);
	}

	@Override
	public BillProduct get(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find(BillProduct.class, id);
	}

	@Override
	public List<BillProduct> search(String findName, int start, int length) {
		String jql = "select bp from BillProduct bp join bp.product p join bp.bill b where p.name like :pname";
		return entityManager.createQuery(jql, BillProduct.class).setParameter("pname","%"+ findName+"%")
				.setFirstResult(start).setMaxResults(length).getResultList();
	}

	@Override
	public List<BillProduct> searchByBill(Long idBill, int start, int length) {
		String jql = "select bp from BillProduct bp join bp.product p join bp.bill b where b.id =:billId";
		return entityManager.createQuery(jql, BillProduct.class)
				.setParameter("billId", idBill).setFirstResult(start).setMaxResults(length).getResultList();
	}
	
}
