/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.controllers;

import biblioteca.models.Emprestimo;
import biblioteca.models.Funcionario;
import biblioteca.models.Leitor;
import biblioteca.models.Livro;
import biblioteca.models.dao.EmprestimoDAO;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author luanm
 */
public class EmprestimoController {

	private final EmprestimoDAO emprestimoDAO;

	public EmprestimoController() {
		this.emprestimoDAO = new EmprestimoDAO();
	}
	
	public Emprestimo realizarEmprestimo(ArrayList<Livro> livros, Leitor leitor, Funcionario funcionario, Date dataEmprestimo, Date dataDevolucao){
		Emprestimo emprestimo;
		emprestimo = new Emprestimo(0, livros, leitor, funcionario, dataEmprestimo, dataDevolucao);
		if(emprestimoDAO.inserirEmprestimo(emprestimo))
			return emprestimo;
		return null;
	}
	
	public boolean removerEmprestimo(int id){
		return emprestimoDAO.removerEmprestimo(id);
	}
	
	public boolean atualizarEmprestimo(Emprestimo emprestimo){
		return emprestimoDAO.atualizarEmprestimo(emprestimo);
	}
	
	public ArrayList<Emprestimo> listarEmprestimos(){
		return emprestimoDAO.listarEmprestimos();
	}
	
	public Emprestimo buscarEmprestimo(String dado, String valor){
		return emprestimoDAO.consultarEmprestimo(dado, valor);
	}
}
