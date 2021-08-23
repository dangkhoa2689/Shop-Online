package united.dk.finalproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import united.dk.finalproject.model.UserDTO;
import united.dk.finalproject.service.UserService;

@Controller
public class AdminUserController {
	@Autowired
	UserService userService;
	
	@GetMapping(value="/admin/user/search")
	public String searchUser(HttpServletRequest request,
			@RequestParam(value="keyword",required=false)String keyword,
			@RequestParam(value="page",required=false)Integer page) {
		page = page == null ? 1 : page;
		keyword = keyword == null ? "" : keyword;
		// mac dinh 10 ban ghi 1 trang
		List<UserDTO> userList = userService.search(keyword, 0, page * 10);
		request.setAttribute("userList", userList);
		request.setAttribute("page", page);
		request.setAttribute("keyword", keyword);
		
		
		return "admin/user/users";
	}
	
	@GetMapping(value="/admin/user/add")
	public String AdminAddUserGet() {
		
		return"admin/user/add-user";
	}
	
	@PostMapping(value="/admin/user/add")
	public String AdminAddUserPost(@ModelAttribute(name="adduser")UserDTO user) {
		user.setEnabled(true);
		userService.insertUser(user);
		
		return"redirect:/admin/user/search";
	}
	
	@GetMapping(value="/admin/user/delete")
	public String deleteUser(long id) {
		userService.deleteUser(id);
		return "redirect:/admin/user/search";
	}
	
	@GetMapping(value="/admin/user/update")
	public String AdminUpdateUserGet(Model model, @RequestParam(name="id")Long id) {
		UserDTO user = userService.get(id);
		model.addAttribute("user", user);
		
		return"admin/user/update-user";
	}
	
	@PostMapping(value="/admin/user/update")
	public String AdminUpdateUserPost(@ModelAttribute(name="user")UserDTO user) {
		userService.updateUser(user);
		return "redirect:/admin/user/search";
	}
	
	@GetMapping(value="/admin/user/change-password")
	public String AdminChangePasswordGet(Model model, @RequestParam(name="id")Long id) {
		UserDTO user = userService.get(id);
		model.addAttribute("user", user);
		
		return "admin/user/change-password";
	}
	
	@PostMapping(value="admin/user/change-password")
	public String AdminChangePasswordPost(@ModelAttribute(name="user")UserDTO user) {
		userService.changePassword(user);
		return "redirect:/admin/user/search";
	}
	
	
}
