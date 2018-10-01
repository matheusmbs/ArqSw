package br.usjt.arqsw18.pipoca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.entity.Genero;
import br.usjt.arqsw18.pipoca.model.service.FilmeService;
import br.usjt.arqsw18.pipoca.model.service.GeneroService;

@RestController
public class ControllerRest {
	@Autowired
	private FilmeService fService;
	@Autowired
	private GeneroService gService;


	@RequestMapping("/novo_filme_rest")
	public @ResponseBody List<Genero> novo(Model model, HttpSession session) throws IOException {
		return gService.listarGeneros(); 
	}

	@RequestMapping("/criar_filme_rest")
	public @ResponseBody Filme criarFilme(@Valid Filme filme, BindingResult erros, Model model) throws IOException {
				Genero genero = new Genero();
				genero.setId(filme.getGenero().getId());
				genero.setNome(gService.buscarGenero(genero.getId()).getNome());
				filme.setGenero(genero);
				return fService.inserirFilme(filme);

	}


	@RequestMapping("/listar_filmes_rest")
	public @ResponseBody List<Filme> listarFilmes(HttpSession session, Model model, String chave) throws IOException {

			ArrayList<Filme> lista;
			if (chave != null && chave.length() > 0) {
				lista = (ArrayList<Filme>) fService.listarFilmes(chave);
			} else {
				lista = (ArrayList<Filme>) fService.listarFilmes();
			}
			return lista;

	}
	
	@RequestMapping("/atualizar_filme_rest")
	public @ResponseBody Filme atualizarFilme(Filme filme, Model model) throws IOException {
			return fService.atualizarFilme(filme);

	}
	
	@RequestMapping("/excluir_filme")
	public List<Filme> ExcluirFilme(HttpServletRequest request, HttpSession session, Model model, String chave) throws IOException {
		chave = request.getParameter("id");
		int id = Integer.parseInt(chave);

		fService.excluirFilme(id);
		return fService.listarFilmes();

	}
}

