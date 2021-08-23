package united.dk.finalproject.dao;

import java.util.List;

import united.dk.finalproject.entity.User;

public interface UserDao {
	void insertUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(User user);
	
	User get(long id);
	
	User getByUserName(String userName);
	
	List<User> search(String findName, int start, int length);
	
}
