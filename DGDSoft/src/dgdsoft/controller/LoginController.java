package dgdsoft.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dgdsoft.animation.FadeOutDownTransition;
import dgdsoft.animation.ShakeTransition;
import dgdsoft.model.dao.UsuarioDAO;
import dgdsoft.model.database.Database;
import dgdsoft.model.database.DatabaseFactory;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class LoginController implements Initializable {

    @FXML   private AnchorPane rootPane;
    @FXML   private JFXButton btnLogin;
    @FXML   private JFXButton btnCancelar;
    @FXML   private JFXTextField login;
    @FXML   private  JFXPasswordField password;
    
     //atributos para manipulação de banco de dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final UsuarioDAO usuarioDao = new UsuarioDAO();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDao.setConnection(connection);
    }    
    
    public void verificaLogin(){
        
    }
    
    @FXML
    void login(ActionEvent event) {
        
        login.setId(null);
        password.setId(null);
        
        if (login.getText().equals("admin") && password.getText().equals("admin")) {
            
             // Aplicando a animação de entrada
            new FadeOutDownTransition(rootPane)
                    .setOnFinish((e) -> {

                        ((Stage) login.getScene().getWindow()).close();
                    })
                    .setDelayTime(Duration.ZERO)
                    .setDuration(Duration.millis(300))
                    .play();

        } else {
            
             // Aplicando a animação de validação
            new ShakeTransition(rootPane)
                    .setDelayTime(Duration.ZERO)
                    .setDuration(Duration.millis(800))
                    .play();

            if (!login.getText().equals("admin")) {
                login.setId("text-field-validation-error");
            }

            if (!password.getText().equals("admin")) {
                password.setId("text-field-validation-error");
            }

        }
    }
    
}
