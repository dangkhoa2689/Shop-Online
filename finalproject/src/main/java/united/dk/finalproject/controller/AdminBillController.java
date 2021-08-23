package united.dk.finalproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import united.dk.finalproject.model.BillDTO;
import united.dk.finalproject.model.BillProductDTO;
import united.dk.finalproject.service.BillProductService;
import united.dk.finalproject.service.BillService;
import united.dk.finalproject.service.ProductService;

@Controller
public class AdminBillController {
	
	@Autowired
	BillService billService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	BillProductService billProductService;
	
	@GetMapping(value = "/admin/bill/search") 
	public String AdminBillSearch(HttpServletRequest request,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer page) {

		page = page == null ? 1 : page;
		keyword = keyword == null ? "" : keyword;
		List<BillDTO> listBill = billService.search(keyword, 0, page * 10);
		request.setAttribute("bills", listBill);
		request.setAttribute("page", page);
		request.setAttribute("keyword", keyword);
		return "admin/bill/bills";
	}

	@GetMapping(value = "/admin/billproduct/search") 
	public String AdminBillProductSearch(HttpServletRequest request, @RequestParam(name = "idBill") Long idBill,
			@RequestParam(value = "keyword", required = false) Long keyword,
			@RequestParam(value = "page", required = false) Integer page) {
		page = page == null ? 1 : page;
		keyword = idBill;
		List<BillProductDTO> listBillProduct = billProductService.searchByBill(keyword, 0, page * 10);
		request.setAttribute("billProducts", listBillProduct);
		request.setAttribute("page", page);
		request.setAttribute("keyword", keyword);
		return "admin/bill/bill";
	}

	@GetMapping(value = "/admin/delete/bills")
	public String deleteBill(@RequestParam(name = "idBill", required = true) Long billId) {
		billService.delete(billId);
		return "redirect:/admin/bill/search";
	}

	@GetMapping(value = "/admin/delete/bill")
	public String deleteBillProduct(@RequestParam(name = "idBill", required = true) Long billId) {
		System.out.println(billId);
		System.out.println();
		billProductService.delete(billId);
		return "redirect:/admin/billproduct/search?idBill=" + billId;
	}
	
}
