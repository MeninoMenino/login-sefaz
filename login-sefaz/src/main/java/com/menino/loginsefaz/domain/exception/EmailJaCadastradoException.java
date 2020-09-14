package com.menino.loginsefaz.domain.exception;

//Classe de erro de atualização
public class EmailJaCadastradoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public EmailJaCadastradoException(){
		super("E-mail já cadastrado!");
	}
}
