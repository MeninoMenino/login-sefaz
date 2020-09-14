package com.menino.loginsefaz.api.dto;

public class LoginDto {
	
	private String email;

	private String senha;
	
	
	public LoginDto(String email, String senha) {
		setEmail(email);
		setSenha(senha);
	}
	
	public LoginDto() {}
	
	//Getters e Setters
	
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}
}