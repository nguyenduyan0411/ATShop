package com.atteam.atshop;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.atteam.atshop.model.Account;
import com.atteam.atshop.service.IAccountService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	IAccountService accountService;
	
	@Autowired
	BCryptPasswordEncoder pe;
	
	@Autowired
	HttpSession session;
	
	// Cung cấp nguồn dữ liệu đăng nhập
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			try {
				Account user = accountService.findById(username);
				// pe.encode : mã hóa password
				String password = pe.encode(user.getPassword());
				System.out.println("Password: " + password);
				
				String[] roles = user.getAuthorities().stream()
						.map(er -> er.getRole().getRoleId())
						.collect(Collectors.toList()).toArray(new String[0]);
				Map<String, Object> authentication = new HashMap<>();
				authentication.put("user", user);
				byte[] token = (username + ":" + user.getPassword()).getBytes();
				authentication.put("token", "Basic " + Base64.getEncoder().encodeToString(token));
				session.setAttribute("authentication", authentication);
				//session.setAttribute("user", user);
				
				return User.withUsername(username).password(password).roles(roles).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + "not found!");
			}
		});
	}
	
	// Phân quyền sử dụng
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/order/**").hasAnyRole("STAF", "CUST")                           // với url bắt đầu /order thì yêu cầu phải đăng nhập
		.antMatchers("/favourite/**").authenticated()
		.antMatchers("/admin/**").hasAnyRole("STAF", "DIRE")
		.antMatchers("/rest/authorities").hasRole("DIRE")
		.anyRequest().permitAll();
		
		http.formLogin()
		.loginPage("/security/login/form")
		.loginProcessingUrl("/security/login")
		.defaultSuccessUrl("/security/login/success", false)                // false: đăng nhập thành công không nhất thiết phải quay về success
		.failureUrl("/security/login/error");
		
		http.rememberMe()
		.tokenValiditySeconds(86400);                                       // 86400 giây, tức là 24 giờ
		
		/*
		 *	sau khi đăng nhập 
		 *	nếu truy cập địa chỉ chưa được cấp quyền thì chuyển sang unauthoried
		 *	localhost:8080/security/unauthoried
		 *
		 * */
		http.exceptionHandling()
		.accessDeniedPage("/security/unauthoried");
		
		http.logout()
		.logoutUrl("/security/logoff")
		.logoutSuccessUrl("/security/logoff/success");
	}
	
	// Cơ chế mã hóa mật khẩu
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// Cho phép truy xuất REST API từ bên ngoài (domain khác)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
