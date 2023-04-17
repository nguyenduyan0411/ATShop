package com.atteam.atshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atteam.atshop.model.Product;

public interface IProductDAO extends JpaRepository<Product, String>{
	@Query("select p from Product p where p.category.categoryId = ?1")
	List<Product> findByCategoryId(String cid);
	
	// Lọc sản phẩm theo ngày đăng mới nhất
	@Query("select p from Product p order by p.productCreateDate desc")
	List<Product> sortByProductLatest();
	
	// Lọc sản phẩm theo ngày cũ nhất
	@Query("select p from Product p order by p.productCreateDate")
	List<Product> sortByProductOldestDay();
	
	// Lọc 8 sản phẩm theo ngày đăng mới nhất. Hiển thị trên trang HOME
	@Query(value = "select top 8 * from Products order by ProductCreateDate desc", nativeQuery = true)
	List<Product> findRecentProducts();
	
	// Search product name or product id
	@Query("SELECT distinct p from Product p WHERE p.productName like %?1% or p.productId like %?2%")
	List<Product> searchByProductNameOrId(String productName, String productId);
	
	// Hiển thị các sản phẩm user đã thích
	@Query(value = "select * from Products p inner join Favourites f on p.ProductId = f.ProductId where Username = ?1 and IsLiked = 1", nativeQuery = true)
	List<Product> findAllProductCustomerLike(String username);
	
}
