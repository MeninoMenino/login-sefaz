package com.menino.loginsefaz.domain.exception;

public class TokenExpiradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TokenExpiradoException() {
		super("Token de acesso expirado");
	}
	
}
