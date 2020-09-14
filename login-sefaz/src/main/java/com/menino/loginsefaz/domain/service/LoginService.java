package com.menino.loginsefaz.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menino.loginsefaz.api.dto.LoginDto;
import com.menino.loginsefaz.api.dto.UsuarioAutenticadoDto;
import com.menino.loginsefaz.domain.exception.EmailOuSenhaIncorretosException;
import com.menino.loginsefaz.domain.model.Usuario;
import com.menino.loginsefaz.domain.repository.UsuarioRepository;

@Service
public class LoginService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	TokenService tokenService;
	
	//Autentica o email e a senha ao tentar efetuar login
	public UsuarioAutenticadoDto autenticarLogin(LoginDto login) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(login.getEmail());
		
		if(usuario.isPresent() && usuario.get().getSenha().equals(login.getSenha())) {
			String token = tokenService.gerarToken(usuario.get());
			return UsuarioAutenticadoDto.toDto(usuario.get(), token, "Bearer ");
		} else {
			throw new EmailOuSenhaIncorretosException();
		}
	}

}
