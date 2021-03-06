package united.dk.finalproject.service;

import java.util.List;

import united.dk.finalproject.model.BillDTO;


public interface BillService {
	void insert(BillDTO billDTO);

	void update(BillDTO billDTO);

	void delete(Long id);

	BillDTO get(Long id);

	List<BillDTO> search(String findName, int start, int length);
	
	List<BillDTO> searchByBuyerId(Long buyerId, int start, int length);
}
