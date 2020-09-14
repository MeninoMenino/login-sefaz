package com.menino.loginsefaz.domain.exception;

public class TokenInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TokenInvalidoException(){
		super("Token inválido");
	}
}
