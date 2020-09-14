package com.menino.loginsefaz.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.menino.loginsefaz.api.dto.LoginDto;
import com.menino.loginsefaz.api.dto.UsuarioAutenticadoDto;
import com.menino.loginsefaz.domain.service.AutenticacaoService;
import com.menino.loginsefaz.domain.service.LoginService;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	AutenticacaoService autenticacaoService;
	
	@Autowired
	LoginService loginService;
	
	@GetMapping
	public String mostrarTelaLogin(ModelMap model) {
		return "login";
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public String efetuarLogin(ModelMap model, @RequestParam String email, @RequestParam String senha){
		LoginDto login = new LoginDto(email, senha); 
		UsuarioAutenticadoDto usuarioAutenticado = loginService.autenticarLogin(login);
		
		model.put("email", usuarioAutenticado.getEmail());
		model.put("nome", usuarioAutenticado.getNome());
		model.put("telefones", usuarioAutenticado.getTelefones());
		model.put("token", usuarioAutenticado.getToken());
		model.put("tipoToken", usuarioAutenticado.getTipoToken());
		return "painelPrincipal";
	}
}
