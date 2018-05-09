/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.controllers.views;

import biblioteca.controllers.FuncionarioController;
import biblioteca.models.Funcionario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jheyele Raquel
 */
public class TelaLoginFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField tfLogin;
    @FXML
    private TextField tfSenha;
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    public void autenticar() throws IOException, Throwable{
        
        String login =  tfLogin.getText();
        String senha =  tfSenha.getText();  
        FuncionarioController funcionarioCtrl = new FuncionarioController();
        Funcionario funcionario = funcionarioCtrl.autenticarFuncionario(login, senha);
      
        if(funcionario == null)
            return;
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(biblioteca.Biblioteca.class.getResource("views/TelaPrincipalFXML.fxml"));
        Parent root = (Parent)loader.load();
        TelaPrincipalFXMLController controladorTelaPrincipal = loader.<TelaPrincipalFXMLController>getController();
        controladorTelaPrincipal.passarFuncionario(funcionario);
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
     
        // Fechar tela de login
        Stage stageLogin = (Stage) tfLogin.getScene().getWindow();
        stageLogin.close();
        
    }
    
}
