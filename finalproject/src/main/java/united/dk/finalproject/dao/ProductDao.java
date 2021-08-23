package united.dk.finalproject.dao;

import java.util.List;

import united.dk.finalproject.entity.Product;


public interface ProductDao {
	void insert(Product product);

	void update(Product product);

	void delete(Product product);

	Product get(Long id);

	List<Product> search(String findName, String categoryName, String tradeMarkName, String sizeName,
			String sexName, Long priceStart, Long priceEnd, int start, int length);

	List<Product> searchByCategory(String findName, String tradeMarkName, String sizeName, String sexName,
			Long priceStart, Long priceEnd, Long categoryId, int start, int length);
}
