package united.dk.finalproject.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import united.dk.finalproject.dao.BillProductDao;
import united.dk.finalproject.entity.Bill;
import united.dk.finalproject.entity.BillProduct;
import united.dk.finalproject.entity.Product;
import united.dk.finalproject.model.BillProductDTO;
import united.dk.finalproject.model.ProductDTO;
import united.dk.finalproject.service.BillProductService;
@Transactional
@Service
public class BillProductServiceImpl implements BillProductService {
	
	@Autowired
	BillProductDao billProductDao;
	@Override
	public void insert(BillProductDTO billProductDTO) {
		// TODO Auto-generated method stub
		BillProduct billProduct = new BillProduct();
		billProduct.setQuantity(billProductDTO.getQuantity());
		billProduct.setUnitPrice(billProductDTO.getUnitPrice());
		
		Bill bill = new Bill();
		bill.setId(billProductDTO.getBill().getId());
		billProduct.setBill(bill);
		
		Product product = new Product();
		product.setId(billProductDTO.getProduct().getId());
		billProduct.setProduct(product);
		
		billProductDao.insert(billProduct);
		
	}

	@Override
	public void update(BillProductDTO billProductDTO) {
		// TODO Auto-generated method stub
		BillProduct billProduct = billProductDao.get(billProductDTO.getId());
		if(billProduct != null) {
			billProduct.setId(billProductDTO.getId());
			billProduct.setQuantity(billProductDTO.getQuantity());
			billProduct.setUnitPrice(billProductDTO.getUnitPrice());
			
			Bill bill = new Bill();
			bill.setId(billProductDTO.getBill().getId());
			
			Product product = new Product();
			product.setId(billProductDTO.getProduct().getId());
			
			billProduct.setBill(bill);
			billProduct.setProduct(product);
			
			billProductDao.update(billProduct);
		}
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		BillProduct billProduct = billProductDao.get(id);
		if(billProduct != null) {
			billProductDao.delete(billProduct);
		}
	}

	@Override
	public List<BillProductDTO> search(String findName, int start, int length) {
		List<BillProduct> billProducts = billProductDao.search(findName,start, length );
		List<BillProductDTO> billProductDTOs = new ArrayList<BillProductDTO>();

		for (BillProduct billProduct : billProducts) {
			billProductDTOs.add(convertDTO(billProduct));
		}
		return billProductDTOs;
	}

	private BillProductDTO convertDTO(BillProduct billProduct) {
		BillProductDTO billProductDTO = new BillProductDTO();
		billProductDTO.setId(billProduct.getId());
		billProductDTO.setQuantity(billProduct.getQuantity());
		billProductDTO.setUnitPrice(billProduct.getUnitPrice());

		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(billProduct.getProduct().getId());
		productDTO.setName(billProduct.getProduct().getName());
		productDTO.setImage(billProduct.getProduct().getImage());
		productDTO.setPrice(billProduct.getProduct().getPrice());
		billProductDTO.setProduct(productDTO);

		return billProductDTO;
	}

	@Override
	public List<BillProductDTO> searchByBill(Long idBill, int start, int length) {
		List<BillProduct> billProducts = billProductDao.searchByBill(idBill, start, length);
		List<BillProductDTO> billProductDTOs = new ArrayList<BillProductDTO>();

		for (BillProduct billProduct : billProducts) {
			billProductDTOs.add(convertDTO(billProduct));
		}
		return billProductDTOs;
	}
	
}
