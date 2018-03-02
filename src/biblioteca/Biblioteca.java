package biblioteca;

import biblioteca.models.db.Conexao;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            PreparedStatement pre = con.prepareStatement("insert into teste (nome) values ('Dois')", Statement.RETURN_GENERATED_KEYS);
            pre.executeUpdate();
            ResultSet res = pre.getGeneratedKeys();
            res.next();
            System.out.println(res.getInt(1));
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
