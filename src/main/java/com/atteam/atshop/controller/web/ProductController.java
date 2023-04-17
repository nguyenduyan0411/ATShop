package com.atteam.atshop.controller.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atteam.atshop.model.Account;
import com.atteam.atshop.model.Favourite;
import com.atteam.atshop.model.Product;
import com.atteam.atshop.service.IAccountService;
import com.atteam.atshop.service.IFavouriteService;
import com.atteam.atshop.service.IProductService;

@Controller
public class ProductController {
	
	@Autowired
	IProductService productService;
	
	@Autowired
	IAccountService accountService;
	
	@Autowired
	IFavouriteService favouriteService;
	
	// Home : Recent products
	@RequestMapping("index")
	public String home(Model model) {
		List<Product> list = productService.findRecentProducts();
		for(int i = 0; i < list.size(); ++i) {
			if(list.get(i).getProductQuantity() == 0) {
				list.remove(list.get(i));
			}
		}
		model.addAttribute("items", list);
		return "web/product/home";
	}
	
	/*
	 * 	Nếu có categoryId là lọc theo loại
	 * 	Ngược lại lọc tất cả
	 * */
	
	@RequestMapping("/product/list")
	public String list(Model model, @RequestParam("cid") Optional<String> cid) {
		if(cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			for(int i = 0; i < list.size(); ++i) {
				if(list.get(i).getProductQuantity() == 0) {
					list.remove(list.get(i));
				}
			}
			model.addAttribute("items", list);
		}else {
			List<Product> list = productService.findAll();
			for(int i = 0; i < list.size(); ++i) {
				if(list.get(i).getProductQuantity() == 0) {
					list.remove(list.get(i));
				}
			}
			model.addAttribute("items", list);
		}
		return "web/product/list";
	}	
	
	// Lọc sản phẩm theo ngày mới nhất
	@RequestMapping("/product/list/sort-date-desc")
	public String listProductSortByDateDesc(Model model) {
		List<Product> list = productService.sortByProductLatest();
		for(int i = 0; i < list.size(); ++i) {
			if(list.get(i).getProductQuantity() == 0) {
				list.remove(list.get(i));
			}
		}
		model.addAttribute("items", list);
		return "web/product/list";
	}
	
	// Lọc sản phẩm theo ngày mới nhất
	@RequestMapping("/product/list/sort-date-asc")
	public String sortByProductOldestDay(Model model) {
		List<Product> list = productService.sortByProductOldestDay();
		for(int i = 0; i < list.size(); ++i) {
			if(list.get(i).getProductQuantity() == 0) {
				list.remove(list.get(i));
			}
		}
		model.addAttribute("items", list);
		return "web/product/list";
	}
	
	// Lọc sản phẩm theo khoảng giá
	@RequestMapping("/product/list/filter")
	public String getProducts(@RequestParam(value="all", required=false) Boolean all,
							  @RequestParam(value="0-150", required=false) Boolean between0and150,
	                          @RequestParam(value="150-200", required=false) Boolean between150and200,
	                          @RequestParam(value="200-300", required=false) Boolean between200and300,
	                          @RequestParam(value="300-400", required=false) Boolean between300and400,
	                          @RequestParam(value="400-500", required=false) Boolean between400and500,
	                          Model model) {
	  List<Product> list = productService.findAll();
	  if(all != null) {
		  list = list.stream().collect(Collectors.toList());
		  for(int i = 0; i < list.size(); ++i) {
			  if(list.get(i).getProductQuantity() == 0) {
				  list.remove(list.get(i));
			  }
		  }
	  }
	  if (between0and150 != null && between0and150) {
		  list = list.stream().filter(p -> p.getProductPrice() < 150).collect(Collectors.toList());
		  for(int i = 0; i < list.size(); ++i) {
			  if(list.get(i).getProductQuantity() == 0) {
				  list.remove(list.get(i));
			  }
		  }
	  }
	  if (between150and200 != null && between150and200) {
		  list = list.stream().filter(p -> p.getProductPrice() >= 150 && p.getProductPrice() <= 200).collect(Collectors.toList());
		  for(int i = 0; i < list.size(); ++i) {
			  if(list.get(i).getProductQuantity() == 0) {
				  list.remove(list.get(i));
			  }
		  }
	  }
	  if (between200and300 != null && between200and300) {
		  list = list.stream().filter(p -> p.getProductPrice() > 200 && p.getProductPrice() <= 300).collect(Collectors.toList());
		  for(int i = 0; i < list.size(); ++i) {
			  if(list.get(i).getProductQuantity() == 0) {
				  list.remove(list.get(i));
			  }
		  }
	  }
	  if (between300and400 != null && between300and400) {
		  list = list.stream().filter(p -> p.getProductPrice() > 300 && p.getProductPrice() <= 400).collect(Collectors.toList());
		  for(int i = 0; i < list.size(); ++i) {
			  if(list.get(i).getProductQuantity() == 0) {
				  list.remove(list.get(i));
			  }
		  }
	  }
	  if (between400and500 != null && between400and500) {
		  list = list.stream().filter(p -> p.getProductPrice() > 400 && p.getProductPrice() <= 500).collect(Collectors.toList());
		  for(int i = 0; i < list.size(); ++i) {
			  if(list.get(i).getProductQuantity() == 0) {
				  list.remove(list.get(i));
			  }
		  }
	  }
	  model.addAttribute("items", list);
	  return "web/product/list";
	}
	
	// product detail
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, HttpSession session, @PathVariable("id") String id) {
		Product product = productService.findById(id);
		model.addAttribute("item", product);
		/*
		 *		Khi người dùng đã đăng nhập, ở trạng thái trang index. 
		 *		Nếu click vào sp xem, ghi lại favourite lần đầu tiên. Ngược lại nếu xem lần 2 thì không cần ghi nữa
		 */

		@SuppressWarnings("unchecked")
		Map<String, Object> authentication = (Map<String, Object>) session.getAttribute("authentication");
		if(authentication != null) {
			Account account = (Account) authentication.get("user");
			Favourite favourite = favouriteService.create(account, product);
			model.addAttribute("flagLikedBtn", favourite.getIsLiked());
		}
		return "web/product/detail";
	}
	
	// Search by ProductName or productId
	@RequestMapping("/product/list/search")
	public String searchProductByNameOrId(@RequestParam("keyword") String kw, @RequestParam("keyword") String kw1, Model model) {
		List<Product> products = productService.searchByProductNameOrId(kw, kw1);
		for(int i = 0; i < products.size(); ++i) {
			if(products.get(i).getProductQuantity() == 0) {
				products.remove(products.get(i));
			}
		}
		if(kw.equals("")) {
			model.addAttribute("message", "Please input keyword to find product");
			model.addAttribute("items", productService.findAll());
			return "web/product/list";
		}else if(products.isEmpty()) {
			model.addAttribute("message", "The product not found");
			model.addAttribute("items", productService.findAll());
			return "web/product/list";
		}
		model.addAttribute("items", products);
		return "web/product/list";
	}
	
}
