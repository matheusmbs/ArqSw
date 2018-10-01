package br.usjt.arqsw18.pipoca.model.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw18.pipoca.model.dao.UsuarioDao;
import br.usjt.arqsw18.pipoca.model.entity.Usuario;

@Service
public class UsuarioService {
	UsuarioDao usuarioDao;
	
	public UsuarioService() {
		usuarioDao = new UsuarioDao();
	}
	
	public int logar(String username, String password) throws IOException {
		return usuarioDao.logar(username,password);
	}

}
