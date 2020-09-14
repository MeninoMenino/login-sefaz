package com.menino.loginsefaz.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.menino.loginsefaz.domain.exception.EmailJaCadastradoException;
import com.menino.loginsefaz.domain.exception.TokenInvalidoException;
import com.menino.loginsefaz.domain.model.Telefone;
import com.menino.loginsefaz.domain.model.Usuario;
import com.menino.loginsefaz.domain.repository.UsuarioRepository;
import com.menino.loginsefaz.domain.service.AutenticacaoService;
import com.menino.loginsefaz.domain.service.TokenService;
import com.menino.loginsefaz.domain.service.UsuarioCadastroService;

//Classe controladora de usuários, mapeando o caminho /usuarios
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	UsuarioCadastroService usuarioCadastroService;

	@Autowired
	TokenService tokenService;

	@Autowired
	AutenticacaoService autenticacaoService;

	//Lista todos os usuários
	//Precisa de um token válido
	@GetMapping
	public List<Usuario> listar(@RequestHeader String Authorization){
		if (autenticacaoService.validarToken(Authorization)) {
			return usuarioRepository.findAll();
		} else {
			throw new TokenInvalidoException();
		}
	}

	//Busca usuário por id
	//Precisa de um token válido
	@GetMapping("/{email}")
	public ResponseEntity<Usuario> buscar(@PathVariable String email, 
			@RequestHeader String Authorization){
		if (autenticacaoService.validarToken(Authorization)) {
			Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

			/*Caso não encontre o usuário, retorna status 404
				  Caso encontre o usuário, retorna status 200 e o registro */
			if(!usuario.isPresent()) {
				return ResponseEntity.notFound().build();	
			} else {
				return ResponseEntity.ok(usuario.get());
			}
		} else {
			throw new TokenInvalidoException();
		}
	}

	//Insere um novo usuário
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String inserir(@RequestParam String email, @RequestParam String nome, @RequestParam String senha, @RequestParam("ddd") List<Integer> ddd, @RequestParam("numero") List<String> numero, @RequestParam("tipo") List<String> tipo){

		List<Telefone> telefones = new ArrayList<Telefone>();

		telefones = usuarioCadastroService.adicionarTelefones(ddd, numero, tipo);

		Usuario usuario = new Usuario(email, nome, senha, telefones);

		Optional<Usuario> emailExistente = usuarioRepository.findByEmail(usuario.getEmail());
		if(emailExistente.isPresent() && usuario.getEmail().equals(emailExistente.get().getEmail())) {
			throw new EmailJaCadastradoException();
		} else {
			usuarioRepository.save(usuario);
			return "login";
		}
	}

	//Altera os dados de um usuário existente
	//Precisa de um token válido
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> alterar(@Valid @RequestBody Usuario usuario, 
			@PathVariable Long id,
			@RequestHeader String Authorization){

		if (autenticacaoService.validarToken(Authorization)) {
			//Confere se o id informado existe
			if(!usuarioRepository.existsById(id)) {
				return ResponseEntity.notFound().build();
			} else {
				usuario.setId(id);
				return ResponseEntity.ok(usuarioCadastroService.validarEmail(usuario));
			}
		} else {
			throw new TokenInvalidoException();
		}
	}

	//Deleta o registro do usuário
	//Precisa de um token válido
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id,
			@RequestHeader String Authorization) {
		if (autenticacaoService.validarToken(Authorization)) {
			if(!usuarioRepository.existsById(id)) {
				return ResponseEntity.notFound().build();
			} else {
				usuarioRepository.deleteById(id);
				return ResponseEntity.noContent().build();
			}
		} else {
			throw new TokenInvalidoException();
		}
	}
}
