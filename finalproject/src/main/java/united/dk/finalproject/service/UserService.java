package united.dk.finalproject.service;

import java.util.List;

import united.dk.finalproject.model.UserDTO;

public interface UserService {
	void insertUser(UserDTO userDTO);

	void updateUser(UserDTO userDTO);

	void deleteUser(long id);

	UserDTO get(long id);

	UserDTO getByUserName(String userName);

	List<UserDTO> search(String findName, int start, int length);
	
	void changePassword(UserDTO accountDTO);
	
	void resetPassword(UserDTO accountDTO);
	
	void setupPassword(UserDTO userDTO);
	
	void updateProfile(UserDTO userDTO);
}
