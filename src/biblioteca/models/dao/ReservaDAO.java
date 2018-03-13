/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.models.dao;

import biblioteca.models.Reserva;
import biblioteca.models.db.Conexao;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author luanm
 */
public class ReservaDAO {

	// Atributos
	private final Connection conexao;
	private PreparedStatement declaracao;
	private final String tabelaReservas = "reservas";
	private final String tabelaLivrosReservados = "livrosreservados";
	
	// Construtor
	public ReservaDAO() {
		this.conexao = Conexao.getConnection();
	}

	public boolean inserirReserva(Reserva reserva) {

		String inserirReserva = "INSERT INTO " + tabelaReservas + " (id_funcionario, id_leitor, id_livro, data_reserva) VALUES (?, ?, ?, ?)";
	
		try {
			// Inserir registro de reserva e armazenar o id
			declaracao = conexao.prepareStatement(inserirReserva, Statement.RETURN_GENERATED_KEYS);
			declaracao.setInt(1, reserva.getFuncionario().getId());
			declaracao.setInt(2, reserva.getLeitor().getId());
			declaracao.setInt(3, reserva.getLivro().getId());
			declaracao.setDate(4, new java.sql.Date(reserva.getDataReserva().getTime()));
			//System.out.println(declaracao.toString());
			int idReserva = inserirRegistro();

		} catch (SQLException ex) {
			System.out.println("Não foi possível inserir o reserva.\nErro: " + ex.getErrorCode());
			return false;
		}
		
		return true;
	}
	
	public ArrayList<Reserva> consultarReservas(String coluna, String valor) {
		
		ArrayList<Reserva> reservas = new ArrayList<>();
		String query = "SELECT * FROM " + tabelaReservas + " WHERE " + coluna + "='" + valor;
	
		try {
			// Buscar dados de reserva
			declaracao = conexao.prepareStatement(query);
			ResultSet res = declaracao.executeQuery(); 
			
			while (res.next()) {
				Reserva reserva = new Reserva();
				reserva.setId(res.getInt("id"));
				reserva.setLeitor(new LeitorDAO().consultarLeitor("id", String.valueOf(res.getInt("id_leitor"))));
				reserva.setFuncionario(new FuncionarioDAO().consultarFuncionario("id", String.valueOf(res.getInt("id_funcionario"))));
				reserva.setLivro(new LivroDAO().consultarLivro("id", String.valueOf(res.getInt("id_livro"))));
				reserva.setDataReserva(new Date(res.getDate("data_reserva").getTime()));
				
				reservas.add(reserva);
			} 
			
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar consultar reserva.\nErro: " + ex.getErrorCode());
			return null;
		}
		return reservas;
	}
	
	public ArrayList<Reserva> listarReservas() {
		
		ArrayList<Reserva> reservas = new ArrayList<>();
		String query = "SELECT * FROM " + tabelaReservas;
	
		try {
			// Buscar dados de reserva
			declaracao = conexao.prepareStatement(query);
			ResultSet res = declaracao.executeQuery(); 
			
			while (res.next()) {
				Reserva reserva = new Reserva();
				reserva.setId(res.getInt("id"));
				reserva.setLeitor(new LeitorDAO().consultarLeitor("id", String.valueOf(res.getInt("id_leitor"))));
				reserva.setFuncionario(new FuncionarioDAO().consultarFuncionario("id", String.valueOf(res.getInt("id_funcionario"))));
				reserva.setLivro(new LivroDAO().consultarLivro("id", String.valueOf(res.getInt("id_livro"))));
				reserva.setDataReserva(new Date(res.getDate("data_reserva").getTime()));
				
				reservas.add(reserva);
			} 
			
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar consultar reserva.\nErro: " + ex.getErrorCode());
			return null;
		}
		return reservas;
	}
	

	public Reserva consultarReserva(String coluna, String valor) {
		
		Reserva reserva = new Reserva();
		String query = "SELECT * FROM " + tabelaReservas + " WHERE " + coluna + "='" + valor + "' LIMIT 1";
	
		try {
			// Buscar dados de reserva
			declaracao = conexao.prepareStatement(query);
			ResultSet res = declaracao.executeQuery(); 
			
			if (res.next()) {
				reserva.setId(res.getInt("id"));
				reserva.setLeitor(new LeitorDAO().consultarLeitor("id", String.valueOf(res.getInt("id_leitor"))));
				reserva.setFuncionario(new FuncionarioDAO().consultarFuncionario("id", String.valueOf(res.getInt("id_funcionario"))));
				reserva.setLivro(new LivroDAO().consultarLivro("id", String.valueOf(res.getInt("id_livro"))));
				reserva.setDataReserva(new Date(res.getDate("data_reserva").getTime()));
			} else {
				System.out.println("Não foi possível localizar o Reserva com " + coluna + ": " + valor);
				return null;
			}
			
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar consultar reserva.\nErro: " + ex.getErrorCode());
		}
		return reserva;
	}
	
	public boolean atualizarReserva(Reserva reserva){
		
		boolean resAtualizacao = false;
		String updateReserva = "UPDATE " + tabelaReservas + " SET id_leitor = ?, id_funcionario = ?, id_livro = ?, data_reserva = ?  WHERE id = ?";
		
		try {
			// Atualizar dados do reserva
			declaracao = conexao.prepareStatement(updateReserva);
			declaracao.setInt(1, reserva.getLeitor().getId());
			declaracao.setInt(2, reserva.getFuncionario().getId());
			declaracao.setInt(3, reserva.getLivro().getId());
			declaracao.setDate(4, new java.sql.Date(reserva.getDataReserva().getTime()));
			declaracao.setInt(5, reserva.getId());
			
			resAtualizacao = declaracao.executeUpdate() != 0;
		
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar atualizar reserva.\nErro: " + ex.getErrorCode());
		}
		return resAtualizacao;
	} 

	public boolean removerReserva(Reserva reserva){
		return removerReserva(reserva.getId());
	}
	
	public boolean removerReserva(int id) {
		try {
			String query = "DELETE FROM " + tabelaReservas + " WHERE id = ?";
			declaracao = conexao.prepareStatement(query);
			declaracao.setInt(1, id);
			
			return declaracao.executeUpdate() != 0;
		} catch (SQLException ex) {
			System.out.println("Erro ao tentar remover reserva.\nErro: " + ex.getErrorCode());
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
