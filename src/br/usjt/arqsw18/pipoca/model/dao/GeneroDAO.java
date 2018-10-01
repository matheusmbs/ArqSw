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

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import br.usjt.arqsw18.pipoca.model.entity.Genero;
@Repository
public class GeneroDAO {
	@PersistenceContext
	EntityManager manager;
	
	public Genero buscarGenero(int id) throws IOException {
		Genero genero = new Genero();
		genero.setId(id);
		return manager.find(Genero.class, genero);
	}

	public List<Genero> listarGeneros() throws IOException {
		return manager.createQuery("select g from Genero g").getResultList();
	}
}
