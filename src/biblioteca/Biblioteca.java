package biblioteca;

import biblioteca.models.db.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author luanm
 */
public class Biblioteca extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Connection con = Conexao.getConexao();
            System.out.println(con.isClosed());
        } catch (SQLException ex) {
            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
