package united.dk.finalproject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import united.dk.finalproject.dao.ProductDao;
import united.dk.finalproject.entity.Product;

@Transactional
@Repository
public class ProductDaoImpl implements ProductDao {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void insert(Product product) {
		// TODO Auto-generated method stub
		entityManager.persist(product);
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		entityManager.merge(product);
	}

	@Override
	public void delete(Product product) {
		// TODO Auto-generated method stub
		entityManager.remove(product);
	}

	@Override
	public Product get(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find(Product.class, id);
	}

	@Override
	public List<Product> search(String findName, String categoryName, String tradeMarkName, String sizeName,
			String sexName, Long priceStart, Long priceEnd, int start, int length) {
		try {
			String hql = "SELECT p FROM Product p join p.category c"
					+ " join p.tradeMark t"
					+ " join p.size s"
					+ " join p.sex sex "
					+ "where (p.name like:pname and c.name like:cname and t.name like:tname and s.name like:sname and sex.name like:sexname"
					+ " and (p.price between :priceStart AND :priceEnd ))";

			return entityManager.createQuery(hql, Product.class).setParameter("pname", "%" + findName + "%")
					.setParameter("cname", "%" + categoryName + "%").setParameter("tname", "%" + tradeMarkName + "%")
					.setParameter("sname", "%" + sizeName + "%").setParameter("sexname", "%" + sexName + "%")
					.setParameter("priceStart", priceStart).setParameter("priceEnd", priceEnd).setFirstResult(start)
					.setMaxResults(length).getResultList();

		} catch (Exception e) {
			System.out.println("loi" + e);
		}
		return null;
	}

	@Override
	public List<Product> searchByCategory(String findName, String tradeMarkName, String sizeName,
			String sexName, Long priceStart, Long priceEnd, Long categoryId, int start, int length) {
		String hql = "SELECT p FROM Product p  inner join Category c on c.id=p.category.id"
				+ " inner join TradeMark t  on p.tradeMark.id=th.id"
				+ " inner join Size s on p.size.id=s.id "
				+ " inner join Sex sex on p.sex.id=sex.id "
				+ "where (p.name like:pname and p.category.id=:cId and p.tradeMark.name like:tname and p.size.name like:sname and p.sex.name like:sexname"
				+ " and (p.price between :priceStart AND :priceEnd ))";

		return entityManager.createQuery(hql, Product.class).setParameter("pname", "%" + findName + "%")
				.setParameter("tname", "%" + tradeMarkName + "%").setParameter("sexname", "%" + sexName + "%")
				.setParameter("priceStart", priceStart).setParameter("priceEnd", priceEnd)
				.setParameter("tname", "%" + sizeName + "%").setParameter("cId", categoryId).setFirstResult(start)
				.setMaxResults(length).getResultList();
	}
	
}
