/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.models.dao;

import biblioteca.models.Emprestimo;
import biblioteca.models.Livro;
import biblioteca.models.db.Conexao;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author luanm
 */
public class EmprestimoDAO {

	// Atributos
	private final Connection conexao;
	private PreparedStatement declaracao;
	private final String tabelaEmprestimos = "emprestimos";
	private final String tabelaLivros = "livros";
	
	// Construtor
	public EmprestimoDAO() {
		this.conexao = Conexao.getConnection();
	}

	public boolean inserirEmprestimo(Emprestimo emprestimo) {

		String inserirEmprestimo = "INSERT INTO " + tabelaEmprestimos + " (id_funcionario, id_leitor, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";
		
		try {
			// Inserir registro de emprestimo e armazenar o id
			declaracao = conexao.prepareStatement(inserirEmprestimo, Statement.RETURN_GENERATED_KEYS);
			declaracao.setInt(1, emprestimo.getFuncionario().getId());
			declaracao.setInt(2, emprestimo.getLeitor().getId());
			declaracao.setDate(3, new java.sql.Date(emprestimo.getDataDevolucao().getTime()));
			declaracao.setDate(4, new java.sql.Date(emprestimo.getDataDevolucao().getTime()));
			System.out.println(declaracao.toString());
			int idEmprestimo = inserirRegistro();
			

			// Atualizar livros
			LivroDAO livroDAO = new LivroDAO();
			for (int i = 0; i < emprestimo.getLivros().size(); i++) {
				emprestimo.getLivros().get(i).setEmprestimo(idEmprestimo);
				livroDAO.atualizarLivro(emprestimo.getLivros().get(i));
			}

		} catch (SQLException ex) {
			System.out.println("Não foi possível inserir o emprestimo.\nErro: " + ex.getErrorCode());
			return false;
		}
		
		return true;
	}

	public Emprestimo consultarEmprestimo(String coluna, String valor) {
		
		Emprestimo emprestimo = new Emprestimo();
		String query = "SELECT * FROM " + tabelaEmprestimos + " WHERE " + coluna + "='" + valor + "' LIMIT 1";	
		
		try {
			// Buscar dados de emprestimo
			declaracao = conexao.prepareStatement(query);
			ResultSet res = declaracao.executeQuery(); 
			
			if (res.next()) {
				emprestimo.setId(res.getInt("id"));
				emprestimo.setLeitor(new LeitorDAO().consultarLeitor("id", String.valueOf(res.getInt("id_leitor"))));
				emprestimo.setFuncionario(new FuncionarioDAO().consultarFuncionario("id", String.valueOf(res.getInt("id_funcionario"))));
				emprestimo.setDataEmprestimo(new Date(res.getDate("data_emprestimo").getTime()));
				emprestimo.setDataDevolucao(new Date(res.getDate("data_devolucao").getTime()));
			} else {
				System.out.println("Não foi possível localizar o Emprestimo com " + coluna + ": " + valor);
				return null;
			}
			
			// Buscando dados dos livros emprestados
			LivroDAO livroDAO = new LivroDAO();
			ArrayList<Livro> livros = livroDAO.consultarLivros("id_emprestimo", String.valueOf(emprestimo.getId()));
			emprestimo.setLivros(livros);
			
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar consultar emprestimo.\nErro: " + ex.getErrorCode());
		}
		return emprestimo;
	}
	
	public boolean atualizarEmprestimo(Emprestimo emprestimo){
		
		boolean resAtualizacao = false;
		String updateEmprestimo = "UPDATE " + tabelaEmprestimos + " SET id_leitor = ?, id_funcionario = ?, data_emprestimo = ?, data_devolucao = ?  WHERE id = ?";
		
		try {
			// Atualizar dados do emprestimo
			declaracao = conexao.prepareStatement(updateEmprestimo);
			declaracao.setInt(1, emprestimo.getLeitor().getId());
			declaracao.setInt(2, emprestimo.getFuncionario().getId());
			declaracao.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
			declaracao.setDate(4, new java.sql.Date(emprestimo.getDataDevolucao().getTime()));
			declaracao.setInt(5, emprestimo.getId());
			
			resAtualizacao = declaracao.executeUpdate() != 0;
			
			// Atualizar livros
			LivroDAO livroDAO = new LivroDAO();
			for(Livro livro: emprestimo.getLivros()){
				livro.setEmprestimo(emprestimo.getId());
				livroDAO.atualizarLivro(livro);
			}
			
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar atualizar emprestimo.\nErro: " + ex.getErrorCode());
		}
		return resAtualizacao;
	} 

	public boolean removerEmprestimo(Emprestimo emprestimo){
		// Atualizar livros
		LivroDAO livroDAO = new LivroDAO();
		for(Livro livro: emprestimo.getLivros()){
			livro.setEmprestimo(0);
			livroDAO.atualizarLivro(livro);
		}
		return removerEmprestimo(emprestimo.getId());
	}
	
	public boolean removerEmprestimo(int id) {
		try {
			String query = "DELETE FROM " + tabelaEmprestimos + " WHERE id = ?";
			declaracao = conexao.prepareStatement(query);
			declaracao.setInt(1, id);
			
			
			
			return declaracao.executeUpdate() != 0;
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar remover emprestimo.\nErro: " + ex.getErrorCode());
		}
		return false;
	}
	
	public ArrayList<Emprestimo> listarEmprestimos(){
		
		ArrayList<Emprestimo> emprestimos = new ArrayList<>();
		String query = "SELECT * FROM " + tabelaEmprestimos;	
		
		try {
			// Buscar dados de emprestimo
			declaracao = conexao.prepareStatement(query);
			ResultSet res = declaracao.executeQuery(); 
			
			LivroDAO livroDAO = new LivroDAO();
			
			while(res.next()) {
				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setId(res.getInt("id"));
				emprestimo.setLeitor(new LeitorDAO().consultarLeitor("id", String.valueOf(res.getInt("id_leitor"))));
				emprestimo.setFuncionario(new FuncionarioDAO().consultarFuncionario("id", String.valueOf(res.getInt("id_funcionario"))));
				emprestimo.setDataEmprestimo(new Date(res.getDate("data_emprestimo").getTime()));
				emprestimo.setDataDevolucao(new Date(res.getDate("data_devolucao").getTime()));
				
				ArrayList<Livro> livros = livroDAO.consultarLivros("id_emprestimo", String.valueOf(emprestimo.getId()));
				emprestimo.setLivros(livros);
				
				emprestimos.add(emprestimo);
			}
			
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar buscar emprestimos.\nErro: " + ex.getErrorCode());
			return null;
		}
		return emprestimos;
	}
	private int inserirRegistro() throws SQLException {
		declaracao.executeUpdate();
		ResultSet res = declaracao.getGeneratedKeys();
		res.next();
		return res.getInt(1);
	}

}
