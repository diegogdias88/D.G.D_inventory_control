package dgdsoft.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import dgdsoft.model.dao.FornecedorDAO;
import dgdsoft.model.database.Database;
import dgdsoft.model.database.DatabaseFactory;
import dgdsoft.model.domain.Fornecedor;
import dgdsoft.model.domain.Produto;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class CadProdutoDialogController implements Initializable {

    @FXML private   TextField ProdutoNome;
    @FXML private   TextField ProdutoCodBarras;
    @FXML private   ComboBox boxFornecedor;
    @FXML private   ComboBox boxCnae;
    @FXML private   ComboBox boxCategoria;
    @FXML private   ComboBox boxUnidade;
    @FXML private   TextField ProdutoValorCusto;
    @FXML private   ComboBox boxLucro;
    @FXML private   TextField ProdutoValorVenda;
    @FXML private   TextField ProdutoEstoque;
    @FXML private   TextField ProdutoEstoqueMin;
    
    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    private Produto produto;
    
    //atributos para manipulação de banco de dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final FornecedorDAO fornecedorDao = new FornecedorDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fornecedorDao.setConnection(connection);
        
        boxFornecedor();
    }    
    
    //Carrega todos os fornecedores para cadastro
    public void boxFornecedor(){
        List<Fornecedor> listaFornecedor = fornecedorDao.listar();
        listaFornecedor.forEach((nomeFornecedor) -> {
            boxFornecedor.getItems().add(nomeFornecedor);
        });
    }
    
    //Carrega todos os CNAES para cadastro
    public void boxCnae(){
        
    }
    
    //Define todas as categorias possiveis a serem cadastradas
    public void boxCategoria(){
        
    }
    
    //Define todas as unidades possiveis a serem cadastradas
    public void boxUnidade(){
        
    }
    
    //Define porcentagem de lucro para cada produto
    public void boxLucro(){
        
    }
    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }

    public void setBtnConfirmarClicked(boolean btnConfirmarClicked) {
        this.btnConfirmarClicked = btnConfirmarClicked;
    }

    public Produto getProduto() {
        return produto;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
        this.ProdutoNome.setText(produto.getNome());
        this.boxFornecedor.valueProperty().set(produto.getFornecedor());
        this.boxCategoria.valueProperty().set(produto.getCategoria());
        this.ProdutoCodBarras.setText(String.valueOf(produto.getCodbarras()));
        this.ProdutoEstoqueMin.setText(String.valueOf(produto.getEstoqueMin()));
        this.ProdutoValorCusto.setText(String.valueOf(produto.getValorcusto()));
        this.ProdutoValorVenda.setText(String.valueOf(produto.getValorvenda()));
        this.ProdutoEstoque.setText(String.valueOf(produto.getEstoque()));
        this.boxUnidade.valueProperty().set(this);
        this.boxCnae.valueProperty().set(this);
    }
    
    @FXML
    public void handleBtnConfirmar() throws Exception{
        produto.setNome(ProdutoNome.getText());
        produto.setFornecedor(String.valueOf(boxFornecedor.valueProperty().get()));
        produto.setCategoria(String.valueOf(boxCategoria.valueProperty().get()));
        produto.setCodbarras(Integer.parseInt(ProdutoCodBarras.getText()));
        produto.setEstoqueMin(Integer.parseInt(ProdutoEstoqueMin.getText()));
        produto.setValorcusto(Double.parseDouble(ProdutoValorCusto.getText()));
        produto.setValorvenda(Double.parseDouble(ProdutoValorVenda.getText()));
        produto.setEstoque(Integer.parseInt(ProdutoEstoque.getText()));
        produto.setUnidade(String.valueOf(boxUnidade.valueProperty().get()));
        produto.setCnae(String.valueOf(boxCnae.valueProperty().get()));
        
        btnConfirmarClicked = true;
        dialogStage.close();
    }
    
    @FXML
    private void handleBtnCancelar(ActionEvent event){
	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	alert.setTitle("Aviso!");//titulo
	alert.setHeaderText("Você esta prestes a fechar esta tela.");//exto do corpo do texto
	alert.setContentText("Deseja realmente sair?");//texto  em baixo
	
	ButtonType buttonSim = new ButtonType("Sim");//1 bot~]ao opção
	ButtonType buttonNao = new ButtonType("Não");//2 bot~]ao opção
	
	alert.getButtonTypes().setAll(buttonSim,buttonNao); //coloca os botões no alerta
	
	Optional<ButtonType> result = alert.showAndWait();//cria um result onde é para esperar clicar em uma tela para executar uma função
	if(result.get() ==buttonSim){
		dialogStage.close();
	}else if(result.get() == buttonNao){
		alert.close();
	}
    }
}
