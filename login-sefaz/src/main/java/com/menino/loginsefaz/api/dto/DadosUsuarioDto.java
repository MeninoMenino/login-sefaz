package com.menino.loginsefaz.api.dto;

import java.util.List;

import com.menino.loginsefaz.domain.model.Telefone;

public class DadosUsuarioDto {
	private String email;
	private String nome;
	private List<Telefone> telefones;
	
	public DadosUsuarioDto(String email, String nome, List<Telefone> telefones) {
		setEmail(email);
		setNome(nome);
		setTelefones(telefones);
	}
	
	public DadosUsuarioDto(){}
	
	
	//Getters e Setters
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
}
