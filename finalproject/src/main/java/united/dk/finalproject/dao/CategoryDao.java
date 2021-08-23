package united.dk.finalproject.dao;

import java.util.List;

import united.dk.finalproject.entity.Category;


public interface CategoryDao {
	
	void insert(Category category);

	void update(Category category);

	void delete(Category category);

	Category get(Long id);

	List<Category> search(String findName);

	List<Category> search(String findName, int start, int length);

	int count(String findName);
}
