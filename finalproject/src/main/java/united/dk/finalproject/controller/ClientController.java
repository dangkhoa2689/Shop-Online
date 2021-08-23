package united.dk.finalproject.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import united.dk.finalproject.model.BillProductDTO;
import united.dk.finalproject.model.CategoryDTO;
import united.dk.finalproject.model.CommentDTO;
import united.dk.finalproject.model.ProductDTO;
import united.dk.finalproject.model.ReviewDTO;
import united.dk.finalproject.model.SexDTO;
import united.dk.finalproject.model.SizeDTO;
import united.dk.finalproject.model.TradeMarkDTO;
import united.dk.finalproject.model.UserDTO;
import united.dk.finalproject.service.CategoryService;
import united.dk.finalproject.service.CommentService;
import united.dk.finalproject.service.ProductService;
import united.dk.finalproject.service.ReviewService;
import united.dk.finalproject.service.SexService;
import united.dk.finalproject.service.SizeService;
import united.dk.finalproject.service.TradeMarkService;
import united.dk.finalproject.service.UserService;
import united.dk.finalproject.utils.RoleEnum;

@Controller
public class ClientController {
	@Autowired
	private TradeMarkService tradeMarkService;
	
	@Autowired
	private SizeService sizeService;
	
	@Autowired
	private SexService sexService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value = "/login")
	public String login(HttpServletRequest request, 
			@RequestParam(name = "err", required = false) String error) {
		if (error != null) {
			request.setAttribute("err", error);
		}
		return "client/login";
	}
	
	@GetMapping(value = "/register")
	public String register(HttpServletRequest request) {
		return "client/register";
	}
	
	@PostMapping(value = "/register")
	public String register(@ModelAttribute UserDTO userDTO) {
		userDTO.setEnabled(true);
		userDTO.setRole(RoleEnum.MEMBER.getRoleName());
		userService.insertUser(userDTO);
		return "redirect:/login";
	}
	
	@GetMapping(value = "/products")
	public String findProducts(HttpServletRequest request) {

		String sexName = request.getParameter("sexName") == null ? "" : request.getParameter("sexName");
		String tradeMarkName = request.getParameter("tradeMarkName") == null ? ""
				: request.getParameter("tradeMarkName");
		String categoryName = request.getParameter("categoryName") == null ? "" : request.getParameter("categoryName");

		String sizeName = request.getParameter("sizeName") == null ? ""
				: request.getParameter("sizeName");

		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");

		Long priceStart = (request.getParameter("priceStart") == null || request.getParameter("priceStart") == "") ? 1
				: Long.valueOf(request.getParameter("priceStart"));

		Integer page = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));

		Long priceEnd = (request.getParameter("priceEnd") == null || request.getParameter("priceEnd") == "") ? 1000000000L
				: Long.valueOf(request.getParameter("priceEnd"));

		List<ProductDTO> listPro = productService.search(keyword, categoryName, tradeMarkName,sizeName, sexName, priceStart, priceEnd, 0, page * 10);

		List<CategoryDTO> list = categoryService.search("", 0, page * 10);
		List<TradeMarkDTO> tradeMarkDTOs = tradeMarkService.search("", 0, page * 10);
		List<SizeDTO> sizeDTOs = sizeService.search("", 0, page * 10);
		List<SexDTO> sexDTOs = sexService.search("", 0, page * 10);

		request.setAttribute("sexList", sexDTOs);
		request.setAttribute("sizeList", sizeDTOs);
		request.setAttribute("tradeMarkList", tradeMarkDTOs);
		request.setAttribute("productList", listPro);
		request.setAttribute("categoryList", list);

		request.setAttribute("sizeName", sizeDTOs);
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("tradeMarkName", tradeMarkName);
		request.setAttribute("page", page);
		request.setAttribute("keyword", keyword);

		return "client/products";
	}

	@GetMapping(value = "/product")
	public String oneProduct(HttpServletRequest request, @RequestParam(name = "id", required = true) Long id) {
		ProductDTO product = productService.get(id);
		List<CommentDTO> commentDTOs = commentService.searchByProduct(id);
		List<ReviewDTO> reviews = reviewService.find(id);
		float sum=0;
		for(ReviewDTO reviewDTO:reviews) {
			int star=reviewDTO.getStarNumber();
			sum=sum+star;
		}
		int dem=reviews.size();
		float totalStar= sum/dem;
		request.setAttribute("dem", dem);
		request.setAttribute("totalStar", totalStar);
		request.setAttribute("product", product);
		request.setAttribute("commentProduct", commentDTOs);
		request.setAttribute("reviews", reviews);
		return "client/product";
	}

	@GetMapping(value = "/member/add-to-cart")
	public String AddToCart(@RequestParam(name = "pid") Long pId, HttpSession session) throws IOException {

//	
		ProductDTO product = productService.get(pId);
	
		Object object = session.getAttribute("cart");
		if (object == null) {// neu cart rong 
			BillProductDTO billProduct = new BillProductDTO();
			billProduct.setProduct(product);
			billProduct.setQuantity(1);
			billProduct.setUnitPrice(product.getPrice());
			Map<Long, BillProductDTO> map = new HashMap<>();
			map.put(pId, billProduct);
			session.setAttribute("cart", map);
		} else {
			Map<Long, BillProductDTO> map = (Map<Long, BillProductDTO>) object;
			BillProductDTO billProduct = map.get(pId);
			if (billProduct == null) {
				billProduct = new BillProductDTO();
				billProduct.setProduct(product);
				billProduct.setQuantity(1);
				billProduct.setUnitPrice(product.getPrice());
				map.put(pId, billProduct);
			} else {
				
				if (billProduct.getQuantity() < product.getQuantity()) {
					billProduct.setQuantity(billProduct.getQuantity() + 1);
				} else {
					billProduct.setQuantity(billProduct.getQuantity());
				}
			}
			session.setAttribute("cart", map);
		}
		return "redirect:/cart";
	}
	@GetMapping(value = "/delete-from-cart")
	public String Deletefromtocart(HttpServletRequest req, @RequestParam(name = "key", required = true) Long key) {
		HttpSession session = req.getSession();
		Object object = session.getAttribute("cart");
		if (object != null) {
			Map<Long, BillProductDTO> map = (Map<Long, BillProductDTO>) object;
			map.remove(key);
			session.setAttribute("cart", map);
		}
		return "redirect:/cart";
	}

	@GetMapping(value = "/cart")
	public String cart() {
		return "client/cart";
	}

	@GetMapping(value = "/category/search")
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

		return "client/categories";
	}

	@GetMapping(value = "/productsByCategory")
	public String AdminProductSearch(HttpServletRequest request,
			@RequestParam(name = "id", required = true) Long categoryId) {

		String tradeMarkName = request.getParameter("tradeMarkName") == null ? ""
				: request.getParameter("tradeMarkName");

		String sexName = request.getParameter("sexName") == null ? "" : request.getParameter("sexName");
		
		String sizeName = request.getParameter("sizeName") == null ? ""
				: request.getParameter("sizeName");
		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
		Integer page = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));

		Long priceStart = (request.getParameter("priceStart") == null || request.getParameter("priceStart") == "") ? 1
				: Long.valueOf(request.getParameter("priceStart"));
		Long priceEnd = (request.getParameter("priceEnd") == null || request.getParameter("priceEnd") == "") ? 100000
				: Long.valueOf(request.getParameter("priceEnd"));

		List<ProductDTO> listPro = productService.searchByCategory(keyword, tradeMarkName,
				sizeName, sexName, priceStart, priceEnd, categoryId, 0, page*10);
		List<TradeMarkDTO> tradeMarkDTOs = tradeMarkService.search("", 0, page * 10);
		List<SizeDTO> sizeDTOs = sizeService.search("", 0, page * 10);
		List<SexDTO> sexDTOs = sexService.search("", 0, page * 10);

		request.setAttribute("sexList", sexDTOs);
		request.setAttribute("sizeList", sizeDTOs);
		request.setAttribute("tradeMarkList", tradeMarkDTOs);
		request.setAttribute("productList", listPro);

		request.setAttribute("sizeName", sizeDTOs);
		request.setAttribute("tradeMarkName", tradeMarkDTOs);
		request.setAttribute("page", page);
		request.setAttribute("keyword", keyword);
		return "client/products-by-category";
	}
}
