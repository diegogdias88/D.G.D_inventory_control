package dgdsoft.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
