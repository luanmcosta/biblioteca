/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.controllers;

import biblioteca.models.Leitor;
import biblioteca.models.dao.LeitorDAO;
import java.util.ArrayList;

/**
 *
 * @author luanm
 */
public class LeitorController {
	
    private final LeitorDAO leitorDAO;

    public LeitorController() {
            this.leitorDAO = new LeitorDAO();
    }

    public Leitor cadastrarLeitor(String nome, String cpf, String rua, String bairro, String email, String telefone){
            Leitor leitor = new Leitor(0, cpf, nome, email, telefone, rua, bairro);
            if(leitorDAO.inserirLeitor(leitor))
                    return leitor;
            return null;
    }

    public boolean removerLeitor(int id){
            return leitorDAO.removerLeitor(id);
    }

    public boolean atualizarLeitor(Leitor leitor){
            return leitorDAO.atualizarLeitor(leitor);
    }
    
    public ArrayList<Leitor> buscarLeitores(String dado, String valor){
        return leitorDAO.buscarLeitores(dado, valor);
    }

    public ArrayList<Leitor> listarLeitores(){
            return leitorDAO.listarLeitores();
    }

    public Leitor buscarLeitor(String dado, String valor){
            return leitorDAO.consultarLeitor(dado, valor);
    }	
}

