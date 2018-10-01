package br.usjt.arqsw18.pipoca.model.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw18.pipoca.model.dao.FilmeDAO;
import br.usjt.arqsw18.pipoca.model.entity.Filme;
@Service
public class FilmeService {
	private FilmeDAO dao;
	public FilmeService() {
		dao = new FilmeDAO();
	}
	
	public Filme buscarFilme(int id) throws IOException{
		return dao.buscarFilme(id);
	}
	
	public Filme inserirFilme(Filme filme) throws IOException {
		int id = dao.inserirFilme(filme);
		filme.setId(id);
		return filme;
	}

	public ArrayList<Filme> listarFilmes(String chave) throws IOException{
		return dao.listarFilmes(chave);
	}

	public ArrayList<Filme> listarFilmes() throws IOException{
		return dao.listarFilmes();
	}
	
	public void excluirFilme(int id) throws IOException {
		dao.excluirFilme(id);
	}

	public Filme atualizarFilme(Filme filme) throws IOException {
		return dao.atualizarFilme(filme);
	}

}
