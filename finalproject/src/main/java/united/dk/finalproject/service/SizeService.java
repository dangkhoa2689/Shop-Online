package united.dk.finalproject.service;

import java.util.List;

import united.dk.finalproject.model.SizeDTO;

public interface SizeService {
	void insert(SizeDTO sizeDTO);

	void update(SizeDTO sizeDTO);

	void delete(Long id);

	List<SizeDTO> search(String name, int start, int length);

	SizeDTO get(Long id);
}
