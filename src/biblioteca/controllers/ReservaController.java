/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.controllers;

import biblioteca.models.Funcionario;
import biblioteca.models.Leitor;
import biblioteca.models.Livro;
import biblioteca.models.Reserva;
import biblioteca.models.dao.ReservaDAO;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author luanm
 */
public class ReservaController {

	private final ReservaDAO reservaDAO;

	public ReservaController() {
		this.reservaDAO = new ReservaDAO();
	}
	
	public Reserva inserirReserva(Livro livro, Leitor leitor, Funcionario funcionario, Date dataReserva){
		Reserva reserva = new Reserva(0, livro, leitor, funcionario, dataReserva);
		if(reservaDAO.inserirReserva(reserva))
			return reserva;
		return null;
	}
	
	public boolean removerReserva(int id){
		return reservaDAO.removerReserva(id);
	}
	
	public boolean atualizarReserva(Reserva reserva){
		return reservaDAO.atualizarReserva(reserva);
	}
	
	public Reserva buscarReserva(String  dado, String valor){
		return reservaDAO.consultarReserva(dado, valor);
	}
	
	public ArrayList<Reserva> buscarReservas(String dado, String valor){
		return reservaDAO.consultarReservas(dado, valor);
	}
	
	public ArrayList<Reserva> listarReservas(){
		return reservaDAO.listarReservas();
	}
	
	
	
}
