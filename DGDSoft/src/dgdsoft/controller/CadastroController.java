package dgdsoft.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
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
    @FXML   private ImageView imgExit;
    @FXML   private ImageView imgInfo;
           
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
        Parent parent = FXMLLoader.load(getClass().getResource("/dgdsoft/view/MainDGD.fxml"));
        Scene scene = new Scene(parent);
        newStage.setScene(scene);
        newStage.show();
        imgInfo.getScene().getWindow().hide();

    }
    
    @FXML
    public void handleBtnCadastrosCliente() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/dgdsoft/view/CadCliente.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleBtndastrosFornecedor() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/dgdsoft/view/CadFornecedor.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleBtnCadastrosProduto() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/dgdsoft/view/CadProduto.fxml"));
        anchorPane.getChildren().setAll(a);
    }
        
}
