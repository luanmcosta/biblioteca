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
public class Livro {

	private int id;
	private String titulo;
	private String autor;
	private String categoria;
	private String status;
	private int ano;
	private int isbn;
	private int edicao;
	private int reservas[];
	private int emprestimo;

	public Livro() {
	}

	public Livro(int id, String titulo, String autor, String categoria, String status, int ano, int isbn, int edicao) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.categoria = categoria;
		this.status = status;
		this.ano = ano;
		this.isbn = isbn;
		this.edicao = edicao;
	}
	
	
	

	public Livro(int id, String titulo, String autor, String categoria, String status, int ano, int isbn, int edicao, int[] reservas, int emprestimo) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.categoria = categoria;
		this.status = status;
		this.ano = ano;
		this.isbn = isbn;
		this.edicao = edicao;
		this.reservas = reservas;
		this.emprestimo = emprestimo;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	public int[] getReservas() {
		return reservas;
	}

	public void setReservas(int[] reservas) {
		this.reservas = reservas;
	}

	public int getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(int emprestimo) {
		this.emprestimo = emprestimo;
	}
	
	public boolean emprestado(){
		return getEmprestimo() != 0;
	}

	
}
