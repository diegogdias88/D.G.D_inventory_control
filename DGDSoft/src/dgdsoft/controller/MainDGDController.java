package dgdsoft.controller;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class MainDGDController implements Initializable {

    @FXML    private Group groupPlanes;
    @FXML    private HBox menusHolder;
    @FXML    private AnchorPane parentContainer;
    @FXML    private Button Cadastro;
    @FXML    private Button Movimentacao;
    @FXML    private Button Relatorio;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void exit(javafx.scene.input.MouseEvent event) {
        Platform.exit();
    }
    
    @FXML
    private void openCadastro() {
        openStage("/dgdsoft/view/Cadastro.fxml");
    }
    
    private void openStage(String fxml) {
        try {
            Stage currentStage = (Stage) Cadastro.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Scene scene = new Scene(root);
            Stage stage = new Stage(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            currentStage.hide();
            stage.getIcons().add(new Image (getClass().getResourceAsStream("/dgdsoft/imagem/balance.png")));

        } catch (IOException ex) {
            Logger.getLogger(MainDGDController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
