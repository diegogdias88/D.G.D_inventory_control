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
import dgdsoft.model.domain.ItemDeVenda;
import dgdsoft.model.domain.Produto;
import dgdsoft.model.domain.Venda;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    @FXML    private Label labelVendaNumeroNota;
    @FXML    private Label labelVendaSerie;
    
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
            labelVendaNumeroNota.setText(String.valueOf(venda.getNumeroNota()));
            labelVendaSerie.setText(String.valueOf(venda.getSerie()));
        } else {
            labelVendaCodigo.setText("");
            labelVendaData.setText("");
            labelVendaValor.setText("");
            labelVendaPago.setText("");
            labelVendaCliente.setText("");
        }
    }
    
    @FXML
    public void handleButtonInserir() throws IOException {
        Venda venda = new Venda();
        List<ItemDeVenda> listItensDeVenda = new ArrayList<>();
        venda.setItensDeVenda(listItensDeVenda);
        boolean buttonConfirmarClicked = showFXMLAnchorPaneProcessosVendasDialog(venda);
        if (buttonConfirmarClicked) {
            try {
                connection.setAutoCommit(false);
                vendaDAO.setConnection(connection);
                vendaDAO.inserir(venda);
                itemDeVendaDAO.setConnection(connection);
                produtoDAO.setConnection(connection);
                for (ItemDeVenda listItemDeVenda : venda.getItensDeVenda()) {
                    Produto produto = listItemDeVenda.getProduto();
                    listItemDeVenda.setVenda(vendaDAO.buscarUltimaVenda());
                    itemDeVendaDAO.inserir(listItemDeVenda);
                    produto.setQuantidade(produto.getQuantidade() - listItemDeVenda.getQuantidade());
                    produtoDAO.alterar(produto);
                }
                connection.commit();
                carregarTableViewVendas();
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(MovVendaController.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(MovVendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {

    }

    @FXML
    public void handleButtonRemover() throws IOException, SQLException {
        Venda venda = tableViewVendas.getSelectionModel().getSelectedItem();
        if (venda != null) {
            connection.setAutoCommit(false);
            vendaDAO.setConnection(connection);
            itemDeVendaDAO.setConnection(connection);
            produtoDAO.setConnection(connection);
            for (ItemDeVenda listItemDeVenda : venda.getItensDeVenda()) {
                Produto produto = listItemDeVenda.getProduto();
                produto.setQuantidade(produto.getQuantidade() + listItemDeVenda.getQuantidade());
                produtoDAO.alterar(produto);
                itemDeVendaDAO.remover(listItemDeVenda);
            }
            vendaDAO.remover(venda);
            connection.commit();
            carregarTableViewVendas();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma venda na Tabela!");
            alert.show();
        }
    }

    public boolean showFXMLAnchorPaneProcessosVendasDialog(Venda venda) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MovVendaDialogController.class.getResource("/dgdsoft/view/MovVendaDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registro de Vendas");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando a Venda no Controller.
        MovVendaDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setVenda(venda);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }

    
}
