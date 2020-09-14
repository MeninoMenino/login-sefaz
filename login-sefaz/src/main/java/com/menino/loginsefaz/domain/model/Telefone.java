package com.menino.loginsefaz.domain.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
//Classe de Telefone
public class Telefone {
	
	@NotNull
	private int ddd;
	@NotBlank
	private String numero;
	@NotBlank
	private String tipo;
	
	public Telefone(int ddd, String numero, String tipo) {
		setDdd(ddd);
		setNumero(numero);
		setTipo(tipo);
	}
	
	public Telefone() {}
	//Getters e Setters
	
	public int getDdd() {
		return ddd;
	}
	public void setDdd(int ddd) {
		this.ddd = ddd;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
