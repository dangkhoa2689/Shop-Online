package united.dk.finalproject.service;

import java.util.List;

import united.dk.finalproject.model.CategoryDTO;

public interface CategoryService {
	void insert(CategoryDTO categoryDTO);

	void update(CategoryDTO categoryDTO);

	void delete(Long id);

	CategoryDTO get(Long id);

	List<CategoryDTO> search(String name, int start, int length);
}
