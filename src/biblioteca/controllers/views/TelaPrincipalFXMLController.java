/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.controllers.views;

import biblioteca.controllers.LeitorController;
import biblioteca.controllers.LivroController;
import biblioteca.models.Leitor;
import biblioteca.models.Livro;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Jheyele Raquel
 */
public class TelaPrincipalFXMLController implements Initializable {

    // Controladores
    private final LeitorController leitorController = new LeitorController();
    private final LivroController livroController = new LivroController();
    // Campos do Painel de Cadastro de leitores
    @FXML
    private TextField tfCadLeitorNome;
    @FXML
    private TextField tfCadLeitorCpf;
    @FXML
    private TextField tfCadLeitorRua;
    @FXML
    private TextField tfCadLeitorBairro;
    @FXML
    private TextField tfCadLeitorNum;
    @FXML
    private TextField tfCadLeitorTelefone;
    @FXML
    private TextField tfCadLeitorEmail;
    
    @FXML
    private Button btnCadastrarLeitor;
    
     // Campos do Painel de Cadastro de livro
    @FXML
    private TextField tfCadLivroTitulo;
    @FXML
    private TextField tfCadLivroISPN;
    @FXML
    private TextField tfCadLivroCategoria;
    @FXML
    private TextField tfCadLivroEdicao;
    @FXML
    private TextField tfCadLivroAno;
    @FXML
    private TextField tfCadLivroAutor;
  
    @FXML
    private Button btnCadastrarLivro;
    
    // Campos de pesquisa de livros
    
    @FXML
    private TextField tfPesquisaFiltro;
    
    @FXML
    private ComboBox cbPesquisaFiltro;
    
    @FXML
    private TableView tvPesquisaTabela;
    
    
    // campos de emprestimo
    
    @FXML
    private TextField tfEmprestimoLivro;
    
    @FXML
    private TextField tfEmprestimoIdLivro;
    
    @FXML
    private TextField tfEmprestimoLeitor;
    
    @FXML
    private TextField tfEmprestimoIdLeitor;
    
    @FXML
    private Button btnEmprestimoAdicionar;
    
    @FXML
    private Button btnEmprestimoCadastrar;
    
    @FXML
    private TableView tvEmprestimoLivros;
    
    @FXML
    private TableView tvEmprestimoLeitores;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        // Pesquisa
        TableColumn colunaId = (TableColumn) tvPesquisaTabela.getColumns().get(0);
        TableColumn colunaTitulo = (TableColumn) tvPesquisaTabela.getColumns().get(1);
        TableColumn colunaAutor = (TableColumn) tvPesquisaTabela.getColumns().get(2);
        TableColumn colunaEdicao = (TableColumn) tvPesquisaTabela.getColumns().get(3);
        TableColumn colunaEstado = (TableColumn) tvPesquisaTabela.getColumns().get(4);
        
        colunaId.setCellValueFactory(new PropertyValueFactory("id"));
        colunaTitulo.setCellValueFactory(new PropertyValueFactory("titulo"));
        colunaAutor.setCellValueFactory(new PropertyValueFactory("autor"));
        colunaEdicao.setCellValueFactory(new PropertyValueFactory("edicao"));
        colunaEstado.setCellValueFactory(new PropertyValueFactory("status"));
        
        cbPesquisaFiltro.getItems().add("id");
        cbPesquisaFiltro.getItems().add("titulo");
        cbPesquisaFiltro.getItems().add("autor");
        cbPesquisaFiltro.getItems().add("status");
        cbPesquisaFiltro.getItems().add("edicao");
        
        tvPesquisaTabela.getItems().addAll(livroController.listarLivros());
        
        //Emprestimo Livro
        TableColumn colunaIdEm = (TableColumn) tvEmprestimoLivros.getColumns().get(0);
        TableColumn colunaTituloEm = (TableColumn) tvEmprestimoLivros.getColumns().get(1);
        TableColumn colunaAutorEm = (TableColumn) tvEmprestimoLivros.getColumns().get(2);
        TableColumn colunaEdicaoEm = (TableColumn) tvEmprestimoLivros.getColumns().get(3);
        TableColumn colunaEstadoEm = (TableColumn) tvEmprestimoLivros.getColumns().get(4);
        
        colunaIdEm.setCellValueFactory(new PropertyValueFactory("id"));
        colunaTituloEm.setCellValueFactory(new PropertyValueFactory("titulo"));
        colunaAutorEm.setCellValueFactory(new PropertyValueFactory("autor"));
        colunaEdicaoEm.setCellValueFactory(new PropertyValueFactory("edicao"));
        colunaEstadoEm.setCellValueFactory(new PropertyValueFactory("status"));
        
        tvEmprestimoLivros.getItems().addAll(livroController.listarLivros());
        
        //Emprestimo Leitor
        
        TableColumn colunaIdLeitor = (TableColumn) tvEmprestimoLeitores.getColumns().get(0);
        TableColumn colunaNomeLeitor = (TableColumn) tvEmprestimoLeitores.getColumns().get(1);
        TableColumn colunaCPFLeitor = (TableColumn) tvEmprestimoLeitores.getColumns().get(2);
        TableColumn colunaTelefoneLeitor = (TableColumn) tvEmprestimoLeitores.getColumns().get(3);
        
        colunaIdLeitor.setCellValueFactory(new PropertyValueFactory("id"));
        colunaNomeLeitor.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaCPFLeitor.setCellValueFactory(new PropertyValueFactory("cpf"));
        colunaTelefoneLeitor.setCellValueFactory(new PropertyValueFactory("telefone"));
    
        tvEmprestimoLeitores.getItems().addAll(leitorController.listarLeitores());
        
        
        
    } 
    
    public void pesquisarLivro(){
        String filtroSelecionado = (String) cbPesquisaFiltro.getValue();
        
        ArrayList livrosFiltrados = livroController.buscarLivros(filtroSelecionado, tfPesquisaFiltro.getText());
        
        tvPesquisaTabela.getItems().clear();
        tvPesquisaTabela.getItems().addAll(livrosFiltrados);
    }
    
    public void cadastrarLeitor(){
        String nome = tfCadLeitorNome.getText();
        String cpf = tfCadLeitorCpf.getText();
        String rua = tfCadLeitorRua.getText();
        String num = tfCadLeitorNum.getText();
        String bairro = tfCadLeitorBairro.getText();
        String telefone = tfCadLeitorTelefone.getText();
        String email = tfCadLeitorEmail.getText();
        
        Leitor leitor;
        leitor = leitorController.cadastrarLeitor(nome, cpf, rua + num, bairro, email, telefone);
    
    }
    
    public void cadastrarLivro(){
        String titulo = tfCadLivroTitulo.getText();
        int ISPN = parseInt(tfCadLivroISPN.getText());
        String categoria = tfCadLivroCategoria.getText();
        int edicao = parseInt(tfCadLivroEdicao.getText());
        int ano = parseInt(tfCadLivroAno.getText());
        String autor = tfCadLivroAutor.getText();
        
        Livro livro;
        livro = livroController.cadastrarLivro(titulo, autor, categoria, "D", ano, ISPN,edicao);
    }
    
    public void pesquisaLivroEmprestimo(){
        String coluna = "titulo";
        
        ArrayList livrosFiltrados = livroController.buscarLivros(coluna, tfEmprestimoLivro.getText());
        
        tvEmprestimoLivros.getItems().clear();
        tvEmprestimoLivros.getItems().addAll(livrosFiltrados);
    }
    
    public void pesquisaLeitorEmprestimo(){
        String coluna = "nome";
        
        Leitor leitoresFiltrados = leitorController.buscarLeitor(coluna, tfEmprestimoLeitor.getText());
        
        tvEmprestimoLeitores.getItems().clear();
        tvEmprestimoLeitores.getItems().addAll(leitoresFiltrados);
    }
    
    
    
}
