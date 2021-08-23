package united.dk.finalproject.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import united.dk.finalproject.dao.ProductDao;
import united.dk.finalproject.entity.Category;
import united.dk.finalproject.entity.Product;
import united.dk.finalproject.entity.Sex;
import united.dk.finalproject.entity.Size;
import united.dk.finalproject.entity.TradeMark;
import united.dk.finalproject.model.CategoryDTO;
import united.dk.finalproject.model.ProductDTO;
import united.dk.finalproject.model.SexDTO;
import united.dk.finalproject.model.SizeDTO;
import united.dk.finalproject.model.TradeMarkDTO;
import united.dk.finalproject.service.ProductService;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public void insert(ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setQuantity(productDTO.getQuantity());
		product.setDescription(productDTO.getDescription());
		product.setImage(productDTO.getImage());

		Category category = new Category();
		category.setId(productDTO.getCategory().getId());
		category.setName(productDTO.getCategory().getName());
		product.setCategory(category);

		TradeMark tradeMark = new TradeMark();
		tradeMark.setId(productDTO.getTradeMark().getId());
		tradeMark.setName(productDTO.getTradeMark().getName());
		product.setTradeMark(tradeMark);

		Size size = new Size();
		size.setId(productDTO.getSize().getId());
		size.setName(productDTO.getSize().getName());
		product.setSize(size);

		Sex sex = new Sex();
		sex.setId(productDTO.getSex().getId());
		sex.setName(productDTO.getSex().getName());
		product.setSex(sex);

		productDao.insert(product);
	}

	@Override
	public void update(ProductDTO productDTO) {
		Product product = productDao.get(productDTO.getId());
		if (product != null) {
			product.setId(productDTO.getId());
			product.setName(productDTO.getName());
			product.setPrice(productDTO.getPrice());
			product.setQuantity(productDTO.getQuantity());
			product.setDescription(productDTO.getDescription());
			product.setImage(productDTO.getImage());

			Category category = new Category();
			category.setId(productDTO.getCategory().getId());
			category.setName(productDTO.getCategory().getName());
			product.setCategory(category);

			TradeMark tradeMark = new TradeMark();
			tradeMark.setId(productDTO.getTradeMark().getId());
			tradeMark.setName(productDTO.getTradeMark().getName());
			product.setTradeMark(tradeMark);

			Size size = new Size();
			size.setId(productDTO.getSize().getId());
			size.setName(productDTO.getSize().getName());
			product.setSize(size);

			Sex sex = new Sex();
			sex.setId(productDTO.getSex().getId());
			sex.setName(productDTO.getSex().getName());
			product.setSex(sex);

			productDao.update(product);
		}
	}

	@Override
	public void delete(Long id) {
		Product product = productDao.get(id);
		if (product != null) {
			productDao.delete(product);
		}
	}

	@Override
	public ProductDTO get(Long id) {
		Product product = productDao.get(id);
		ProductDTO dto = new ProductDTO();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		dto.setQuantity(product.getQuantity());
		dto.setDescription(product.getDescription());
		dto.setImage(product.getImage());

		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(product.getCategory().getId());
		categoryDTO.setName(product.getCategory().getName());
		dto.setCategory(categoryDTO);

		TradeMarkDTO tradeMarkDTO = new TradeMarkDTO();
		tradeMarkDTO.setId(product.getTradeMark().getId());
		tradeMarkDTO.setName(product.getTradeMark().getName());
		dto.setTradeMark(tradeMarkDTO);

		SizeDTO sizeDTO = new SizeDTO();
		sizeDTO.setId(product.getSize().getId());
		sizeDTO.setName(product.getSize().getName());
		dto.setSize(sizeDTO);

		SexDTO sexDTO = new SexDTO();
		sexDTO.setId(product.getSex().getId());
		sexDTO.setName(product.getSex().getName());
		dto.setSex(sexDTO);

		return dto;
	}

	@Override
	public List<ProductDTO> search(String findName, String categoryName, String tradeMarkName, String sizeName,
			String sexName, Long priceStart, Long priceEnd, int start, int length) {
		List<Product> listProducts = productDao.search(findName, categoryName, tradeMarkName, sizeName, sexName,
				priceStart, priceEnd, start, length);
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (Product product : listProducts) {
			ProductDTO dto = new ProductDTO();
			dto.setId(product.getId());
			dto.setName(product.getName());
			dto.setPrice(product.getPrice());
			dto.setQuantity(product.getQuantity());
			dto.setDescription(product.getDescription());
			dto.setImage(product.getImage());

			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setId(product.getCategory().getId());
			categoryDTO.setName(product.getCategory().getName());
			dto.setCategory(categoryDTO);

			TradeMarkDTO tradeMarkDTO = new TradeMarkDTO();
			tradeMarkDTO.setId(product.getTradeMark().getId());
			tradeMarkDTO.setName(product.getTradeMark().getName());
			dto.setTradeMark(tradeMarkDTO);

			SizeDTO sizeDTO = new SizeDTO();
			sizeDTO.setId(product.getSize().getId());
			sizeDTO.setName(product.getSize().getName());
			dto.setSize(sizeDTO);

			SexDTO sexDTO = new SexDTO();
			sexDTO.setId(product.getSex().getId());
			sexDTO.setName(product.getSex().getName());
			dto.setSex(sexDTO);
			
			productDTOs.add(dto);
		}
		return productDTOs;
	}

	@Override
	public List<ProductDTO> searchByCategory(String findName, String tradeMarkName, String sizeName, String sexName,
			Long priceStart, Long priceEnd, Long categoryId, int start, int length) {

		List<Product> listProducts = productDao.searchByCategory(findName, tradeMarkName, sizeName, sexName,
				priceStart, priceEnd, categoryId, start, length);
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (Product product : listProducts) {
			ProductDTO dto = new ProductDTO();
			dto.setId(product.getId());
			dto.setName(product.getName());
			dto.setPrice(product.getPrice());
			dto.setQuantity(product.getQuantity());
			dto.setDescription(product.getDescription());
			dto.setImage(product.getImage());

			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setId(product.getCategory().getId());
			categoryDTO.setName(product.getCategory().getName());
			dto.setCategory(categoryDTO);

			TradeMarkDTO tradeMarkDTO = new TradeMarkDTO();
			tradeMarkDTO.setId(product.getTradeMark().getId());
			tradeMarkDTO.setName(product.getTradeMark().getName());
			dto.setTradeMark(tradeMarkDTO);

			SizeDTO sizeDTO = new SizeDTO();
			sizeDTO.setId(product.getSize().getId());
			sizeDTO.setName(product.getSize().getName());
			dto.setSize(sizeDTO);

			SexDTO sexDTO = new SexDTO();
			sexDTO.setId(product.getSex().getId());
			sexDTO.setName(product.getSex().getName());
			dto.setSex(sexDTO);
			
			productDTOs.add(dto);
		}
		return null;
	}
}
