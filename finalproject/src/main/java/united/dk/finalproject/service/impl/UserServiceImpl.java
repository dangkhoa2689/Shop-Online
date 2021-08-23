package united.dk.finalproject.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import united.dk.finalproject.dao.UserDao;
import united.dk.finalproject.entity.User;
import united.dk.finalproject.model.UserDTO;
import united.dk.finalproject.model.UserPrincipal;
import united.dk.finalproject.service.UserService;
import united.dk.finalproject.utils.PasswordGenerator;

@Transactional
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	UserDao userDao;

	@Override
	public void insertUser(UserDTO userDTO) {
		User user = new User();
		user.setName(userDTO.getName());
		user.setAge(userDTO.getAge());
		user.setEnabled(userDTO.getEnabled());
		user.setRole(userDTO.getRole());
		user.setUsername(userDTO.getUsername());
		user.setPassword(PasswordGenerator.getHashString(userDTO.getPassword()));
		user.setGender(userDTO.getGender());
		user.setAddress(userDTO.getAddress());
		user.setPhone(userDTO.getPhone());
		user.setEmail(userDTO.getEmail());

		userDao.insertUser(user);
	}

	@Override
	public void updateUser(UserDTO userDTO) {
		User user = userDao.get(userDTO.getId());
		if (user != null) {
			user.setName(userDTO.getName());
			user.setAge(userDTO.getAge());
			user.setRole(userDTO.getRole());
			user.setUsername(userDTO.getUsername());
			user.setGender(userDTO.getGender());
			user.setAddress(userDTO.getAddress());
			user.setEnabled(userDTO.getEnabled());
			user.setPhone(userDTO.getPhone());
			user.setEmail(userDTO.getEmail());
			userDao.updateUser(user);
		}
	}

	@Override
	public void deleteUser(long id) {
		User user = userDao.get(id);
		if (user != null) {
			userDao.deleteUser(user);
		}

	}

	@Override
	public UserDTO get(long id) {
		User user = userDao.get(id);
		if (user != null) {
			return convert(user);
		}
		return null;
	}

	private UserDTO convert(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setAge(user.getAge());
		userDTO.setRole(user.getRole());
		userDTO.setUsername(user.getUsername());
		userDTO.setGender(user.getGender());
		userDTO.setAddress(user.getAddress());
		userDTO.setEnabled(user.getEnabled());
		userDTO.setPhone(user.getPhone());
		userDTO.setEmail(user.getEmail());
		return userDTO;
	}

	@Override
	public UserDTO getByUserName(String userName) {
		User user = userDao.getByUserName(userName);
		if (user != null) {
			return convert(user);
		}
		return null;
	}

	@Override
	public List<UserDTO> search(String findName, int start, int length) {
		List<User> users = userDao.search(findName, start, length);
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (User user : users) {
			userDTOs.add(convert(user));
		}
		return userDTOs;
	}

	@Override
	public void changePassword(UserDTO accountDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetPassword(UserDTO accountDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupPassword(UserDTO userDTO) {
		User user = userDao.get(userDTO.getId());
		if (user != null) {
			user.setPassword(PasswordGenerator.getHashString(userDTO.getPassword()));
			userDao.updateUser(user);
		}
	}

	@Override
	public void updateProfile(UserDTO userDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.getByUserName(username.trim());
		if (user == null) {
			throw new UsernameNotFoundException("not found");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));

		UserPrincipal userPrincipal = new UserPrincipal(user.getUsername(), user.getPassword(), user.getEnabled(), true,
				true, true, authorities);

		userPrincipal.setId(user.getId());
		userPrincipal.setName(user.getName());
		userPrincipal.setRole(user.getRole());
		userPrincipal.setPhone(user.getPhone());
		userPrincipal.setEmail(user.getEmail());
		
		return userPrincipal;
	}

}
