/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgdsoft.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class MainController implements Initializable {

    //@FXML private Button btnCadastro;
    @FXML private Button btnMovimentacao;
    @FXML private Button btnRelatorio;
     
    //Image imageCad = new Image("imagem/cad.png");
    //ImageView cad = new ImageView(imageCad);
    Button btnCadastro = new Button();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         btnCadastro.setStyle("-fx-background-image: url('/dgdsoft/imagem/cad.png')");
    }    
    
}
