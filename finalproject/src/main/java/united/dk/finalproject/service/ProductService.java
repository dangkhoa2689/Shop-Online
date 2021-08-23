package united.dk.finalproject.service;

import java.util.List;

import united.dk.finalproject.model.ProductDTO;

public interface ProductService {
	void insert(ProductDTO productDTO);

	void update(ProductDTO productDTO);

	void delete(Long id);

	ProductDTO get(Long id);

	List<ProductDTO> search(String findName, String categoryName, String tradeMarkName, String sizeName, String sexName,
			Long priceStart, Long priceEnd, int start, int length);

	List<ProductDTO> searchByCategory(String findName, String tradeMarkName, String sizeName, String sexName,
			Long priceStart, Long priceEnd, Long categoryId, int start, int length);
}
