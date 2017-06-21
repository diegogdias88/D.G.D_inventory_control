package dgdsoft.controller;

import dgdsoft.model.dao.ProdutoDAO;
import dgdsoft.model.database.Database;
import dgdsoft.model.database.DatabaseFactory;
import dgdsoft.model.domain.Produto;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class CadProdutoController implements Initializable {

    @FXML   private Label ProdutoCodigo;
    @FXML   private Label ProdutoNome;
    @FXML   private Label ProdutoFornecedor;
    @FXML   private Label ProdutoCNAE;
    @FXML   private Label ProdutoLucro;
    @FXML   private Label ProdutoUnidade;
    @FXML   private Label ProdutoEstoque;
    @FXML   private Label ProdutoValorCusto;
    @FXML   private Label ProdutoValorVenda;
    @FXML   private Label ProdutoCategoria;
    @FXML   private Label ProdutoCodBarras;
    @FXML   private Label ProdutoEstoqueMin;
    
    @FXML    private TableView<Produto> tableViewProdutos;
    @FXML    private TableColumn<Produto, String> tableColumnProdutoNome;
    @FXML    private TableColumn<Produto, String> tableColumnQtdEstoque;
    
    
    @FXML
    private List<Produto> listProdutos;
    private ObservableList<Produto> observableListProdutos;
    
    
     //atributos para manipulação de banco de dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ProdutoDAO produtoDao = new ProdutoDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       produtoDao.setConnection(connection);
        carregarTableVielProduto();
        
         //listem acionado diante de qualquer alterações na seleção de itens da Tableview
        tableViewProdutos.getSelectionModel().selectedItemProperty().addListener(
        (observable,oldValue,newValue) -> selecionarItemTableViewProdutos(newValue));
    }    
    
    public void carregarTableVielProduto(){
        //configura as colunas para exibir o nome e cpf na tabela (TableView)
        tableColumnProdutoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnQtdEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        //chama o metodo listar
        listProdutos = produtoDao.listar();
        //converte um observableList para um arraylist
        observableListProdutos = FXCollections.observableArrayList(listProdutos);
        //passa o observablelist para a tableview
        tableViewProdutos.setItems(observableListProdutos);
    }
    
    public void selecionarItemTableViewProdutos(Produto produto){
        if(produto != null){
            ProdutoCodigo.setText(String.valueOf(produto.getCdProduto()));
            ProdutoNome.setText(produto.getNome());
            ProdutoFornecedor.setText(produto.getFornecedor());
            ProdutoEstoque.setText(String.valueOf(produto.getEstoque()));
            ProdutoValorCusto.setText(String.valueOf(produto.getValorcusto()));
            ProdutoValorVenda.setText(String.valueOf(produto.getValorvenda()));
            ProdutoCNAE.setText(produto.getCnae());
            ProdutoUnidade.setText(produto.getUnidade());
            ProdutoCategoria.setText(produto.getCategoria());
            ProdutoCodBarras.setText(String.valueOf(produto.getCodbarras()));
            ProdutoEstoqueMin.setText(String.valueOf(produto.getEstoqueMin()));
        }else{
            //ClienteNome.setText("");
        }
    }
    
}
