package united.dk.finalproject.dao;

import java.util.List;

import united.dk.finalproject.entity.Sex;


public interface SexDao {
	void add(Sex sex);

	void update(Sex sex);

	void delete(Long id);

	Sex getById(Long id);

	List<Sex> Search(String name, int start, int length);
}
