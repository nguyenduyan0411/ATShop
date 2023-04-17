package com.atteam.atshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atteam.atshop.dao.IFavouriteDAO;
import com.atteam.atshop.dao.IProductDAO;
import com.atteam.atshop.model.Account;
import com.atteam.atshop.model.Favourite;
import com.atteam.atshop.model.Product;
import com.atteam.atshop.service.IFavouriteService;

@Service
public class FavouriteServiceImpl implements IFavouriteService{

	@Autowired
	IFavouriteDAO dao;
	
	@Autowired
	IProductDAO productDao;
	
	@Override
	public Favourite findByUsernameAndProductId(String username, String productId) {
		return dao.findByUsernameAndProductId(username, productId);
	}

	@Override
	public void delete(Favourite favourite) {
		dao.delete(favourite);
	}
	
	@Override
	public Favourite create(Account account, Product product) {
		Favourite existFavourite = findByUsernameAndProductId(account.getUsername(), product.getProductId());
		if(existFavourite == null) {
			existFavourite = new Favourite();
			existFavourite.setAccount(account);
			existFavourite.setProduct(product);
			existFavourite.setIsLiked(false);
			return dao.save(existFavourite);
		}
		return existFavourite;
	}

	@Override
	public Favourite updateLikeOrUnlike(Account account, String productId) {
		Product product = productDao.findById(productId).get();
		Favourite existFavourite = findByUsernameAndProductId(account.getUsername(), product.getProductId());
		if(existFavourite.getIsLiked() == false) {
			existFavourite.setIsLiked(true);
		}else {
			existFavourite.setIsLiked(false);
		}
		Favourite updateFavourite = dao.save(existFavourite);
		return updateFavourite;
	}

	@Override
	public List<Object[]> getTotalLikesOfProduct() {
		return dao.getTotalLikesOfProduct();
	}

	@Override
	public List<Object[]> getUserInfoWithProductIsLikedByUsers(String productId) {
		return dao.getUserInfoWithProductIsLikedByUsers(productId);
	}

}
