package com.menino.loginsefaz.api.dto;

import java.util.List;

import com.menino.loginsefaz.domain.model.Telefone;
import com.menino.loginsefaz.domain.model.Usuario;

public class UsuarioAutenticadoDto {
	
	private Long id;
	
	private String email;
	
	private String nome;
	
	private List<Telefone> telefones;
	
	private String token;
	private String tipoToken;
	
	//Função que converte a entidade usuário para dto
	public static UsuarioAutenticadoDto toDto(Usuario usuario, String token, String tipoToken) {
		return new UsuarioAutenticadoDto(usuario.getId(), usuario.getEmail(), usuario.getNome(), usuario.getTelefones(), token, tipoToken);
	}
	
	//Construtores
	public UsuarioAutenticadoDto(Long id, String email, String nome, List<Telefone> telefones, String token, String tipoToken){
		setId(id);
		setEmail(email);
		setNome(nome);
		setTelefones(telefones);
		setToken(token);
		setTipoToken(tipoToken);
	}
	
	public UsuarioAutenticadoDto() {}
	
	//-----------------------------------------
	
	//Getters e Setters
	
		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public List<Telefone> getTelefones() {
			return telefones;
		}

		public void setTelefones(List<Telefone> telefones) {
			this.telefones = telefones;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getTipoToken() {
			return tipoToken;
		}

		public void setTipoToken(String tipoToken) {
			this.tipoToken = tipoToken;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
}