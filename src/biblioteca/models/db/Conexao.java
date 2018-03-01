/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.models.db;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author luanm
 */
public class Conexao {
    private static final String db_ip = "localhost";
    private static final String db_banco = "biblioteca";
    private static final String db_usuario = "root";
    private static final String db_senha = "123";
    
    public static Connection conexao;

    public Conexao() {
    }
    
    public static Connection getConexao() throws SQLException{
        if(conexao == null)
            conexao = (Connection) DriverManager.getConnection( "jdbc:mysql://" + db_ip + "/" + db_banco, db_usuario, db_senha);
        return conexao;
    }
    
}
