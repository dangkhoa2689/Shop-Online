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

import united.dk.finalproject.model.CategoryDTO;
import united.dk.finalproject.service.CategoryService;

@Controller
public class AdminCategoryController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping(value = "/admin/category/search")
	public String searchCategory(HttpServletRequest request,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer page) {
		page = page == null ? 1 : page;
		keyword = keyword == null ? "" : keyword;
		// mac dinh 10 ban ghi 1 trang
		List<CategoryDTO> categoryList = categoryService.search(keyword, 0, page * 10);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("page", page);
		request.setAttribute("keyword", keyword);

		return "admin/category/categories";
	}
	
	@GetMapping(value = "/admin/category/add")
	public String addCategoryGet() {

		return "/admin/category/add-category";
	}

	@PostMapping(value = "/admin/category/add")
	public String addCategoryPost(@ModelAttribute CategoryDTO category) {
		categoryService.insert(category);
		return "redirect:/admin/category/search";
	}

	@GetMapping(value = "/admin/category/update")
	public String updateCategoryGet(Model model, @RequestParam (value ="id") long id) {
		CategoryDTO category = categoryService.get(id);
		model.addAttribute("cate", category);
		
		
		return "/admin/category/update-category";
	}
	@PostMapping(value ="/admin/category/update")
	public String updateCategoryPost(@ModelAttribute CategoryDTO category) {
		categoryService.update(category);
		
		return "redirect:/admin/category/search";
	}
	
	@GetMapping(value = "/admin/category/delete")
	public String deleteCategory(@RequestParam (value = "id")long id) {
		categoryService.delete(id);
		
		return "redirect:/admin/category/search";
	}
}
