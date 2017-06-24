package dgdsoft.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dgdsoft.model.dao.UsuarioDAO;
import dgdsoft.model.database.Database;
import dgdsoft.model.database.DatabaseFactory;
import dgdsoft.model.domain.Cliente;
import dgdsoft.model.domain.Usuario;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    
    @FXML   private JFXButton Cadastro;
    @FXML   private JFXButton Movimentacao;
    @FXML   private JFXButton Relatorio;
    
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
    private void acess (){
        Usuario usuario = new Usuario();
        listUsuario = userDao.buscar(usuario);
        
        usuario.setUser(login.getText());
        usuario.setPassword(password.getText());     
        
        observableListUsuario = FXCollections.observableArrayList(listUsuario);
        
        if(login.getText().equals(usuario.getNome()) && password.getText().equals(usuario.getPassword()) ){
            System.out.println("Deu Certo");
            System.out.println(observableListUsuario.toString());
        }else{
             System.out.println("Deu Errado");
             System.out.println(observableListUsuario.toString());
        }
        
    }
    
    @FXML
    private void exit(javafx.scene.input.MouseEvent event) {
        Platform.exit();
    }
    
    @FXML
    private void openCadastro() {
        openStage("/dgdsoft/view/Cadastro.fxml");
    }
    
    @FXML
    private void openMovimentacao() {
        openStage("/dgdsoft/view/Movimentacao.fxml");
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
