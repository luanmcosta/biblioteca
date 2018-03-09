/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.models.dao;

import biblioteca.models.Funcionario;
import biblioteca.models.db.Conexao;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author luanm
 */
public class FuncionarioDAO {

	// Atributos
	private final Connection conexao;
	private PreparedStatement declaracao;
	private final String tabelaFuncionarios = "funcionarios";
	private final String tabelaPessoas = "pessoas";

	// Construtor
	public FuncionarioDAO() {
		this.conexao = Conexao.getConnection();
	}

	public void inserirFuncionario(Funcionario funcionario) {

		String inserirPessoa = "INSERT INTO " + tabelaPessoas + " (nome, cpf, rua, bairro, email, telefone) VALUES (?, ?, ?, ?, ?, ?)";
		String inserirFuncionario = "INSERT INTO " + tabelaFuncionarios + " (usuario, senha, id_pessoa) VALUES (?, ?, ?)";
		String atualizarPessoa = "UPDATE " + tabelaPessoas + " SET id_funcionario = ? WHERE id = ?";

		try {
			// Inserir registro de pessoa e armazenar o id
			declaracao = conexao.prepareStatement(inserirPessoa, Statement.RETURN_GENERATED_KEYS);
			declaracao.setString(1, funcionario.getNome());
			declaracao.setString(2, funcionario.getCpf());
			declaracao.setString(3, funcionario.getRua());
			declaracao.setString(4, funcionario.getBairro());
			declaracao.setString(5, funcionario.getEmail());
			declaracao.setString(6, funcionario.getTelefone());
			int idPessoa = inserirRegistro();

			// Inserir registro de funcionario e armazenar o id
			declaracao = conexao.prepareStatement(inserirFuncionario, Statement.RETURN_GENERATED_KEYS);
			declaracao.setString(1, funcionario.getUsuario());
			declaracao.setString(2, funcionario.getSenha());
			declaracao.setInt(3, idPessoa);
			int idFuncionario = inserirRegistro();

			// Atualizar registro de pessoa com o id do funcionario
			declaracao = conexao.prepareStatement(atualizarPessoa, Statement.RETURN_GENERATED_KEYS);
			declaracao.setInt(1, idFuncionario);
			declaracao.setInt(2, idPessoa);
			declaracao.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("Não foi possível gerar a declaração.\nErro: " + ex.getErrorCode());
		}
	}
	
	public Funcionario consultarFuncionario(String coluna, String valor) {
		
		Funcionario funcionario = null;
		String query = "SELECT p.*, f.usuario, f.senha, f.id_pessoa FROM " + tabelaPessoas + " p JOIN " + tabelaFuncionarios + " f ON p.id_funcionario = f.id WHERE p.id_funcionario IS NOT NULL AND p." + coluna + " = '" + valor +"'";
		
		try {
			declaracao = conexao.prepareStatement(query);
			//System.out.println(declaracao.toString());
			ResultSet res = declaracao.executeQuery(); 

			if (res.next()) {
				funcionario = new Funcionario();
				funcionario.setId(res.getInt("id"));
				funcionario.setNome(res.getString("nome"));
				funcionario.setCpf(res.getString("cpf"));
				funcionario.setRua(res.getString("rua"));
				funcionario.setBairro(res.getString("bairro"));
				funcionario.setEmail(res.getString("email"));
				funcionario.setTelefone(res.getString("telefone"));
				funcionario.setUsuario(res.getString("usuario"));
				funcionario.setSenha(res.getString("senha"));
			} else {
				System.out.println("Não foi possível localizar o Funcionario com esses dados forneceidos");
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar consultar funcionario.\nErro: " + ex.getErrorCode());
		}
		return funcionario;
	}

	public Funcionario consultarFuncionarioLogin(String usuario, String senha) {
		
		Funcionario funcionario = null;
		String query = "SELECT p.*, f.usuario, f.senha, f.id_pessoa FROM " + tabelaPessoas + " p JOIN " + tabelaFuncionarios + " f ON p.id_funcionario = f.id WHERE p.id_funcionario IS NOT NULL AND usuario = ? AND senha = ?";
		
		try {
			declaracao = conexao.prepareStatement(query);
			declaracao.setString(1, usuario);
			declaracao.setString(2, senha);
			//System.out.println(declaracao.toString());
			ResultSet res = declaracao.executeQuery(); 

			if (res.next()) {
				funcionario = new Funcionario();
				funcionario.setId(res.getInt("id"));
				funcionario.setNome(res.getString("nome"));
				funcionario.setCpf(res.getString("cpf"));
				funcionario.setRua(res.getString("rua"));
				funcionario.setBairro(res.getString("bairro"));
				funcionario.setEmail(res.getString("email"));
				funcionario.setTelefone(res.getString("telefone"));
				funcionario.setUsuario(res.getString("usuario"));
				funcionario.setSenha(res.getString("senha"));
			} else {
				System.out.println("Não foi possível localizar o Funcionario com esses dados forneceidos");
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar consultar funcionario.\nErro: " + ex.getErrorCode());
		}
		return funcionario;
	}
	
	public boolean atualizarFuncionario(Funcionario funcionario){
		boolean resAtualizacao = false;
		String updatePessoa = "UPDATE " + tabelaPessoas + " SET nome = ?, cpf = ?, rua = ?, bairro = ?, email = ?, telefone = ? WHERE id = ?";
		String updateFuncionario = "UPDATE " + tabelaFuncionarios + " SET usuario = ?, senha = ? WHERE id_pessoa = ?";
		
		try {
			// Atualizar dados pessoais
			declaracao = conexao.prepareStatement(updatePessoa);
			declaracao.setString(1, funcionario.getNome());
			declaracao.setString(2, funcionario.getCpf());
			declaracao.setString(3, funcionario.getRua());
			declaracao.setString(4, funcionario.getBairro());
			declaracao.setString(5, funcionario.getEmail());
			declaracao.setString(6, funcionario.getTelefone());
			declaracao.setInt(7, funcionario.getId());
			resAtualizacao = declaracao.executeUpdate() != 0;
			
			// Atualizar dados de funcionario
			declaracao = conexao.prepareStatement(updateFuncionario);
			declaracao.setString(1, funcionario.getUsuario());
			declaracao.setString(2, funcionario.getSenha());
			declaracao.setInt(3, funcionario.getId());
			resAtualizacao = declaracao.executeUpdate() != 0;
			
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar atualizar funcionario.\nErro: " + ex.getErrorCode());
		}
		return resAtualizacao;
	} 

	public boolean removerFuncionario(Funcionario funcionario){
		return removerFuncionario(funcionario.getId());
	}
	
	public boolean removerFuncionario(int id) {
		try {
			String query = "DELETE FROM " + tabelaPessoas + " WHERE id = ?";
			declaracao = conexao.prepareStatement(query);
			declaracao.setInt(1, id);
			return declaracao.executeUpdate() != 0;
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar remover funcionario.\nErro: " + ex.getErrorCode());
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
