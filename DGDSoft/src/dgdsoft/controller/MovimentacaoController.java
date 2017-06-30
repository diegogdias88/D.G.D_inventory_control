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
public class MovimentacaoController implements Initializable {

    @FXML   private AnchorPane anchorPane;
    @FXML   private Button btnHome;
    @FXML   private Button btnMovCompra;  
    @FXML   private Button btnMovVenda;
    @FXML   private ImageView imgExit;
    @FXML   private ImageView imgInfo;
    @FXML   private ImageView ingLoggout;
    @FXML   private ImageView ingHome;
    @FXML   private ImageView ingBuy;
    @FXML   private ImageView ingSell;
    
    
     String defultStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:none";

    String activeStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:#FF4E3C";

    Image home = new Image("/dgdsoft/imagem/home_1.png");
    Image homeRed = new Image("/dgdsoft/imagem/homeRed.png");
    Image buy = new Image("/dgdsoft/imagem/buy.png");
    Image buyRed = new Image("/dgdsoft/imagem/buyRed.png");
    Image sell = new Image("/dgdsoft/imagem/sell2.png");
    Image sellRed = new Image("/dgdsoft/imagem/sell2Red.png");
    
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
    public void handleBtnMovCompra(ActionEvent event) throws IOException{
        buyActive();
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/dgdsoft/view/MovCompra.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleBtnMovVenda(ActionEvent event) throws IOException{
        sellActive();
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/dgdsoft/view/MovVenda.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    private void homeActive(){
       ingHome.setImage(homeRed);
       ingBuy.setImage(buy);
       ingSell.setImage(sell);
       btnHome.setStyle(activeStyle);
       btnMovCompra.setStyle(defultStyle);
       btnMovVenda.setStyle(defultStyle);
    }
    
    private void buyActive(){
       ingHome.setImage(home);
       ingBuy.setImage(buyRed);
       ingSell.setImage(sell);
       btnHome.setStyle(defultStyle);
       btnMovCompra.setStyle(activeStyle);
       btnMovVenda.setStyle(defultStyle);
    }
    
    private void sellActive(){
       ingHome.setImage(home);
       ingBuy.setImage(buy);
       ingSell.setImage(sellRed);
       btnHome.setStyle(defultStyle);
       btnMovCompra.setStyle(defultStyle);
       btnMovVenda.setStyle(activeStyle);
    }
}
