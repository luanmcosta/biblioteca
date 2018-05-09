/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sun.java2d.pipe.SpanShapeRenderer;

/**
 *
 * @author luanm
 */
public class Emprestimo {

	private int id;
	private List<Livro> livros;
	private Leitor leitor;
	private Funcionario funcionario;
	private Date dataEmprestimo;
	private Date dataDevolucao;

	public Emprestimo() {
		livros = new ArrayList<>();
	}

	public Emprestimo(int id, List<Livro> livros, Leitor leitor, Funcionario funcionario, Date dataEmprestimo, Date dataDevolucao) {
		this.id = id;
		this.livros = livros;
		this.leitor = leitor;
		this.funcionario = funcionario;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public Leitor getLeitor() {
		return leitor;
	}
	
	public void addLivro(Livro livro){
		this.livros.add(livro);
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

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
        
        public String nomeLeitor(){
            return this.leitor.getNome();
        }
        
        public String dataDevolucaoForm(){
            String data;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            data = sdf.format(this.dataDevolucao);
            return data;
        }
        
        public String dataEmprestimoForm(){
            String data;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            data = sdf.format(this.dataEmprestimo);
            return data;
        }
        
        
}
