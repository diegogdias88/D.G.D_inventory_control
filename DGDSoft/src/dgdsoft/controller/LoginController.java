package dgdsoft.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dgdsoft.model.dao.UsuarioDAO;
import dgdsoft.model.database.Database;
import dgdsoft.model.database.DatabaseFactory;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class LoginController implements Initializable {

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
}
