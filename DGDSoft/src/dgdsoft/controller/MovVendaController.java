/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgdsoft.controller;

import dgdsoft.model.dao.ItemDeVendaDAO;
import dgdsoft.model.dao.ProdutoDAO;
import dgdsoft.model.dao.VendaDAO;
import dgdsoft.model.database.Database;
import dgdsoft.model.database.DatabaseFactory;
import dgdsoft.model.domain.Venda;
import java.net.URL;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class MovVendaController implements Initializable {

    @FXML    private TableView<Venda> tableViewVendas;
    @FXML    private TableColumn<Venda, Integer> tableColumnVendaCodigo;
    @FXML    private TableColumn<Venda, Date> tableColumnVendaData;
    @FXML    private TableColumn<Venda, Venda> tableColumnVendaCliente;
    
    @FXML    private Button buttonInserir;
    @FXML    private Button buttonAlterar;
    @FXML    private Button buttonRemover;
    
    @FXML    private Label labelVendaCodigo;
    @FXML    private Label labelVendaData;
    @FXML    private Label labelVendaValor;
    @FXML    private Label labelVendaPago;
    @FXML    private Label labelVendaCliente;
    
    private List<Venda> listVendas;
    private ObservableList<Venda> observableListVendas;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final VendaDAO vendaDAO = new VendaDAO();
    private final ItemDeVendaDAO itemDeVendaDAO = new ItemDeVendaDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vendaDAO.setConnection(connection);

        carregarTableViewVendas();

        // Limpando a exibição dos detalhes da venda
        selecionarItemTableViewVendas(null);

        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        tableViewVendas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewVendas(newValue));
    }    
    
    public void carregarTableViewVendas() {
        tableColumnVendaCodigo.setCellValueFactory(new PropertyValueFactory<>("cdVenda"));
        tableColumnVendaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableColumnVendaCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));

        listVendas = vendaDAO.listar();

        observableListVendas = FXCollections.observableArrayList(listVendas);
        tableViewVendas.setItems(observableListVendas);
    }

    public void selecionarItemTableViewVendas(Venda venda) {
        if (venda != null) {
            labelVendaCodigo.setText(String.valueOf(venda.getCdVenda()));
            labelVendaData.setText(String.valueOf(venda.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            labelVendaValor.setText(String.format("%.2f", venda.getValor()));
            labelVendaPago.setText(String.valueOf(venda.getPago()));
            labelVendaCliente.setText(venda.getCliente().toString());
        } else {
            labelVendaCodigo.setText("");
            labelVendaData.setText("");
            labelVendaValor.setText("");
            labelVendaPago.setText("");
            labelVendaCliente.setText("");
        }
    }
    
}
