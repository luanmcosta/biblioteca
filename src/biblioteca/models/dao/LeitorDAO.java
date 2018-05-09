/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.models.dao;

import biblioteca.models.Leitor;
import biblioteca.models.db.Conexao;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author luanm
 */
public class LeitorDAO {

	// Atributos
	private final Connection conexao;
	private PreparedStatement declaracao;
	private final String tabelaLeitores = "leitores";
	private final String tabelaPessoas = "pessoas";

	// Construtor
	public LeitorDAO() {
		this.conexao = Conexao.getConnection();
	}

	public boolean inserirLeitor(Leitor leitor) {

		String inserirPessoa = "INSERT INTO " + tabelaPessoas + " (nome, cpf, rua, bairro, email, telefone) VALUES (?, ?, ?, ?, ?, ?)";
		String inserirLeitor = "INSERT INTO " + tabelaLeitores + " (quantidade_livros, bloqueio, id_pessoa) VALUES (?, ?, ?)";
		String atualizarPessoa = "UPDATE " + tabelaPessoas + " SET id_leitor = ? WHERE id = ?";

		try {
			// Inserir registro de pessoa e armazenar o id
			declaracao = conexao.prepareStatement(inserirPessoa, Statement.RETURN_GENERATED_KEYS);
			declaracao.setString(1, leitor.getNome());
			declaracao.setString(2, leitor.getCpf());
			declaracao.setString(3, leitor.getRua());
			declaracao.setString(4, leitor.getBairro());
			declaracao.setString(5, leitor.getEmail());
			declaracao.setString(6, leitor.getTelefone());
			int idPessoa = inserirRegistro();

			// Inserir registro de leitor e armazenar o id
			declaracao = conexao.prepareStatement(inserirLeitor, Statement.RETURN_GENERATED_KEYS);
			declaracao.setInt(1, leitor.getQuantidadeLivros());
			declaracao.setBoolean(2, leitor.isBloqueio());
			declaracao.setInt(3, idPessoa);
			int idLeitor = inserirRegistro();

			// Atualizar registro de pessoa com o id do leitor
			declaracao = conexao.prepareStatement(atualizarPessoa, Statement.RETURN_GENERATED_KEYS);
			declaracao.setInt(1, idLeitor);
			declaracao.setInt(2, idPessoa);
			declaracao.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("Não foi possível gerar a declaração.\nErro: " + ex.getErrorCode());
			return false;
		}
		
		return true;
	}
	
        public ArrayList<Leitor> buscarLeitores(String coluna, String valor){
            ArrayList<Leitor> leitores = new ArrayList<>();
            String query = "SELECT p.*, l.quantidade_livros, l.bloqueio, l.id_pessoa FROM " + tabelaPessoas + " p JOIN " + tabelaLeitores + " l ON p.id_leitor = l.id WHERE p.id_leitor IS NOT NULL AND p." + coluna + " LIKE '%" + valor + "%' ";
            
            try {

                    declaracao = conexao.prepareStatement(query);
                    //System.out.println(declaracao.toString());
                    ResultSet res = declaracao.executeQuery(); 
                    //System.out.println("Erro");
                    while (res.next()) {
                        Leitor leitor = new Leitor();
                        leitor.setId(res.getInt("id"));
                        leitor.setNome(res.getString("nome"));
                        leitor.setCpf(res.getString("cpf"));
                        leitor.setRua(res.getString("rua"));
                        leitor.setBairro(res.getString("bairro"));
                        leitor.setEmail(res.getString("email"));
                        leitor.setTelefone(res.getString("telefone"));
                        leitor.setQuantidadeLivros(res.getInt("quantidade_livros"));
                        leitor.setBloqueio(res.getBoolean("bloqueio"));
                        leitores.add(leitor);
                    }
            } catch (SQLException ex) {
                    System.out.println("Erro ao tentar listar os leitores.\nErro: " + ex.getErrorCode());
            }
            return leitores;
	}
        
	public ArrayList<Leitor> listarLeitores(){
            
		ArrayList<Leitor> leitores = new ArrayList<>();
		String query = "SELECT p.*, l.quantidade_livros, l.bloqueio, l.id_pessoa FROM " + tabelaPessoas + " p JOIN " + tabelaLeitores + " l ON p.id_leitor = l.id WHERE p.id_leitor IS NOT NULL";
		
		try {
                        
			declaracao = conexao.prepareStatement(query);
			//System.out.println(declaracao.toString());
			ResultSet res = declaracao.executeQuery(); 
                        //System.out.println("Erro");
			while (res.next()) {
                            Leitor leitor = new Leitor();
                            leitor.setId(res.getInt("id"));
                            leitor.setNome(res.getString("nome"));
                            leitor.setCpf(res.getString("cpf"));
                            leitor.setRua(res.getString("rua"));
                            leitor.setBairro(res.getString("bairro"));
                            leitor.setEmail(res.getString("email"));
                            leitor.setTelefone(res.getString("telefone"));
                            leitor.setQuantidadeLivros(res.getInt("quantidade_livros"));
                            leitor.setBloqueio(res.getBoolean("bloqueio"));
                            leitores.add(leitor);
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar listar os leitores.\nErro: " + ex.getErrorCode());
		}
		return leitores;
	}

	public Leitor consultarLeitor(String coluna, String valor) {
		
		Leitor leitor = null;
		String query = "SELECT p.*, l.quantidade_livros, l.bloqueio, l.id_pessoa FROM " + tabelaPessoas + " p JOIN " + tabelaLeitores + " l ON p.id_leitor = l.id WHERE p.id_leitor IS NOT NULL AND p." + coluna + " LIKE '%" + valor + "%'";
		
		try {
			declaracao = conexao.prepareStatement(query);
			//System.out.println(declaracao.toString());
			ResultSet res = declaracao.executeQuery(); 

			if (res.next()) {
				leitor = new Leitor();
				leitor.setId(res.getInt("id"));
				leitor.setNome(res.getString("nome"));
				leitor.setCpf(res.getString("cpf"));
				leitor.setRua(res.getString("rua"));
				leitor.setBairro(res.getString("bairro"));
				leitor.setEmail(res.getString("email"));
				leitor.setTelefone(res.getString("telefone"));
				leitor.setQuantidadeLivros(res.getInt("quantidade_livros"));
				leitor.setBloqueio(res.getBoolean("bloqueio"));
			} else {
				System.out.println("Não foi possível localizar o Leitor com " + coluna + ": " + valor);
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar consultar leitor.\nErro: " + ex.getErrorCode());
		}
		return leitor;
	}
	
	public boolean atualizarLeitor(Leitor leitor){
		boolean resAtualizacao = false;
		String updatePessoa = "UPDATE " + tabelaPessoas + " SET nome = ?, cpf = ?, rua = ?, bairro = ?, email = ?, telefone = ? WHERE id = ?";
		String updateLeitor = "UPDATE " + tabelaLeitores + " SET quantidade_livros = ?, bloqueio = ? WHERE id_pessoa = ?";
		
		try {
			// Atualizar dados pessoais
			declaracao = conexao.prepareStatement(updatePessoa);
			declaracao.setString(1, leitor.getNome());
			declaracao.setString(2, leitor.getCpf());
			declaracao.setString(3, leitor.getRua());
			declaracao.setString(4, leitor.getBairro());
			declaracao.setString(5, leitor.getEmail());
			declaracao.setString(6, leitor.getTelefone());
			declaracao.setInt(7, leitor.getId());
			resAtualizacao = declaracao.executeUpdate() != 0;
			
			// Atualizar dados de leitor
			declaracao = conexao.prepareStatement(updateLeitor);
			declaracao.setInt(1, leitor.getQuantidadeLivros());
			declaracao.setBoolean(2, leitor.isBloqueio());
			declaracao.setInt(3, leitor.getId());
			resAtualizacao = declaracao.executeUpdate() != 0;
			
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar atualizar leitor.\nErro: " + ex.getErrorCode());
		}
		return resAtualizacao;
	} 

	public boolean removerLeitor(Leitor leitor){
		return removerLeitor(leitor.getId());
	}
	
	public boolean removerLeitor(int id) {
		try {
			String query = "DELETE FROM " + tabelaPessoas + " WHERE id = ?";
			declaracao = conexao.prepareStatement(query);
			declaracao.setInt(1, id);
			return declaracao.executeUpdate() != 0;
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar remover leitor.\nErro: " + ex.getErrorCode());
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
