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

import united.dk.finalproject.model.TradeMarkDTO;
import united.dk.finalproject.service.TradeMarkService;

@Controller
public class AdminTradeMarkController {
	
	@Autowired
	TradeMarkService tradeMarkService;
	
	@GetMapping(value = "/admin/tradeMark/search")
	public String searchThuongHieu(HttpServletRequest request,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer page) {
		page = page == null ? 1 : page;
		keyword = keyword == null ? "" : keyword;
		// mac dinh 10 ban ghi 1 trang
		List<TradeMarkDTO> tradeMarkDTOs = tradeMarkService.search(keyword, 0, page * 10);
		request.setAttribute("tradeMarkList", tradeMarkDTOs);
		request.setAttribute("page", page);
		request.setAttribute("keyword", keyword);

		return "admin/tradeMark/trade-mark-list";
	}
	
	@GetMapping(value = "/admin/tradeMark/add")
	public String addTradeMark() {
		
		return "/admin/tradeMark/add-TradeMark";
	}
	
	@PostMapping(value = "/admin/tradeMark/add")
	public String addTradeMarkPost(@ModelAttribute TradeMarkDTO tradeMarkDTO) {
		tradeMarkService.insert(tradeMarkDTO);
		return "redirect:/admin/tradeMark/search";
	}
	
	@GetMapping(value ="/admin/tradeMark/update")
	public String updateTradeMarkGet(Model model, @RequestParam(value = "id")Long id) {
		TradeMarkDTO tradeMarkDTO = tradeMarkService.get(id);
		model.addAttribute("tradeMark", tradeMarkDTO);
		return "/admin/TradeMark/update-TradeMark";
	}
	
	@PostMapping(value ="admin/tradeMark/update")
	public String updateTradeMarkPost(@ModelAttribute TradeMarkDTO tradeMarkDTO) {
		tradeMarkService.update(tradeMarkDTO);
		
		return "redirect:/admin/tradeMark/search";
	}
	@GetMapping(value ="admin/tradeMark/delete")
	public String deleteTradeMark(@RequestParam(value ="id")long id) {
		tradeMarkService.delete(id);
		return "redirect:/admin/tradeMark/search";
	}
}
