package dgdsoft;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
     @author diego
 */
public class DGDSoft extends Application {
    
       
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("view/MainDGD.fxml"));
        
        Scene scene = new Scene(root);
        //stage.setMaximized(true);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        stage.getIcons().add(new Image (getClass().getResourceAsStream("/dgdsoft/imagem/balance.png")));
    }

    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
