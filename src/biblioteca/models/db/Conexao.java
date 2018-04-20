/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.models.db;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luanm
 */
public class Conexao {

	private static final String DB_IP = "localhost";
	private static final String DB_BANCO = "biblioteca";
	private static final String DB_USUARIO = "root";
	private static final String DB_SENHA = "";

	public static Connection conexao;

	// Construtor
	public Conexao() {
	}

	// Métodos
	public static Connection getConnection() {
		if (conexao == null) {
			try {
				conexao = (Connection) DriverManager.getConnection("jdbc:mysql://" + DB_IP + "/" + DB_BANCO, DB_USUARIO, DB_SENHA);
			} catch (SQLException ex) {
				System.out.println("Não foi possivel conectar ao banco de dados:\n Erro: " + ex.getErrorCode());
			}
		}
		return conexao;
	}

	public static void closeConnection() {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException ex) {
				System.out.println("Não foi possível fechar a conexão:\n Erro: " + ex.getErrorCode());
			}
		}
	}
}
