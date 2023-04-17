package com.atteam.atshop.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atteam.atshop.model.Product;
import com.atteam.atshop.service.IProductService;

@Controller
public class FavouriteController {
	
	@Autowired
	IProductService productService;
	
	@RequestMapping("/favourite/view")
	public String view(Model model, HttpServletRequest request, @RequestParam(defaultValue = "1") int page) {
		String username = request.getRemoteUser();
		List<Product> list = productService.findAllProductCustomerLike(username);
		
		if(list.isEmpty()) {
			int maxPages = 0;
			model.addAttribute("favourite", list);
			model.addAttribute("currentPage", page);
			model.addAttribute("maxPages", maxPages);
			return "web/favourite/view";
		}

		for(int i = 0; i < list.size(); ++i) {
			if(list.get(i).getProductQuantity() == 0) {
				list.remove(list.get(i));
			}
		}
		System.out.println("List: " + list.size());
		/*
		 * 		pageSize   : số lượng sản phẩm trên 1 trang
		 * 		maxPages   : Tổng số trang
		 * 		startIndex : chỉ số bắt đầu của sản phẩm cần hiển thị trên trang hiện tại
		 */
		
		int pageSize = 4; // Kích thước trang
		int maxPages = (int) Math.ceil((double) list.size() / pageSize); 

		int startIndex = (page - 1) * pageSize;

		startIndex = Math.min(startIndex, list.size()); // Đảm bảo không vượt quá số lượng sản phẩm
		int endIndex = Math.min(startIndex + pageSize, list.size()); // Chỉ số kết thúc của sản phẩm cần hiển thị trên trang hiện tại
 
		// Kiểm tra và xử lý lỗi
	    if (startIndex == 0) {
	        startIndex = 0;
	    }

	    if (endIndex > list.size()) {
	        endIndex = list.size();
	    }   
	    

		// dssp cần hiển thị
		List<Product> productsOnPage = list.subList(startIndex, endIndex); // Danh sách sản phẩm trên trang hiện tại
		model.addAttribute("favourite", productsOnPage);
		model.addAttribute("currentPage", page);
		model.addAttribute("maxPages", maxPages);

		return "web/favourite/view";
	}
}
