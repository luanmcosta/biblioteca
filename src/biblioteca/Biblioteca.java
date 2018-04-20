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
import java.io.IOException;
import java.util.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author luanm
 */
public class Biblioteca extends Application {

	@Override
	public void start(Stage stage) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("views/TelaPrincipalFXML.fxml"));
            Scene scene = new Scene(root);
        
            stage.setScene(scene);
            stage.show();
	}

	/**
	 * @param args the command line
	 * arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
