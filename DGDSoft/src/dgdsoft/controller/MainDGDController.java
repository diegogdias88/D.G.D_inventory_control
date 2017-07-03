package dgdsoft.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dgdsoft.model.dao.UsuarioDAO;
import dgdsoft.model.database.Database;
import dgdsoft.model.database.DatabaseFactory;
import dgdsoft.model.domain.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
    @FXML   private JFXButton btnLogin;
    @FXML   private ImageView imgExit;
    @FXML   private JFXTextField login;
    @FXML   private JFXPasswordField password;

    @FXML
    private Usuario listUsuario;
    private ObservableList<Usuario> observableListUsuario;
    
     //atributos para manipulação de banco de dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final UsuarioDAO userDao = new UsuarioDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userDao.setConnection(connection);
        
    }    
    
    @FXML
    private void acess () throws IOException{
        if(userDao.buscar(login.getText(), password.getText())){
            System.out.println("Login");
            Stage newStage = new Stage();
            newStage.initStyle(StageStyle.TRANSPARENT);
            Parent parent = FXMLLoader.load(getClass().getResource("/dgdsoft/view/MainDGDPrincipal.fxml"));
            Scene scene = new Scene(parent);
            newStage.setScene(scene);
            newStage.show();
            newStage.getIcons().add(new Image (getClass().getResourceAsStream("/dgdsoft/imagem/logo.png")));
            btnLogin.getScene().getWindow().hide();           
        }else{
            
            System.out.println("Sem Login");
        }
        
    }
    
    @FXML
    private void exit(javafx.scene.input.MouseEvent event) {
        Platform.exit();
    } 
}
