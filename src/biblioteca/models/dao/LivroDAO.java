/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.models.dao;

import biblioteca.models.Livro;
import biblioteca.models.db.Conexao;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luanm
 */
public class LivroDAO {

	// Atributos
	private final Connection conexao;
	private PreparedStatement declaracao;
	private final String tabelaLivros = "livros";
	
	// Construtor
	public LivroDAO() {
		this.conexao = Conexao.getConnection();
	}

	public boolean inserirLivro(Livro livro) {

		String inserirLivro = "INSERT INTO " + tabelaLivros + " (titulo, autor, categoria, status, ano, isbn, edicao) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			// Inserir registro de livro e armazenar o id
			declaracao = conexao.prepareStatement(inserirLivro, Statement.RETURN_GENERATED_KEYS);
			declaracao.setString(1, livro.getTitulo());
			declaracao.setString(2, livro.getAutor());
			declaracao.setString(3, livro.getCategoria());
			declaracao.setString(4, livro.getStatus());
			declaracao.setInt(5, livro.getAno());
			declaracao.setInt(6, livro.getIsbn());
			declaracao.setInt(7, livro.getEdicao());
			int idLivro = inserirRegistro();


		} catch (SQLException ex) {
			System.out.println("Não foi possível gerar a declaração.\nErro: " + ex.getErrorCode());
			return false;
		}
		
		return true;
	}
	
	public ArrayList<Livro> consultarLivros(String coluna, String valor) {
		
		ArrayList<Livro> livros = new ArrayList<>();
		String query = "SELECT * FROM " + tabelaLivros + " WHERE " + coluna + " LIKE '%" + valor + "%'";
		
		try {
			declaracao = conexao.prepareStatement(query);
			ResultSet res = declaracao.executeQuery(); 

			while (res.next()) {
				Livro livro = new Livro();
				livro.setId(res.getInt("id"));
				livro.setTitulo(res.getString("titulo"));
				livro.setAutor(res.getString("autor"));
				livro.setCategoria(res.getString("categoria"));
				livro.setStatus(res.getString("status"));
				livro.setAno(res.getInt("ano"));
				livro.setIsbn(res.getInt("isbn"));
				livro.setEdicao(res.getInt("edicao"));
                                livro.setEmprestimo(res.getInt("id_emprestimo"));
				livros.add(livro);
			} 
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar consultar livros.\nErro: " + ex.getErrorCode());
		}
		return livros;
	}
	
	public ArrayList<Livro> listarLivros() {
		
		ArrayList<Livro> livros = new ArrayList<>();
		String query = "SELECT * FROM " + tabelaLivros;
		
		try {
			declaracao = conexao.prepareStatement(query);
			ResultSet res = declaracao.executeQuery(); 

			while (res.next()) {
				Livro livro = new Livro();
				livro.setId(res.getInt("id"));
				livro.setTitulo(res.getString("titulo"));
				livro.setAutor(res.getString("autor"));
				livro.setCategoria(res.getString("categoria"));
				livro.setStatus(res.getString("status"));
				livro.setAno(res.getInt("ano"));
				livro.setIsbn(res.getInt("isbn"));
				livro.setEdicao(res.getInt("edicao"));
                                livro.setEmprestimo(res.getInt("id_emprestimo"));
				livros.add(livro);
			} 
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar consultar livros.\nErro: " + ex.getErrorCode());
		}
		return livros;
	}

	public Livro consultarLivro(String coluna, String valor) {
		
		Livro livro = null;
		String query = "SELECT * FROM " + tabelaLivros + " WHERE " + coluna + "='" + valor + "' LIMIT 1";
		
		try {
			declaracao = conexao.prepareStatement(query);
			ResultSet res = declaracao.executeQuery(); 

			if (res.next()) {
				livro = new Livro();
				livro.setId(res.getInt("id"));
				livro.setTitulo(res.getString("titulo"));
				livro.setAutor(res.getString("autor"));
				livro.setCategoria(res.getString("categoria"));
				livro.setStatus(res.getString("status"));
				livro.setAno(res.getInt("ano"));
				livro.setIsbn(res.getInt("isbn"));
				livro.setEdicao(res.getInt("edicao"));
                                livro.setEmprestimo(res.getInt("id_emprestimo"));
			} else {
				System.out.println("Não foi possível localizar o Livro com " + coluna + ": " + valor);
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar consultar livro.\nErro: " + ex.getErrorCode());
		}
		return livro;
	}
	
	public boolean atualizarLivro(Livro livro){
		
		boolean resAtualizacao = false;
		String updateLivro = "UPDATE " + tabelaLivros + " SET titulo = ?, autor = ?, categoria = ?, status = ?, ano = ?, isbn = ?, edicao = ?, id_emprestimo = ?  WHERE id = ?";
		
		try {
			// Atualizar dados pessoais
			declaracao = conexao.prepareStatement(updateLivro);
			declaracao.setString(1, livro.getTitulo());
			declaracao.setString(2, livro.getAutor());
			declaracao.setString(3, livro.getCategoria());
			declaracao.setString(4, livro.getStatus());
			declaracao.setInt(5, livro.getAno());
			declaracao.setInt(6, livro.getIsbn());
			declaracao.setInt(7, livro.getEdicao());
			if(livro.emprestado()) 
				declaracao.setInt(8, livro.getEmprestimo());
			else
				declaracao.setNull(8, java.sql.Types.NULL);
			declaracao.setInt(9, livro.getId());
			
			resAtualizacao = declaracao.executeUpdate() != 0;
			
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar atualizar livro.\nErro: " + ex.getErrorCode());
		}
		return resAtualizacao;
	} 

	public boolean removerLivro(Livro livro){
		return removerLivro(livro.getId());
	}
	
	public boolean removerLivro(int id) {
		try {
			String query = "DELETE FROM " + tabelaLivros + " WHERE id = ?";
			declaracao = conexao.prepareStatement(query);
			declaracao.setInt(1, id);
			return declaracao.executeUpdate() != 0;
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar remover livro.\nErro: " + ex.getErrorCode());
		}
		return false;
	}

	private int inserirRegistro() throws SQLException {
		declaracao.executeUpdate();
		ResultSet res = declaracao.getGeneratedKeys();
		res.next();
		return res.getInt(1);
	}

}
