package com.atteam.atshop.service;

import java.util.List;

import com.atteam.atshop.model.Account;
import com.atteam.atshop.model.Favourite;
import com.atteam.atshop.model.Product;

public interface IFavouriteService {
	
	Favourite findByUsernameAndProductId(String username, String productId);
	
	void delete(Favourite favourite);

	Favourite create(Account account, Product product);
	
	Favourite updateLikeOrUnlike(Account account, String productId);

	// Bởi vì câu truy vấn trả về một số lượng cột không xác định trước, nên là sử dụng kiểu dữ liệu Object[] để chứa kết quả trả về.
	List<Object[]> getTotalLikesOfProduct();
	
	// Hiển thị thông tin những người đã like sp theo mã sản phẩm
	List<Object[]> getUserInfoWithProductIsLikedByUsers(String productId);
}
