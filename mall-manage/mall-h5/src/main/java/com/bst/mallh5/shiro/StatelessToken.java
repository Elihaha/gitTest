package com.bst.mallh5.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class StatelessToken implements AuthenticationToken{
	private static final long serialVersionUID = 1L;

	private String token;

	public StatelessToken(String token){
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	@Override
	public Object getCredentials() {
		return token;
	}

	@Override
	public Object getPrincipal() {
		return token;
	}
	
}
