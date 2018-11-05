package br.usjt.arqsw18.pipoca.controller;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw18.pipoca.model.entity.Usuario;
import br.usjt.arqsw18.pipoca.model.service.FilmeService;
import br.usjt.arqsw18.pipoca.model.service.GeneroService;
import br.usjt.arqsw18.pipoca.model.service.UsuarioService;

@Controller
public class UsuarioController {
	private UsuarioService usuarioService;
	
	public UsuarioController() {
		usuarioService = new UsuarioService();
	}
	
	@RequestMapping("/logar")
	public String logar(HttpServletRequest request, Model model) throws IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		int validacao = usuarioService.logar(username, password);
		
		if(validacao == 2) {
			return "index";
		}else {
			String login = "1";
			model.addAttribute("login", login);
			return "login";
		}
		
	}
}
