package united.dk.finalproject.service;

import java.util.List;

import united.dk.finalproject.model.SexDTO;

public interface SexService {
	void add(SexDTO sexDTO);

	void update(SexDTO sexDTO);

	void delete(Long id);

	SexDTO getById(Long id);

	List<SexDTO> search(String name, int start, int length);
}
