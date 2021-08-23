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

import united.dk.finalproject.model.SizeDTO;
import united.dk.finalproject.service.SizeService;

@Controller
public class AdminSizeController {
	
	@Autowired
	SizeService sizeService;
	
	@GetMapping(value = "/admin/size/search")
	public String searchKichThuoc(HttpServletRequest request,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer page) {
		page = page == null ? 1 : page;
		keyword = keyword == null ? "" : keyword;
		// mac dinh 10 ban ghi 1 trang
		List<SizeDTO> sizeList = sizeService.search(keyword, 0, page * 10);
		request.setAttribute("sizeList", sizeList);
		request.setAttribute("page", page);
		request.setAttribute("keyword", keyword);

		return "admin/size/size-list";
	}
	
	@GetMapping(value ="/admin/size/add")
	public String addSizeGet() {
		
		return "/admin/size/add-size";
	}
	
	@PostMapping(value ="/admin/size/add")
	public String addSizePost(@ModelAttribute SizeDTO sizeDTO) {
		sizeService.insert(sizeDTO);
		return "redirect:/admin/size/search";
	}
	
	@GetMapping(value ="/admin/size/update")
		public String updateSizeGet(Model model, @RequestParam(value = "id")long id) {
		SizeDTO sizeDTO = sizeService.get(id);
		model.addAttribute("sizeDTO", sizeDTO);
		
			return "/admin/size/update-size";
		}
	
	@PostMapping(value = "/admin/size/update")
	public String updateSizePost(@ModelAttribute SizeDTO sizeDTO) {
		sizeService.update(sizeDTO);
		return "redirect:/admin/size/search";
	}
	
	@GetMapping(value ="/admin/size/delete")
	public String deleteSize(@RequestParam(value ="id")long id) {
		sizeService.delete(id); 
		return "redirect:/admin/size/search";
	}
	}
