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
public class Leitor extends Pessoa {

	private int quantidadeLivros;
	private boolean bloqueio;

	public Leitor() {

	}

	public Leitor(int id, String cpf, String nome, String email, String telefone, String rua, String bairro) {
		super(id, cpf, nome, email, telefone, rua, bairro);
	}

	public Leitor(int id, String cpf, String nome, String email, String telefone, String rua, String bairro, int quantidadeLivros, boolean bloqueio) {
		super(id, cpf, nome, email, telefone, rua, bairro);
		this.quantidadeLivros = quantidadeLivros;
		this.bloqueio = bloqueio;
	}

	public int getQuantidadeLivros() {
		return quantidadeLivros;
	}

	public void setQuantidadeLivros(int quantidadeLivros) {
		this.quantidadeLivros = quantidadeLivros;
	}

	public boolean isBloqueio() {
		return bloqueio;
	}

	public void setBloqueio(boolean bloqueio) {
		this.bloqueio = bloqueio;
	}
        
        public String toString(){
            return this.getNome();
        }
        
}
