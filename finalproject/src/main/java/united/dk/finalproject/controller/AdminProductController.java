package united.dk.finalproject.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import united.dk.finalproject.model.CategoryDTO;
import united.dk.finalproject.model.ProductDTO;
import united.dk.finalproject.model.SexDTO;
import united.dk.finalproject.model.SizeDTO;
import united.dk.finalproject.model.TradeMarkDTO;
import united.dk.finalproject.service.CategoryService;
import united.dk.finalproject.service.ProductService;
import united.dk.finalproject.service.SexService;
import united.dk.finalproject.service.SizeService;
import united.dk.finalproject.service.TradeMarkService;

@Controller
public class AdminProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	TradeMarkService tradeMarkService;

	@Autowired
	SizeService sizeService;

	@Autowired
	SexService sexService;

	@GetMapping(value = "/admin/product/add")
	public String addProductGet(HttpServletRequest request, Model model) {
		model.addAttribute("product", new ProductDTO());
		List<CategoryDTO> list = categoryService.search("", 0, 100);
		List<TradeMarkDTO> tradeMarkDTOs = tradeMarkService.search("", 0, 100);
		List<SizeDTO> sizeDTOs = sizeService.search("", 0, 100);
		List<SexDTO> sexDTOs = sexService.search("", 0, 100);

		request.setAttribute("sexList", sexDTOs);
		request.setAttribute("categoryList", list);
		request.setAttribute("tradeMarkList", tradeMarkDTOs);
		request.setAttribute("sizeList", sizeDTOs);
		return "/admin/product/add";
	}

	@PostMapping(value = "/admin/product/add")
	public String addProductPost(@ModelAttribute(name = "product") ProductDTO product,
			@RequestParam(name = "imageFile") MultipartFile imagefile) {
		String originalFilename = imagefile.getOriginalFilename();
		int lastIndex = originalFilename.lastIndexOf(".");
		String ext = originalFilename.substring(lastIndex);

		String avatarFilename = System.currentTimeMillis() + ext;
		File newfile = new File("D:\\Upload\\" + avatarFilename);
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(newfile);
			fileOutputStream.write(imagefile.getBytes());
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		product.setImage(avatarFilename);
		productService.insert(product);
		return "redirect:/admin/product/search";
	}
	
	@GetMapping(value ="/admin/product/update")
	public String updateProductGet(HttpServletRequest request, Model model, long id) {
		ProductDTO product = productService.get(id);
		List<CategoryDTO> list = categoryService.search("", 0, 100);
		List<TradeMarkDTO> tradeMarkDTOs = tradeMarkService.search("", 0, 100);
		List<SizeDTO> sizeDTOs = sizeService.search("", 0, 100);
		List<SexDTO> sexDTOs = sexService.search("", 0, 100);
		
		model.addAttribute("product", product);
		request.setAttribute("sexList", sexDTOs);
		request.setAttribute("categoryList", list);
		request.setAttribute("tradeMarkList", tradeMarkDTOs);
		request.setAttribute("sizeList", sizeDTOs);
		return "/admin/product/update";
	}
	
	@PostMapping(value ="/admin/product/update")
	public String updateProductPost(@ModelAttribute(name = "product") ProductDTO product,
			@RequestParam(name = "imageFile") MultipartFile imagefile) {
		String originalFilename = imagefile.getOriginalFilename();
		int lastIndex = originalFilename.lastIndexOf(".");
		String ext = originalFilename.substring(lastIndex);

		String avatarFilename = System.currentTimeMillis() + ext;
		File newfile = new File("D:\\Upload\\" + avatarFilename);
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(newfile);
			fileOutputStream.write(imagefile.getBytes());
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		product.setImage(avatarFilename);
		productService.update(product);
		return "redirect:/admin/product/search";
	}
	
	@GetMapping(value ="/admin/product/delete")
	public String deleteProduct(@RequestParam(value ="id")long id) {
		productService.delete(id);
		return "redirect:/admin/product/search";
	}
	
	@GetMapping(value = "/admin/product/search")
	public String AdminProductSearch(HttpServletRequest request) {

		String tradeMarkName = request.getParameter("tradeMarkName") == null ? ""
				: request.getParameter("tradeMarkName");

		String sexName = request.getParameter("sexName") == null ? "" : request.getParameter("sexName");

		String categoryName = request.getParameter("categoryName") == null ? "" : request.getParameter("categoryName");

		String sizeName = request.getParameter("sizeName") == null ? ""
				: request.getParameter("sizeName");

		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");

		Integer page = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));

		Long priceStart = (request.getParameter("priceStart") == null || request.getParameter("priceStart") == "") ? 1
				: Long.valueOf(request.getParameter("priceStart"));

		Long priceEnd = (request.getParameter("priceEnd") == null || request.getParameter("priceEnd") == "") ? 100000000L
				: Long.valueOf(request.getParameter("priceEnd"));

		List<ProductDTO> listPro = productService.search(keyword,categoryName, tradeMarkName, sizeName,sexName,
			priceStart, priceEnd, 0, page * 10);
		List<CategoryDTO> categoryDTOs = categoryService.search("", 0, page * 10);
		List<TradeMarkDTO> tradeMarkDTOs = tradeMarkService.search("", 0, page * 10);
		List<SizeDTO> sizeDTOs = sizeService.search("", 0, page * 10);
		List<SexDTO> sexDTOs = sexService.search("", 0, page * 10);

		request.setAttribute("sexList", sexDTOs);
		request.setAttribute("sizeList", sizeDTOs);
		request.setAttribute("tradeMarkList", tradeMarkDTOs);
		request.setAttribute("productList", listPro);
		request.setAttribute("categoryList", categoryDTOs);

		request.setAttribute("sexName", sexName);
		request.setAttribute("sizeName", sizeName);
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("tradeMarkName", tradeMarkName);
		request.setAttribute("page", page);
		request.setAttribute("keyword", keyword);
		return "admin/product/search";
	}
	

}
