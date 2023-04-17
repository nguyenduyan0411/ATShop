package com.atteam.atshop.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Productid")
	private String productId;
	
	@Column(name = "Productname")
	private String productName;
	
	@Column(name = "Productprice")
	private Double productPrice;
	
	/**
	 * 	@CreationTimestamp : khi new 1 entity đẩy vào database. 
	 *                       chỗ này auto lấy thời gian của hệ thống tại thời điểm tạo entity đó
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "Productcreatedate")
	@CreationTimestamp
	private Date productCreateDate = new Date();
	
	@Column(name = "Productdescription")
	private String productDescription;
	
	@Column(name = "Productimage")
	private String productImage;
	
	@ManyToOne
	@JoinColumn(name = "Categoryid")
	Category category;
	
	@Column(name = "Productquantity")
	private Integer productQuantity;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Favourite> favourites;

}
