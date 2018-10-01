package br.usjt.arqsw18.pipoca.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.entity.Genero;
import br.usjt.arqsw18.pipoca.model.service.FilmeService;
import br.usjt.arqsw18.pipoca.model.service.GeneroService;

@Controller
public class ManterFilmesController {
	private FilmeService fService;
	private GeneroService gService;

	public ManterFilmesController() {
		fService = new FilmeService();
		gService = new GeneroService();
	}

	// request.setCharacterEncoding("UTF-8");

	/*
	 * String titulo = request.getParameter("titulo"); String descricao =
	 * request.getParameter("descricao"); String diretor =
	 * request.getParameter("diretor"); String posterPath =
	 * request.getParameter("posterPath"); String popularidade =
	 * request.getParameter("popularidade") == null ||
	 * request.getParameter("popularidade").length() == 0 ? "0" :
	 * request.getParameter("popularidade"); String dataLancamento =
	 * request.getParameter("dataLancamento") == null ||
	 * request.getParameter("dataLancamento").length() == 0 ? "" :
	 * request.getParameter("dataLancamento"); String idGenero =
	 * request.getParameter("genero.id"); String chave =
	 * request.getParameter("data[search]");
	 */

	@RequestMapping("index")
	public String iniciar() {
		return "index";
	}
	
	@RequestMapping("/novo_filme")
	public String novo(Model model) {
		try {
			gService = new GeneroService();
			ArrayList<Genero> generos = gService.listarGeneros();
			model.addAttribute("generos", generos);
			return "CriarFilme";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}

	@RequestMapping("/criar_filme")
	public String criarFilme(Filme filme, Model model) {
		try {
			
			Genero genero = new Genero();
			genero.setId(filme.getGenero().getId());
			genero.setNome(gService.buscarGenero(genero.getId()).getNome());
			filme.setGenero(genero);

			filme = fService.inserirFilme(filme);

			model.addAttribute("filme", filme);

			return "VisualizarFilme";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}

	@RequestMapping("/reiniciar_lista")
	public String reiniciarLista(HttpSession session) {
		session.setAttribute("lista", null);
		return "ListarFilmes";
	}

	@RequestMapping("/listar_filmes")
	public String listarFilmes(HttpServletRequest request, HttpSession session, Model model, String chave) {
		chave = request.getParameter("chave");
		
		try {
			//HttpSession session = ((HttpServletRequest) model).getSession();

			ArrayList<Filme> lista;
			if (chave != null && chave.length() > 0) {
				lista = fService.listarFilmes(chave);
			} else {
				lista = fService.listarFilmes();
			}
			session.setAttribute("lista", lista);
			return "ListarFilmes";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}
	
	@RequestMapping("/visualizar_filme")
	public String buscarFilme(HttpServletRequest request, HttpSession session, Model model, String chave) {
		chave = request.getParameter("id");
		int id = Integer.parseInt(chave);
		try {
			
			Filme filme = fService.buscarFilme(id);
			session.setAttribute("filme", filme);
			return "VisualizarFilme";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}
	
	@RequestMapping("/excluir_filme")
	public String ExcluirFilme(HttpServletRequest request, HttpSession session, Model model, String chave) {
		chave = request.getParameter("id");
		int id = Integer.parseInt(chave);
		try {
			
			fService.excluirFilme(id);
			ArrayList<Filme> lista;
			lista = fService.listarFilmes();
			session.setAttribute("lista", lista);
			return "ListarFilmes";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}
	
	@RequestMapping("/alterar_filme")
	public String abrirAtualizarFilme(HttpServletRequest request, HttpSession session, Model model, String chave) {
		chave = request.getParameter("id");
		int id = Integer.parseInt(chave);
		try {
			gService = new GeneroService();
			ArrayList<Genero> generos = gService.listarGeneros();
			model.addAttribute("generos", generos);
			Filme filme = fService.buscarFilme(id);
			session.setAttribute("filme", filme);
			return "AtualizarFilme";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}
	
	@RequestMapping("/atualizar_filme")
	public String atualizarFilme(Filme filme, Model model) {
		try {

			filme = fService.atualizarFilme(filme);

			model.addAttribute("filme", filme);

			return "VisualizarFilme";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}
}
