package br.usjt.arqsw18.pipoca.model.entity;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Genero {
	@Id
	@NotNull
	private int id;
	
	@NotNull
	@Size(max=60, message="Tamanho m�ximo de 60 caracteres")	
	private String nome;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Genero [id=" + id + ", nome=" + nome + "]";
	}
	
}
