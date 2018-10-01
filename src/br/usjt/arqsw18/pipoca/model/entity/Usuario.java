package br.usjt.arqsw18.pipoca.model.entity;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Usuario {
	@Id
	@NotNull
	private int idUsuario;
	
	@NotNull
	@Size(max=45, message="Tamanho máximo de 45 caracteres")	
	private String username;
	
	@NotNull
	@Size(max=45, message="Tamanho máximo de 45 caracteres")
	private String password;
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setId(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
