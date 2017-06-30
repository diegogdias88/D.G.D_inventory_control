package dgdsoft.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class CadastroController implements Initializable {

    @FXML   private AnchorPane anchorPane;
    @FXML   private Button btnCadCliente;  
    @FXML   private Button btnCadFornecedor;
    @FXML   private Button btnCadProduto;
    @FXML   private Button btnHome;
   
    @FXML   private ImageView imgExit;
    @FXML   private ImageView ingLoggout;
    @FXML   private ImageView imgInfo;
    
    @FXML   private ImageView ingHome;
    @FXML   private ImageView ingCliente;
    @FXML   private ImageView ingFornecedor;
    @FXML   private ImageView ingProduto;
   
    String defultStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:none";

    String activeStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:#FF4E3C";

    Image home = new Image("/dgdsoft/imagem/home_1.png");
    Image homeRed = new Image("/dgdsoft/imagem/homeRed.png");
    Image cliente = new Image("/dgdsoft/imagem/employe.png");
    Image clienteRed = new Image("/dgdsoft/imagem/employeRed.png");
    Image fornecedor = new Image("/dgdsoft/imagem/fornec.png");
    Image fornecedorRed = new Image("/dgdsoft/imagem/fornecRed.png");
    Image produto = new Image("/dgdsoft/imagem/stock.png");
    Image produtoRed = new Image("/dgdsoft/imagem/stockRed.png");
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void exit(MouseEvent event) {
        Platform.exit();
    }
    
        
     @FXML
    private void goHomeAA(MouseEvent event) throws IOException {
        homeActive();
        Stage newStage = new Stage();
        newStage.initStyle(StageStyle.TRANSPARENT);
        Parent parent = FXMLLoader.load(getClass().getResource("/dgdsoft/view/MainDGDPrincipal.fxml"));
        Scene scene = new Scene(parent);
        newStage.setScene(scene);
        newStage.show();
        imgInfo.getScene().getWindow().hide();
    }
    
     @FXML
    private void goHomeBB(ActionEvent event) throws IOException {
        homeActive();
        Stage newStage = new Stage();
        newStage.initStyle(StageStyle.TRANSPARENT);
        Parent parent = FXMLLoader.load(getClass().getResource("/dgdsoft/view/MainDGDPrincipal.fxml"));
        Scene scene = new Scene(parent);
        newStage.setScene(scene);
        newStage.show();
        btnHome.getScene().getWindow().hide();
    }
    
     @FXML
    private void goLogin(MouseEvent event) throws IOException {
        Stage newStage = new Stage();
        newStage.initStyle(StageStyle.TRANSPARENT);
        Parent parent = FXMLLoader.load(getClass().getResource("/dgdsoft/view/MainDGD.fxml"));
        Scene scene = new Scene(parent);
        newStage.setScene(scene);
        newStage.show();
        ingLoggout.getScene().getWindow().hide();
    }
    
    @FXML
    public void handleBtnCadastrosCliente(ActionEvent event) throws IOException{
        clienteActive();
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/dgdsoft/view/CadCliente.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleBtndastrosFornecedor(ActionEvent event) throws IOException{
        fornecActive();
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/dgdsoft/view/CadFornecedor.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleBtnCadastrosProduto(ActionEvent event) throws IOException{
        produtoActive();
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/dgdsoft/view/CadProduto.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    private void homeActive(){
       ingHome.setImage(homeRed);
       ingCliente.setImage(cliente);
       ingFornecedor.setImage(fornecedor);
       ingProduto.setImage(produto);
       btnHome.setStyle(activeStyle);
       btnCadCliente.setStyle(defultStyle);
       btnCadFornecedor.setStyle(defultStyle);
       btnCadProduto.setStyle(defultStyle);
    }
    
    private void clienteActive(){
       ingHome.setImage(home);
       ingCliente.setImage(clienteRed);
       ingFornecedor.setImage(fornecedor);
       ingProduto.setImage(produto);
       btnHome.setStyle(defultStyle);
       btnCadCliente.setStyle(activeStyle);
       btnCadFornecedor.setStyle(defultStyle);
       btnCadProduto.setStyle(defultStyle);
    }
    
   private void fornecActive(){
       ingHome.setImage(home);
       ingCliente.setImage(cliente);
       ingFornecedor.setImage(fornecedorRed);
       ingProduto.setImage(produto);
       btnHome.setStyle(defultStyle);
       btnCadCliente.setStyle(defultStyle);
       btnCadFornecedor.setStyle(activeStyle);
       btnCadProduto.setStyle(defultStyle);
    } 
    
   private void produtoActive(){
       ingHome.setImage(home);
       ingCliente.setImage(cliente);
       ingFornecedor.setImage(fornecedor);
       ingProduto.setImage(produtoRed);
       btnHome.setStyle(defultStyle);
       btnCadCliente.setStyle(defultStyle);
       btnCadFornecedor.setStyle(defultStyle);
       btnCadProduto.setStyle(activeStyle);
    } 
   
}
