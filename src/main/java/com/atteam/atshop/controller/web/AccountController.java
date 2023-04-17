package com.atteam.atshop.controller.web;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atteam.atshop.model.Account;
import com.atteam.atshop.model.MailInfo;
import com.atteam.atshop.service.IAccountService;
import com.atteam.atshop.service.IEmailService;

@Controller
public class AccountController {
	
	@Autowired
	IAccountService accountService;
	
	@Autowired
	IEmailService emailService;
	
	@GetMapping("/account/register")
	public String register(Model model) {
		model.addAttribute("account", new Account());
		return "web/security/register";
	}
	
    @PostMapping("/account/register")
    public String registerUser(@ModelAttribute Account account) {
        accountService.create(account);     
        return "web/security/register";
    }
    
    @GetMapping("/account/forgot-pass")
    public String forgotPass() {
    	return "web/security/forgot-pass";
    }
    
    // send email
    @SuppressWarnings("unused")
	@PostMapping("/account/forgot-pass")
    public String getNewPassword(@RequestParam("email") String email) {
    	Account account = accountService.resetPassword(email);
    	System.out.println(account.getPassword());
    	if(account != null) {
    		MailInfo mail = new MailInfo();
    		mail.setTo(email);
    		mail.setSubject("New password");
    		mail.setBody("Dear " + account.getUserFullname() + ", your new password here: " + account.getPassword());
    		try {
				emailService.send(mail);
				return "web/security/sign-in";
			} catch (MessagingException e) {
				System.out.println("aloooo");
				return "web/security/forgot-pass";
			}
    	}
    	return "web/security/forgot-pass";
    }
	
}
