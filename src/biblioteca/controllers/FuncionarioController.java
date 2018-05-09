/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.controllers;

import biblioteca.models.Funcionario;
import biblioteca.models.dao.FuncionarioDAO;

/**
 *
 * @author luanm
 */
public class FuncionarioController {
	
	private final FuncionarioDAO funcionarioDAO;

	public FuncionarioController() {
		this.funcionarioDAO = new FuncionarioDAO();
	}
	
	public Funcionario cadastrarFuncionario(String nome, String cpf, String email, String telefone, String rua, String bairro, String usuario, String senha){
		Funcionario funcionario = new Funcionario(0, cpf, nome, email, telefone, rua, bairro, usuario, senha);
		if(funcionarioDAO.inserirFuncionario(funcionario))
			return funcionario;
		return null;
	}
	
	public boolean removerFuncionario(int id){
		return funcionarioDAO.removerFuncionario(id);
	}
	
	public boolean atualizarFuncionario(Funcionario funcionario){
		return funcionarioDAO.atualizarFuncionario(funcionario);
	}
	
	public Funcionario autenticarFuncionario(String login, String senha){
		return funcionarioDAO.consultarFuncionarioLogin(login, senha);
	}
	
	public Funcionario buscarFuncionario(String dado, String valor){
		return funcionarioDAO.consultarFuncionario(dado, valor);
	}
	
	
}
