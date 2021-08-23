package united.dk.finalproject.dao;

import java.util.List;

import united.dk.finalproject.entity.Size;

public interface SizeDao {
	void insert(Size size);

	void update(Size size);

	void delete(Size size);

	Size get(long id);

	List<Size> search(String findName);

	List<Size> search(String findName, int start, int length);

	int count(String findName);
}
