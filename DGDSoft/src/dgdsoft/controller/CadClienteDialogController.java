package dgdsoft.controller;

import java.net.URL;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import dgdsoft.model.dao.CidEstDao;
import dgdsoft.model.database.Database;
import dgdsoft.model.database.DatabaseFactory;
import dgdsoft.model.domain.Cliente;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class CadClienteDialogController implements Initializable {

    @FXML private TextField ClienteNome;
    @FXML private ComboBox boxSexo;
    @FXML private ComboBox boxNacionalidade;
    @FXML private ComboBox boxEstCivil;
    @FXML private TextField ClienteCPF;
    @FXML private TextField ClienteRG;
    @FXML private TextField ClienteNasc;
    @FXML private TextField ClienteEnd;
    @FXML private TextField ClienteNumero;
    @FXML private TextField ClienteComp;
    @FXML private TextField ClienteBairro;
    @FXML private ComboBox boxUF;
    @FXML private ComboBox boxCidade;
    @FXML private TextField ClienteTelefone;
    @FXML private TextField ClienteCelular;
    @FXML private TextField ClienteEmail;
    @FXML private Button btnConfirmar;
    @FXML private Button btnCancelar;
       
    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    private Cliente cliente;
    
    //OBJETOS UTILIZADOS
    private String uf;
    private String cidade;
    
    //atributos para manipulação de banco de dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final CidEstDao cidestDao = new CidEstDao();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cidestDao.setConnection(connection);
        
        carregarEstado();
        carregarEstCivil();
        carregaSexo();
        //CarregaPais();
        carregaCidades();
    }

    //CONVERTE UMA STRING EM FORMATO DATE
    public static java.sql.Date formataData(String data) throws Exception {
        if (data == null || data.equals("")) {
            return null;
        }
        java.sql.Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = new java.sql.Date(((java.util.Date) formatter.parse(data)).getTime());
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }
    //CARREGA Estado Civil na ComboBox
    public void carregarEstCivil(){
        boxEstCivil.getItems().add("Solteiro");
        boxEstCivil.getItems().add("Casado");
        boxEstCivil.getItems().add("Divorciado");
        boxEstCivil.getItems().add("Amaziado");
    }
    
    //carrega sexo na combobox
    public void carregaSexo(){
        boxSexo.getItems().add("Masculino");
        boxSexo.getItems().add("Feminino");
    }
    
    //Carregar paises na ComboBox
    public void CarregaPais(){
        List<String> listaPais = cidestDao.listaPais();
        listaPais.forEach((nomePais) -> {
            boxNacionalidade.getItems().add(nomePais);
         });
    }
    
    //CARREGA OS ESTADOS E OS ADD EM UMA COMBOBOX    
    public void carregarEstado(){
        List<String> listaEstado = cidestDao.listaEstados();
        listaEstado.forEach((nomeEstado) -> {
            boxUF.getItems().add(nomeEstado);
        });
    }
    /*
    private List<CidadeEstado> cidadePorEstado() throws Exception {
        CidEstController cc = new CidEstController();
        try 
        {    
            return cc.buscaCidadePorEstado (uf);
        }
        catch (SQLException e) {                } 
        return Collections.emptyList();
    }
    */

    //CARREGA TODAS AS CIDADES DE TODOS OS ESTADOS, NÃO É O MELHOR METODO, MAS O UNICO ENCONTRADO
    public void carregaCidades(){
        List<String> listaCidade = cidestDao.listaCidades();
        listaCidade.forEach((nomeCidade) -> {
            boxCidade.getItems().add(nomeCidade);
        });
    }
    
    //TORNA OS NOMES COMPLETOS EM SIGLAS
    private String EstadoNomeParaSigla(){
        String estado = null;        
        if("Acre".equals(boxUF.valueProperty().get()) ) estado = "AC";
        if("Alagoas".equals(boxUF.valueProperty().get()) ) estado = "AL";
        if("Amazonas".equals(boxUF.valueProperty().get()) ) estado = "AM";
        if("Amapá".equals(boxUF.valueProperty().get()) ) estado = "AP";
        if("Bahia".equals(boxUF.valueProperty().get()) ) estado = "BA";
        if("Ceará".equals(boxUF.valueProperty().get()) ) estado = "CE";
        if("Distrito Federal".equals(boxUF.valueProperty().get()) ) estado = "DF";
        if("Espírito Santo".equals(boxUF.valueProperty().get()) ) estado = "ES";
        if("Goiás".equals(boxUF.valueProperty().get()) ) estado = "GO";
        if("Maranhão".equals(boxUF.valueProperty().get()) ) estado = "MA";
        if("Minas Gerais".equals(boxUF.valueProperty().get()) ) estado = "MG";
        if("Mato Grosso do Sul".equals(boxUF.valueProperty().get()) ) estado = "MS";
        if("Mato Grosso".equals(boxUF.valueProperty().get()) ) estado = "MT";
        if("Pará".equals(boxUF.valueProperty().get()) ) estado = "PA";
        if("Paraíba".equals(boxUF.valueProperty().get()) ) estado = "PB";
        if("Pernambuco".equals(boxUF.valueProperty().get()) ) estado = "PE";
        if("Piauí".equals(boxUF.valueProperty().get()) ) estado = "PI";
        if("Paraná".equals(boxUF.valueProperty().get()) ) estado = "PR";
        if("Rio de Janeiro".equals(boxUF.valueProperty().get()) ) estado = "RJ";
        if("Rio Grande do Norte".equals(boxUF.valueProperty().get()) ) estado = "RN";
        if("Rondônia".equals(boxUF.valueProperty().get()) ) estado = "RO";
        if("Roraima".equals(boxUF.valueProperty().get()) ) estado = "RR";
        if("Rio Grande do Sul".equals(boxUF.valueProperty().get()) ) estado = "RS";
        if("Santa Catarina".equals(boxUF.valueProperty().get()) ) estado = "SC";
        if("Sergipe".equals(boxUF.valueProperty().get()) ) estado = "SE";
        if("São Paulo".equals(boxUF.valueProperty().get()) ) estado = "SP";
        if("Tocantins".equals(boxUF.valueProperty().get()) ) estado = "TO";
        return estado;
    }
    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }

    public void setBtnConfirmarClicked(boolean btnConfirmarClicked) {
        this.btnConfirmarClicked = btnConfirmarClicked;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.ClienteNome.setText(cliente.getNome());
        this.ClienteCPF.setText(cliente.getCpf());
        this.ClienteRG.setText(cliente.getRg());
        this.ClienteEnd.setText(cliente.getEnd());
        this.boxCidade.valueProperty().set(String.valueOf(cliente.getCidade()));
        this.ClienteBairro.setText(cliente.getBairro());
        this.boxUF.valueProperty().set(String.valueOf(cliente.getUf()));
        this.ClienteTelefone.setText(cliente.getTelefone());
        this.boxSexo.valueProperty().set(String.valueOf(cliente.getSexo()));
        this.boxNacionalidade.valueProperty().set(String.valueOf(cliente.getNacionalidade()));
        this.boxEstCivil.valueProperty().set(String.valueOf(cliente.getEstCivil()));
        this.ClienteNumero.setText(String.valueOf(cliente.getNumeroEnd()));
        //IMAGEM
        //CEP Ver se vai utilizar
        //this.ClienteNasc.setText(String.valueOf(cliente.getNasc()));
    }
       
    @FXML
    public void handleBtnConfirmar() throws Exception{
        cliente.setNome(ClienteNome.getText());
        //cliente.setCpf(ClienteCPF.getText());
        if(ClienteCPF.getText() == null){cliente.setCpf("0");}else{cliente.setCpf(ClienteCPF.getText());}
        cliente.setRg(ClienteRG.getText());
        cliente.setEnd(ClienteEnd.getText());
        cliente.setCidade(String.valueOf(boxCidade.valueProperty().get()));
        cliente.setBairro(ClienteBairro.getText());
        cliente.setUf(String.valueOf(boxUF.valueProperty().get()));
        cliente.setTelefone(ClienteTelefone.getText());
        //IMAGEM
        //CEP Ver se vai utilizar
        //cliente.setNasc(formataData(ClienteNasc.getText()));
        cliente.setNacionalidade(String.valueOf(boxNacionalidade.valueProperty().get()));
        cliente.setEstCivil(String.valueOf(boxEstCivil.valueProperty().get()));
        cliente.setSexo(String.valueOf(boxSexo.valueProperty().get()));
        //cliente.setNumeroEnd(Integer.parseInt(ClienteNumero.getText()));      //não tem numero no banco de dados
        
        btnConfirmarClicked = true;
        dialogStage.close();
    }
       
    @FXML
    private void handleBtnCancelar(ActionEvent event){
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Aviso!");//titulo
	alert.setHeaderText("Você esta prestes a fechar esta tela.");//exto do corpo do texto
	alert.setContentText("Deseja realmente sair?");//texto  em baixo
	
	ButtonType buttonSim = new ButtonType("Sim");//1 bot~]ao opção
	ButtonType buttonNao = new ButtonType("Não");//2 bot~]ao opção
	
	alert.getButtonTypes().setAll(buttonSim,buttonNao); //coloca os botões no alerta
	
	Optional<ButtonType> result = alert.showAndWait();//cria um result onde é para esperar clicar em uma tela para executar uma função
	if(result.get() ==buttonSim){
		dialogStage.close();
	}else if(result.get() == buttonNao){
		alert.close();
	}
    }
    
}
