package com.atteam.atshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.atteam.atshop.model.Favourite;

@Repository
public interface IFavouriteDAO extends JpaRepository<Favourite, Integer>{
	
	@Query(value = "select * from Favourites where Username = ?1 and ProductId = ?2", nativeQuery = true)
	Favourite findByUsernameAndProductId(String username, String productId);

	/*		Chức năng:
	 * 					Thống kê sản phẩm được bao nhiêu lượt like
	 * 		Nghiệp vụ:
	 * 					Bởi vì câu truy vấn trả về một số lượng cột không xác định trước
	 * 					nên là sử dụng kiểu dữ liệu Object[] để chứa kết quả trả về.
	 * 
	 */
	@Query(value = "select p.CategoryId , p.ProductId, p.ProductName, p.ProductImage, sum(cast(f.IsLiked as int)) as [Total like] from Favourites f inner join Products p on f.ProductId = p.ProductId"
			+ " group by p.CategoryId, p.ProductId, p.ProductName, p.ProductImage, f.IsLiked order by sum(cast(f.IsLiked as int)) desc", nativeQuery = true)
	List<Object[]> getTotalLikesOfProduct();
	
	// Hiển thị thông tin những người đã like sp theo mã sản phẩm
	@Query(value = "select ac.UserFullname, ac.UserEmail from Accounts ac inner join Favourites f on ac.Username = f.Username"
			+ " inner join Products p on f.ProductId = p.ProductId"
			+ " where p.ProductId = ?1 and f.IsLiked = 1", nativeQuery = true)
	List<Object[]> getUserInfoWithProductIsLikedByUsers(String productId);
}
