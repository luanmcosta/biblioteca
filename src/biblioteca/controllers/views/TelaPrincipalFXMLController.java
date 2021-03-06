/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.controllers.views;

import biblioteca.controllers.EmprestimoController;
import biblioteca.controllers.LeitorController;
import biblioteca.controllers.LivroController;
import biblioteca.controllers.ReservaController;
import biblioteca.models.Emprestimo;
import biblioteca.models.Funcionario;
import biblioteca.models.Leitor;
import biblioteca.models.Livro;
import biblioteca.models.Reserva;
import static java.lang.Integer.parseInt;
import java.net.URL;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    Funcionario funcionario;
    // Controladores
    private final LeitorController leitorController = new LeitorController();
    private final LivroController livroController = new LivroController();
    private final EmprestimoController emprestimoController = new EmprestimoController();
    private final ReservaController reservaController = new ReservaController();
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
    
    @FXML
    private Button btnPesquisaApagar;
    
    @FXML
    private TextField tfPesquisaFiltroLeitor;
    
    @FXML
    private ComboBox cbPesquisaFiltroLeitor;
    
    @FXML
    private TableView tvPesquisaTabelaLeitor;
    
    @FXML
    private Button btnPesquisaApagarLeitor;
    
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
    
    @FXML
    private TableView tvEmprestimoLista;
    
    @FXML
    private Label lblLeitorSelecionadoEmprestimoNome;
    
    ArrayList<Livro> livrosSelecionadosEmprestimo;
    
    private Leitor leitorSelecionadoEmprestimo;
    
    // campos reserva 
       
    @FXML
    private TextField tfReservaLeitor;
    
    @FXML
    private TextField tfReservaLivro;
    
    @FXML
    private Button btnReserva;
    
    @FXML
    private TableView tvReservaLivros;
    
    @FXML
    private TableView tvReservaLeitores;
    
    private Leitor leitorSelecionadoReserva;
    
    private Livro livroSelecionadoReserva;
    
    // consulta de emprestimos
    
    @FXML
    private TableView tvConsultaEmprestimo;
    
    @FXML
    private TableView tvConsultaEmprestimoLivro;
    
    @FXML
    private TextField tfConsultaEmprestimo;
    
    @FXML
    private Button btnConsultaEmprestimoVisualizar;
    
    @FXML
    private Button RenovarEmprestimo;
    
    @FXML
    private Button DevolverLivro;
    
    // consulta de reservas
    
    @FXML
    private TableView tvConsultaReserva;
    
    @FXML
    private TableView tvConsultaReservaLivro;
    
    @FXML
    private TextField tfConsultaReserva;
    
    @FXML
    private Button btnConsultaReservaVisualizar;
    
    @FXML
    private Button btnConsultaReservaEncerrar;

    
    // Informações da Tela Inicio
    @FXML
    private Label lblInicioNomeFuncionario;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        livrosSelecionadosEmprestimo = new ArrayList<>();
        
        // Pesquisa Livros
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
        
        cbPesquisaFiltro.getItems().add("titulo");
        cbPesquisaFiltro.getItems().add("autor");
        cbPesquisaFiltro.getItems().add("status");
        cbPesquisaFiltro.getItems().add("edicao");
        
        tvPesquisaTabela.getItems().addAll(livroController.listarLivros());
        cbPesquisaFiltro.getSelectionModel().select(0);
        
        
        // Pesquisa Leitor
        TableColumn colunaIdLeitor2 = (TableColumn) tvPesquisaTabelaLeitor.getColumns().get(0);
        TableColumn colunaNomeLeitor2 = (TableColumn) tvPesquisaTabelaLeitor.getColumns().get(1);
        TableColumn colunaCPFLeitor2 = (TableColumn) tvPesquisaTabelaLeitor.getColumns().get(2);
        TableColumn colunaTelefoneLeitor2 = (TableColumn) tvPesquisaTabelaLeitor.getColumns().get(3);
        
        colunaIdLeitor2.setCellValueFactory(new PropertyValueFactory("id"));
        colunaNomeLeitor2.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaCPFLeitor2.setCellValueFactory(new PropertyValueFactory("cpf"));
        colunaTelefoneLeitor2.setCellValueFactory(new PropertyValueFactory("telefone"));
        
        cbPesquisaFiltroLeitor.getItems().add("nome");
        cbPesquisaFiltroLeitor.getItems().add("cpf");
        cbPesquisaFiltroLeitor.getItems().add("telefone");
        
        tvPesquisaTabelaLeitor.getItems().addAll(leitorController.listarLeitores());
        cbPesquisaFiltroLeitor.getSelectionModel().select(0);
        
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
        
        tvEmprestimoLivros.getItems().addAll(livroController.listarLivrosNaoEmprestados());
        
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
        
        // Emprestimo Lista de Livros
        
        TableColumn colunaTituloLivros = (TableColumn) tvEmprestimoLista.getColumns().get(0);
        TableColumn colunaAutorLivros = (TableColumn) tvEmprestimoLista.getColumns().get(1);
        TableColumn colunaEdicaoLivros = (TableColumn) tvEmprestimoLista.getColumns().get(2);
        
        colunaTituloLivros.setCellValueFactory(new PropertyValueFactory("titulo"));
        colunaAutorLivros.setCellValueFactory(new PropertyValueFactory("autor"));
        colunaEdicaoLivros.setCellValueFactory(new PropertyValueFactory("edicao"));
        
        
        //Reserva Livro
        TableColumn colunaIdRe = (TableColumn) tvReservaLivros.getColumns().get(0);
        TableColumn colunaTituloRe = (TableColumn) tvReservaLivros.getColumns().get(1);
        TableColumn colunaAutorRe = (TableColumn) tvReservaLivros.getColumns().get(2);
        TableColumn colunaEdicaoRe = (TableColumn) tvReservaLivros.getColumns().get(3);
        TableColumn colunaEstadoRe = (TableColumn) tvReservaLivros.getColumns().get(4);
        
        colunaIdRe.setCellValueFactory(new PropertyValueFactory("id"));
        colunaTituloRe.setCellValueFactory(new PropertyValueFactory("titulo"));
        colunaAutorRe.setCellValueFactory(new PropertyValueFactory("autor"));
        colunaEdicaoRe.setCellValueFactory(new PropertyValueFactory("edicao"));
        colunaEstadoRe.setCellValueFactory(new PropertyValueFactory("status"));
        
        tvReservaLivros.getItems().addAll(livroController.listarLivrosEmprestados());
        
        //Reserva Leitor
        
        TableColumn colunaIdLeitorR = (TableColumn) tvReservaLeitores.getColumns().get(0);
        TableColumn colunaNomeLeitorR = (TableColumn) tvReservaLeitores.getColumns().get(1);
        TableColumn colunaCPFLeitorR = (TableColumn) tvReservaLeitores.getColumns().get(2);
        TableColumn colunaTelefoneLeitorR = (TableColumn) tvReservaLeitores.getColumns().get(3);
        
        colunaIdLeitorR.setCellValueFactory(new PropertyValueFactory("id"));
        colunaNomeLeitorR.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaCPFLeitorR.setCellValueFactory(new PropertyValueFactory("cpf"));
        colunaTelefoneLeitorR.setCellValueFactory(new PropertyValueFactory("telefone"));
    
        tvReservaLeitores.getItems().addAll(leitorController.listarLeitores());
    
        
        // Consulta de emprestimo
        
        TableColumn colunaLivro = (TableColumn) tvConsultaEmprestimo.getColumns().get(0);
        TableColumn colunaLeitor = (TableColumn) tvConsultaEmprestimo.getColumns().get(1);
        TableColumn colunaDataEmprestimo = (TableColumn) tvConsultaEmprestimo.getColumns().get(2);
        TableColumn colunaDataDevolucao = (TableColumn) tvConsultaEmprestimo.getColumns().get(3);
      
        colunaLivro.setCellValueFactory(new PropertyValueFactory("id"));
        colunaLeitor.setCellValueFactory(new PropertyValueFactory("leitor"));
        colunaDataEmprestimo.setCellValueFactory(new PropertyValueFactory("dataEmprestimo"));
        colunaDataDevolucao.setCellValueFactory(new PropertyValueFactory("dataDevolucao"));
        
        tvConsultaEmprestimo.getItems().addAll(emprestimoController.listarEmprestimos());
        
        TableColumn colunaLivroTitulo = (TableColumn) tvConsultaEmprestimoLivro.getColumns().get(0);
        TableColumn colunaLivroAutor = (TableColumn) tvConsultaEmprestimoLivro.getColumns().get(1);
        TableColumn colunaLivroEdicao = (TableColumn) tvConsultaEmprestimoLivro.getColumns().get(2);
      
        colunaLivroTitulo.setCellValueFactory(new PropertyValueFactory("titulo"));
        colunaLivroAutor.setCellValueFactory(new PropertyValueFactory("autor"));
        colunaLivroEdicao.setCellValueFactory(new PropertyValueFactory("edicao"));
        
        
        // Consulta de reserva
        
        TableColumn colunaReservaLivro = (TableColumn) tvConsultaReserva.getColumns().get(0);
        TableColumn colunaReservaLeitor = (TableColumn) tvConsultaReserva.getColumns().get(1);
        TableColumn colunaReservaDataEmprestimo = (TableColumn) tvConsultaReserva.getColumns().get(2);
      
        colunaReservaLivro.setCellValueFactory(new PropertyValueFactory("id"));
        colunaReservaLeitor.setCellValueFactory(new PropertyValueFactory("leitor"));
        colunaReservaDataEmprestimo.setCellValueFactory(new PropertyValueFactory("dataReserva"));
        
        tvConsultaReserva.getItems().addAll(reservaController.listarReservas());
        
        TableColumn colunaReservaLivroTitulo = (TableColumn) tvConsultaReservaLivro.getColumns().get(0);
        TableColumn colunaReservaLivroAutor = (TableColumn) tvConsultaReservaLivro.getColumns().get(1);
        TableColumn colunaReservaLivroEdicao = (TableColumn) tvConsultaReservaLivro.getColumns().get(2);
      
        colunaReservaLivroTitulo.setCellValueFactory(new PropertyValueFactory("titulo"));
        colunaReservaLivroAutor.setCellValueFactory(new PropertyValueFactory("autor"));
        colunaReservaLivroEdicao.setCellValueFactory(new PropertyValueFactory("edicao"));
        
       
    } 
    
    public void pesquisarLivro(){
        String filtroSelecionado = (String) cbPesquisaFiltro.getValue();
        
        ArrayList<Livro> livrosFiltrados = livroController.buscarLivros(filtroSelecionado, tfPesquisaFiltro.getText());
        
        tvPesquisaTabela.getItems().clear();
        tvPesquisaTabela.getItems().addAll(livrosFiltrados);
    }
    
    public void apagarLivro(){
        Livro livroSelecionado = (Livro) tvPesquisaTabela.getSelectionModel().getSelectedItem();
        int id = livroSelecionado.getId();
        
        System.out.println(id);
        livroController.removerLivro(id);
        tvPesquisaTabela.getItems().clear();
        atualizarTabelas();
    }
    
    public void pesquisarLeitor(){
//        if(tfPesquisaFiltroLeitor.getText().length() == 0){
//            atualizarTabelas();
//            return;
//        }
//        
        String filtroSelecionado = (String) cbPesquisaFiltroLeitor.getValue();
        
        ArrayList<Leitor> leitorFiltrados = leitorController.buscarLeitores(filtroSelecionado, tfPesquisaFiltroLeitor.getText());
        
        
        tvPesquisaTabelaLeitor.getItems().clear();
        tvPesquisaTabelaLeitor.getItems().addAll(leitorFiltrados);
    }
    
    public void apagarLeitor(){
        Leitor leitorSelecionado = (Leitor) tvPesquisaTabelaLeitor.getSelectionModel().getSelectedItem();
        int id = leitorSelecionado.getId();
        
        leitorController.removerLeitor(id);
        tvPesquisaTabelaLeitor.getItems().clear();
        atualizarTabelas();
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
        
        tfCadLeitorNome.setText("");
        tfCadLeitorCpf.setText("");
        tfCadLeitorRua.setText("");
        tfCadLeitorNum.setText("");
        tfCadLeitorBairro.setText("");
        tfCadLeitorTelefone.setText("");
        tfCadLeitorEmail.setText("");
        atualizarTabelas();
    
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
        
        tfCadLivroTitulo.setText("");
        tfCadLivroISPN.setText("");
        tfCadLivroCategoria.setText("");
        tfCadLivroEdicao.setText("");
        tfCadLivroAno.setText("");
        tfCadLivroAutor.setText("");
        atualizarTabelas();
        
    }
    
    public void pesquisaLivroEmprestimo(){
        String coluna = "titulo";
        
        ArrayList livrosFiltrados = livroController.buscarLivros(coluna, tfEmprestimoLivro.getText());
        
        tvEmprestimoLivros.getItems().clear();
        tvEmprestimoLivros.getItems().addAll(livrosFiltrados);
    }
    
    public void pesquisaLeitorEmprestimo(){
        String coluna = "nome";
        
        ArrayList <Leitor> leitoresFiltrados = leitorController.buscarLeitores(coluna, tfEmprestimoLeitor.getText());
        
        tvEmprestimoLeitores.getItems().clear();
        tvEmprestimoLeitores.getItems().addAll(leitoresFiltrados);
    }
    
 
    
    public void passarFuncionario(Funcionario func){
        this.funcionario = func;
        this.lblInicioNomeFuncionario.setText(func.getNome());
    }
   
    public void adicionarLivroEmprestimo(){
        Livro livroSelecionado = (Livro) tvEmprestimoLivros.getSelectionModel().getSelectedItem();
        livrosSelecionadosEmprestimo.add(livroSelecionado);
        
        tvEmprestimoLista.getItems().clear();
        tvEmprestimoLista.getItems().addAll(livrosSelecionadosEmprestimo);
    }
    
    public void selecionarLeitorEmprestimo(){
        leitorSelecionadoEmprestimo = (Leitor) tvEmprestimoLeitores.getSelectionModel().getSelectedItem();
        lblLeitorSelecionadoEmprestimoNome.setText(leitorSelecionadoEmprestimo.getNome());
    
    }
    
    public void removerLivroEmprestimoLista(){
        Livro livroSelecionado = (Livro) tvEmprestimoLista.getSelectionModel().getSelectedItem();
        livrosSelecionadosEmprestimo.remove(livroSelecionado);
        tvEmprestimoLista.getItems().clear();
        tvEmprestimoLista.getItems().addAll(livrosSelecionadosEmprestimo);
    }
    
    
    public void cadastrarEmprestimo(){
        Date dataEmprestimo = new Date();
        Date dataDevolucao = new Date(dataEmprestimo.getTime() + (1000 * 60 * 60 * 24) * 7);
        System.out.println(dataEmprestimo.toString());
        System.out.println(dataDevolucao.toString());
        emprestimoController.realizarEmprestimo(livrosSelecionadosEmprestimo, leitorSelecionadoEmprestimo, funcionario, dataEmprestimo, dataDevolucao);
        atualizarTabelas();
    }
    
    public void pesquisaLivroReserva(){
        String coluna = "titulo";
        
        ArrayList livrosFiltrados = livroController.buscarLivros(coluna, tfReservaLivro.getText());
        
        tvReservaLivros.getItems().clear();
        tvReservaLivros.getItems().addAll(livrosFiltrados);
    }
    
    public void pesquisaLeitorReserva(){
        String coluna = "nome";
        
        ArrayList <Leitor> leitoresFiltrados = leitorController.buscarLeitores(coluna, tfReservaLeitor.getText());
        
        tvReservaLeitores.getItems().clear();
        tvReservaLeitores.getItems().addAll(leitoresFiltrados);
    }
    
    public void selecionarLeitorReserva(){
        leitorSelecionadoReserva = (Leitor) tvReservaLeitores.getSelectionModel().getSelectedItem();
    }
    
    public void selecionarLivroReserva(){
        livroSelecionadoReserva = (Livro) tvReservaLivros.getSelectionModel().getSelectedItem();
    }
    
    
    
     public void ReservarLivro(){
        selecionarLeitorReserva();
        selecionarLivroReserva();
        ReservaController reservaCtrl = new ReservaController();
        reservaCtrl.inserirReserva(livroSelecionadoReserva, leitorSelecionadoReserva, funcionario, new Date());
        
        tfReservaLeitor.setText("");
        tfReservaLivro.setText("");
        atualizarTabelas();
    }
    
     
     
     public void visualizarDadosEmprestimo(){
         Emprestimo emprestimoSelecionado = (Emprestimo) tvConsultaEmprestimo.getSelectionModel().getSelectedItem();
         tvConsultaEmprestimoLivro.getItems().clear();
         tvConsultaEmprestimoLivro.getItems().addAll(emprestimoSelecionado.getLivros());

     }
    
     public void devolverLivroEmprestimo(){
         Livro livroSelecionado = (Livro) tvConsultaEmprestimoLivro.getSelectionModel().getSelectedItem();
         livroSelecionado.setEmprestimo(NULL);
         livroController.atualizarLivro(livroSelecionado);
         tvConsultaEmprestimoLivro.getItems().remove(livroSelecionado);
     }
     
     public void renovarEmprestimo(){
         Emprestimo emprestimoSelecionado = (Emprestimo) tvConsultaEmprestimo.getSelectionModel().getSelectedItem();
         emprestimoSelecionado.setDataDevolucao(new Date());
         emprestimoController.atualizarEmprestimo(emprestimoSelecionado);
     }
     
     public void encerrarEmprestimo(){
         Emprestimo emprestimoSelecionado = (Emprestimo) tvConsultaEmprestimo.getSelectionModel().getSelectedItem();
         emprestimoController.removerEmprestimo(emprestimoSelecionado.getId());
         tvConsultaEmprestimo.getItems().remove(emprestimoSelecionado);
         
         atualizarTabelas();
     }
     
     
     // Reservas
     public void visualizarDadosReserva(){
         Reserva reservaSelecionado = (Reserva) tvConsultaReserva.getSelectionModel().getSelectedItem();
         tvConsultaReservaLivro.getItems().clear();
         tvConsultaReservaLivro.getItems().addAll(reservaSelecionado.getLivro());

     }
    
     
     public void encerrarReserva(){
         Reserva reservaSelecionado = (Reserva) tvConsultaReserva.getSelectionModel().getSelectedItem();
         reservaController.removerReserva(reservaSelecionado.getId());
         tvConsultaEmprestimo.getItems().remove(reservaSelecionado);
         atualizarTabelas();
     }

     
     
     public void atualizarTabelas(){
         // Limpar todas as tabelas
         tvConsultaEmprestimo.getItems().clear();
         tvConsultaEmprestimoLivro.getItems().clear();
         tvEmprestimoLeitores.getItems().clear();
         tvEmprestimoLista.getItems().clear();
         tvEmprestimoLivros.getItems().clear();
         tvReservaLeitores.getItems().clear();
         tvReservaLivros.getItems().clear();
         tvPesquisaTabelaLeitor.getItems().clear();
         tvPesquisaTabela.getItems().clear();
         tvConsultaEmprestimo.getItems().clear();
         tvConsultaReserva.getItems().clear();
         
         // Limpar campos de pesquisa
         tfPesquisaFiltroLeitor.setText("");
         tfConsultaEmprestimo.setText("");
         tfPesquisaFiltro.setText("");
         tfEmprestimoLivro.setText("");
         tfEmprestimoLeitor.setText("");
         tfReservaLeitor.setText("");
         tfReservaLivro.setText("");
         
         // Mostrar dados nas tabelas padrões
         tvPesquisaTabela.getItems().addAll(livroController.listarLivros());
         tvPesquisaTabelaLeitor.getItems().addAll(leitorController.listarLeitores());
         tvEmprestimoLivros.getItems().addAll(livroController.listarLivrosNaoEmprestados());
         tvEmprestimoLeitores.getItems().addAll(leitorController.listarLeitores());
         tvConsultaEmprestimo.getItems().addAll(emprestimoController.listarEmprestimos());
         tvReservaLivros.getItems().addAll(livroController.listarLivrosEmprestados());
         tvReservaLeitores.getItems().addAll(leitorController.listarLeitores());
         tvConsultaReserva.getItems().addAll(reservaController.listarReservas());
     }
}
