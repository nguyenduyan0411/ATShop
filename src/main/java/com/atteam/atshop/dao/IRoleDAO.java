package com.atteam.atshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atteam.atshop.model.Role;

public interface IRoleDAO extends JpaRepository<Role, String>{

}
