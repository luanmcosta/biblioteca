/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.models;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author luanm
 */
public class Reserva {

	private int id;
	private Livro livro;
	private Leitor leitor;
	private Funcionario funcionario;
	private Date dataReserva;

	public Reserva() {
	}

	public Reserva(int id, Livro livro, Leitor leitor, Funcionario funcionario, Date dataReserva) {
		this.id = id;
		this.livro = livro;
		this.leitor = leitor;
		this.funcionario = funcionario;
		this.dataReserva = dataReserva;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Leitor getLeitor() {
		return leitor;
	}

	public void setLeitor(Leitor leitor) {
		this.leitor = leitor;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}
	
}
