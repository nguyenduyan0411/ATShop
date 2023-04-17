package com.atteam.atshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {
	private String from = "an.chantroimoi@gmail.com";
	private String to;
	private String subject;
	private String body;
	
	public MailInfo(String to, String subject, String body) {
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
