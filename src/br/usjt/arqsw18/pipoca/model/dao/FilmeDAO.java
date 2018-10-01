package br.usjt.arqsw18.pipoca.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.entity.Genero;
@Repository
public class FilmeDAO {
	@PersistenceContext
	EntityManager manager;
	
	public int inserirFilme(Filme filme) throws IOException {
		manager.persist(filme);
		return filme.getId();
	}

	public Filme buscarFilme(int id) throws IOException{
		return manager.find(Filme.class, id);
	}

	public List<Filme> listarFilmes(String chave) throws IOException {
		String sql = "select f.id, f.titulo, f.descricao, f.diretor, f.posterpath, "
				+ "f. popularidade, f.data_lancamento, f.id_genero, g.nome "
				+ "from filme f, genero g "
				+ "where f.id_genero = g.id and upper(f.titulo) like ?";
		return manager.createQuery(sql).getResultList();
	}
	
	public List<Filme> listarFilmes() throws IOException {

		String jpql = "select f from filme f";

		Query query = manager.createQuery(jpql);

		List<Filme> result = query.getResultList();
		return result;
	}


	public void excluirFilme(int id) throws IOException {
		manager.remove(manager.find(Filme.class, id));
	}
	
	public Filme atualizarFilme(Filme filme) throws IOException {
		manager.merge(filme);
		return filme;
	}

}
