package com.menino.loginsefaz.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menino.loginsefaz.domain.exception.EmailJaCadastradoException;
import com.menino.loginsefaz.domain.model.Telefone;
import com.menino.loginsefaz.domain.model.Usuario;
import com.menino.loginsefaz.domain.repository.UsuarioRepository;

@Service
public class UsuarioCadastroService {

	@Autowired
	UsuarioRepository usuarioRepository;

	//Adiciona os telefones recebidos em uma lista de telefones
	public List<Telefone> adicionarTelefones(List<Integer> ddd, List<String> numero, List<String> tipo){
		List<Telefone> telefones = new ArrayList<Telefone>();
		for(int i = 0; i < ddd.size(); i++) {
			if(ddd.get(i) == null) {
				telefones.add(new Telefone(1, "", ""));
			} else {
				System.out.println(ddd.get(i));
				telefones.add(new Telefone(ddd.get(i), numero.get(i), tipo.get(i)));
			}
		}
		return telefones;
	}

	//Valida se o email informado já está cadastrado em outro registro
	public Usuario validarEmail(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		if(usuarioExistente.isPresent() && usuarioExistente.get().getId() != usuario.getId()) {
			throw new EmailJaCadastradoException();
		} else {
			return salvar(usuario);
		}
	}

	//Caso passe pela validação, insere os dados no banco
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
}
