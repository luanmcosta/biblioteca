/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.models;

/**
 *
 * @author luanm
 */
public class Funcionario extends Pessoa{
    
    private String usuario;
    private String senha;

    public Funcionario(int id, int cpf, String nome, String email, String telefone, String rua, String bairro, String usuario, String senha) {
        super(id, cpf, nome, email, telefone, rua, bairro);
        this.usuario = usuario;
        this.senha = senha;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    
}


