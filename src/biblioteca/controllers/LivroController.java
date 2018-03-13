/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.controllers;

import biblioteca.models.Livro;
import biblioteca.models.dao.LivroDAO;
import java.util.ArrayList;

/**
 *
 * @author luanm
 */
public class LivroController {
	
	private final LivroDAO livroDAO;

	public LivroController() {
		livroDAO = new LivroDAO();
	}
	
	
	public Livro cadastrarLivro(String titulo, String autor, String categoria, String status, int ano, int isbn, int edicao){
		Livro livro = new Livro(0, titulo, autor, categoria, status, ano, isbn, edicao);
		if(livroDAO.inserirLivro(livro))
			return livro;
		return null;
	}
	
	public boolean removerLivro(int id){
		Livro livro = livroDAO.consultarLivro("id", String.valueOf(id));
		if(livro == null)
			return false;
		return livroDAO.removerLivro(livro);
	}
	
	public boolean atualizarLivro(Livro livro){
		if(livro == null)
			return false;
		return livroDAO.atualizarLivro(livro);
	}
	
	public ArrayList<Livro> listarLivros(){
		ArrayList<Livro> livros = livroDAO.listarLivros();
		return livros;
	}
	
	public ArrayList<Livro> buscarLivros(String coluna, String valor){
		ArrayList<Livro> livros = livroDAO.consultarLivros(coluna, valor);
		return livros;
	}
	
	public Livro buscarLivro(String coluna, String valor){
		Livro livro = livroDAO.consultarLivro(coluna, valor);
		return livro;
	}
	
}
