package com.atteam.atshop.rest.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atteam.atshop.model.Role;
import com.atteam.atshop.service.IRoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roles")
public class RoleRestController {
	@Autowired
	IRoleService roleService;
	
	@GetMapping
	public List<Role> getAll(){
		return roleService.findAll();
	}
}
