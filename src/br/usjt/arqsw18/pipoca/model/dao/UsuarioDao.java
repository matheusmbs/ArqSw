package br.usjt.arqsw18.pipoca.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.usjt.arqsw18.pipoca.model.entity.Genero;
import br.usjt.arqsw18.pipoca.model.entity.Usuario;

public class UsuarioDao {

	public int logar(String username, String password) throws IOException {
		Usuario usuario_logado;
		String sql = "select username, password from usuario where username=? and password = ?";

		try (Connection conn = ConnectionFactory.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setString(1, username);
			pst.setString(2, password);
			try (ResultSet rs = pst.executeQuery();) {
				if(rs.next()) {
					if(rs.getString("username") != null) {
						
						return 2;
					}else {
						return 1;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		
		return 1;
	}

}
