package com.atteam.atshop.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Categories")
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Categoryid")
	private String categoryId;
	
	@Column(name = "Categorylogo")
	private String categoryLogo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<Product> products;
}
