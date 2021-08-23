package united.dk.finalproject.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import united.dk.finalproject.dao.BillDao;
import united.dk.finalproject.entity.Bill;
import united.dk.finalproject.entity.User;
import united.dk.finalproject.model.BillDTO;
import united.dk.finalproject.model.UserDTO;
import united.dk.finalproject.service.BillService;
import united.dk.finalproject.utils.DateTimeUtils;

@Transactional
@Service
public class BillServiceImpl implements BillService{
	
	@Autowired
	BillDao billDao;

	@Override
	public void insert(BillDTO billDTO) {
		// TODO Auto-generated method stub
		Bill bill = new Bill();
		bill.setBuyDate(new Date());
		bill.setBuyer(new User(billDTO.getUser().getId()));
		bill.setStatus(billDTO.getStatus());
		bill.setPay(billDTO.getPay());
		
		billDao.insert(bill);
		billDTO.setId(bill.getId());
	}

	@Override
	public void update(BillDTO billDTO) {
		// TODO Auto-generated method stub
		Bill bill = billDao.get(billDTO.getId());
		if(bill != null) {
			bill.setPriceTotal(billDTO.getPriceTotal());
			bill.setDiscountPersent(billDTO.getDiscountPercent());
			bill.setStatus(billDTO.getStatus());
			bill.setPay(billDTO.getPay());
			
			billDao.update(bill);
		}
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Bill bill = billDao.get(id);
		if(bill != null) {
			billDao.delete(bill);
		}
	}

	@Override
	public BillDTO get(Long id) {
		Bill bill = billDao.get(id);
		return convertDTO(bill);
	}
	
	private BillDTO convertDTO(Bill bill) {
		BillDTO billDTO = new BillDTO();
		billDTO.setId(bill.getId());
		billDTO.setBuyDate(DateTimeUtils.formatDate(bill.getBuyDate(), DateTimeUtils.DD_MM_YYYY_HH_MM));
		billDTO.setPriceTotal(bill.getPriceTotal());
		billDTO.setDiscountPercent(bill.getDiscountPersent());
		billDTO.setPay(bill.getPay());

		UserDTO userDTO = new UserDTO();
		userDTO.setId(bill.getBuyer().getId());
		userDTO.setAddress(bill.getBuyer().getAddress());
		userDTO.setName(bill.getBuyer().getName());
		userDTO.setPhone(bill.getBuyer().getPhone());
		billDTO.setUser(userDTO);

		return billDTO;
	}

	@Override
	public List<BillDTO> search(String findName, int start, int length) {
		List<Bill> bills = billDao.search(findName, start, length);
		List<BillDTO> billDTOs = new ArrayList<BillDTO>();
		for (Bill bill : bills) {
			billDTOs.add(convertDTO(bill));
		}
		return billDTOs;
	}

	@Override
	public List<BillDTO> searchByBuyerId(Long buyerId, int start, int length) {
		List<Bill> bills = billDao.searchByBuyerId(buyerId, start, length);
		List<BillDTO> billDTOs = new ArrayList<BillDTO>();
		if (bills.isEmpty()) {
			return null;
		} else {
			for (Bill bill : bills) {
				billDTOs.add(convertDTO(bill));
			}
			return billDTOs;
		}
	}
}
