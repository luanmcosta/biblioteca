package biblioteca;

import biblioteca.models.Emprestimo;
import biblioteca.models.Funcionario;
import biblioteca.models.Leitor;
import biblioteca.models.Livro;
import biblioteca.models.Reserva;
import biblioteca.models.dao.EmprestimoDAO;
import biblioteca.models.dao.FuncionarioDAO;
import biblioteca.models.dao.LeitorDAO;
import javafx.application.Application;
import biblioteca.models.dao.LivroDAO;
import biblioteca.models.dao.ReservaDAO;
import java.util.Date;
import javafx.stage.Stage;

/**
 *
 * @author luanm
 */
public class Biblioteca extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Leitor luan = new Leitor(0, "12345678910", "Luan M. Costa", "luan@gmail.com", "88996311105", "Rua dos Alfeneiros", "Centro", 0, true);
		
		//LeitorDAO leitorDAO = new LeitorDAO();
		//leitorDAO.inserirLeitor(luan);
		//Leitor resultado = leitorDAO.consultarLeitor("telefone", "999999");
		//leitorDAO.inserirLeitor(luan);
		//if(resultado != null)
		//    System.out.println(resultado.getCpf());
		///resultado.setQuantidadeLivros(5);
		//boolean res = leitorDAO.removerLeitor(resultado);
		//System.out.println(res);
		
//		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
//		Funcionario alguem = funcionarioDAO.consultarFuncionario("loginbr123", "brbr");
//		if(alguem != null){
//			System.out.println(alguem.getNome());
//			alguem.setSenha("brbr");
//			funcionarioDAO.removerFuncionario(alguem);
//			//funcionarioDAO.atualizarFuncionario(alguem);
//		}	
//
//		LivroDAO livroDAO = new LivroDAO();
		//Livro livro2 = new Livro(0, "Harry Potter 2", "J .K Rowling", "Aventura", "Novo", 2002, 2222222, 4);
//		//livroDAO.inserirLivro(livro);
//	
//		Livro livro = livroDAO.consultarLivro("id", "2");
//	livro.setStatus("Velho");
//		livro.setAutor("Joao Neves");
//		livro.setTitulo("Divina Comédia");
//	boolean res = livroDAO.atualizarLivro(livro);
//		System.out.println(res)
//		boolean res = livroDAO.removerLivro(livro);
//		System.out.println(res);
		
		LivroDAO livroDAO = new LivroDAO();
		//livroDAO.inserirLivro(new Livro(0, "A arte da guerra", "Donald", "Ciencia", "Novo", 2010, 20000, 1));
		//livroDAO.inserirLivro(livro2);
//Livro livro = livroDAO.consultarLivro("id", "2");
//		LeitorDAO leitorDAO = new LeitorDAO();
//		Leitor leitor = leitorDAO.consultarLeitor("id", "1");
//		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
//		Funcionario funcionario = funcionarioDAO.consultarFuncionario("id", "2");
//		
//		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
//		Emprestimo emprestimo = emprestimoDAO.consultarEmprestimo("id", "1");
//		emprestimo.setDataDevolucao(new Date(System.currentTimeMillis()));
//		emprestimo.setDataEmprestimo(new Date(System.currentTimeMillis()));
//		emprestimo.setLeitor(leitor);
//		emprestimo.setFuncionario(funcionario);
//		System.out.println(livro.getTitulo());
//		System.out.println(leitor.getNome());
//		System.out.println(funcionario.getNome());
//		emprestimo.addLivro(livro);
//		emprestimoDAO.atualizarEmprestimo(emprestimo);
	
		Livro l1 = livroDAO.consultarLivro("id", "1");
		Livro l2 = livroDAO.consultarLivro("id", "2");
		
		Funcionario f = new FuncionarioDAO().consultarFuncionario("id", "2");
		Leitor l = new LeitorDAO().consultarLeitor("id", "1");
		
		EmprestimoDAO empDAO = new EmprestimoDAO();
		Emprestimo emp = new Emprestimo();
		emp.setLeitor(l);
		emp.setFuncionario(f);
		emp.addLivro(l2);
		emp.setDataEmprestimo(new Date());
		emp.setDataDevolucao(new Date());
		//empDAO.inserirEmprestimo(emp);
		
		ReservaDAO resDAO = new ReservaDAO();
		Reserva res = new Reserva();
		res.setLivro(l1);
		res.setFuncionario(f);
		res.setLeitor(l);
		res.setDataReserva(new Date());
		///resDAO.inserirReserva(res);
		Emprestimo e1 = empDAO.consultarEmprestimo("id", "1");
		System.out.println(e1.getLivros().get(0).getTitulo());
		empDAO.removerEmprestimo(e1);
		
		Reserva r1 = resDAO.consultarReserva("id", "1");
		System.out.println(r1.getLivro().getTitulo());
		resDAO.removerReserva(r1);
	}

	/**
	 * @param args the command line
	 * arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
