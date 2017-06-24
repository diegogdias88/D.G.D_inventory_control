package dgdsoft;

import com.sun.javafx.scene.control.behavior.ButtonBehavior;
import com.sun.javafx.scene.control.behavior.KeyBinding;
import dgdsoft.animation.FadeOutDownTransition;
import dgdsoft.animation.ShakeTransition;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import static javafx.scene.input.KeyEvent.KEY_RELEASED;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
     @author diego
 */
public class DGDSoft extends Application {
    
       
    @Override
    public void start(Stage stage) throws IOException {      
        Parent root = FXMLLoader.load(getClass().getResource("view/MainDGD.fxml"));       
        Scene scene = new Scene(root);
        
        //stage.setMaximized(true); // define que a tela inicie maximizada
        //stage.setTitle("Aesome UI FX!"); //define um texto para o stage
        //root.setEffect(new BoxBlur()); // Aplicando o efeito de desfoque na principal
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT); //some com a borda de tela do windows
        stage.show();
        stage.getIcons().add(new Image (getClass().getResourceAsStream("/dgdsoft/imagem/balance.png")));
        
    }

    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
