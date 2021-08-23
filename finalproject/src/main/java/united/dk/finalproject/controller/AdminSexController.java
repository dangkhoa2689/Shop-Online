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

import united.dk.finalproject.entity.Sex;
import united.dk.finalproject.model.SexDTO;
import united.dk.finalproject.service.SexService;

@Controller
public class AdminSexController {
	
	@Autowired
	SexService sexService;
	
	@GetMapping(value = "/admin/sex/search")
	public String search(HttpServletRequest request, @RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer page) {
		page = page == null ? 1 : page;
		keyword = keyword == null ? "" : keyword;
		// mac dinh 10 ban ghi 1 trang

		List<SexDTO> sexList = sexService.search(keyword, 0, page * 10);
		request.setAttribute("sexList", sexList);
		request.setAttribute("page", page);
		request.setAttribute("keyword", keyword);

		return "admin/sex/sex-list";
	}
	
	@GetMapping(value ="/admin/sex/add")
	public String addSexGet() {
		
		return "/admin/sex/add-sex";
	}
	@PostMapping(value ="/admin/sex/add")
	public String addSexPost(@ModelAttribute SexDTO sexDTO) {
		sexService.add(sexDTO);
		return "redirect:/admin/sex/search";
	}
	
	@GetMapping(value ="/admin/sex/update")
	public String updateSexGet(Model model, @RequestParam(value ="id")long id) {
		SexDTO sex = sexService.getById(id);
		model.addAttribute("sex", sex);
		return "/admin/sex/update-sex";
	}
	
	@PostMapping(value = "/admin/sex/update")
	public String updateSexPost(@ModelAttribute SexDTO sexDTO) {
		sexService.update(sexDTO);
		return "redirect:/admin/sex/search";
	}
	
	@GetMapping(value ="/admin/sex/delete")
	public String deleteSex(@RequestParam(value ="id")long id) {
		sexService.delete(id);
		return "redirect:/admin/sex/search";
	}
}
