/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upiicsa.crudjavafxmaven.controller;

import com.upiicsa.crudjavafxmaven.model.Conexion;
import com.upiicsa.crudjavafxmaven.model.Validacion;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Brian54
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private Button btnCerrar;
    @FXML
    private Button btnMaximiza;
    @FXML
    private TextField txtEmailLogin;
    @FXML
    private PasswordField txtPasswordLogin;
    @FXML
    private Button btnVerPassLogin;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnRegisterLogin;
    @FXML
    private Label lblOlvidastePassword;
    Alert information = new Alert(Alert.AlertType.INFORMATION);
    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
    @FXML
    private Button btnMinimiza;
    private int contador=2;
    private int verPass=2;
    @FXML
    private TextField txtVerPassword;
    Conexion instanciaSQL = Conexion.getInstance();
    FXMLLoginController fxmlLoginController;
    FXMLRegisterController fxmlRegisterController;
    Validacion validacion = new Validacion();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fxmlLoginController = this;
    }    

    @FXML
    private void btnCerrarOnAction(ActionEvent event) {
        alerta.setTitle("Confirmación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Desea cerrar la aplicación?");
        
        Optional<ButtonType> result = alerta.showAndWait();
        if(result.get() == ButtonType.OK){
            Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void btnMaximizaOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnMaximiza.getScene().getWindow();
        if(contador%2 == 0){
            stage.setMaximized(true);
        } else {
            stage.setMaximized(false);
        }
        contador++;
    }

    @FXML
    private void btnMinimizaOnAction(ActionEvent event) {
            Stage stage = (Stage) this.btnMinimiza.getScene().getWindow();
            stage.setIconified(true);
    }
 
    @FXML
    private void btnVerPassLoginOnAction(ActionEvent event) {
        if(verPass%2 == 0){
            txtPasswordLogin.setVisible(false);
            txtVerPassword.setVisible(true);
            txtVerPassword.setText(txtPasswordLogin.getText());
        } else {
            txtVerPassword.setVisible(false);
            txtPasswordLogin.setVisible(true);
            txtPasswordLogin.setText(txtVerPassword.getText());
        }
        verPass++;
    }

    @FXML
    private void btnRegisterLoginOnAction(ActionEvent event) {
        try {
            Stage stage2 = new Stage();
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane)loader.load(getClass().getResource("/com/upiicsa/crudjavafxmaven/FXMLRegister.fxml").openStream());
            fxmlRegisterController = (FXMLRegisterController)loader.getController();
            fxmlRegisterController.recibirDatos(txtEmailLogin.getText(), txtPasswordLogin.getText());
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/com/upiicsa/crudjavafxmaven/css/style.css").toExternalForm());
            stage2.setScene(scene);
            stage2.alwaysOnTopProperty();
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.show();
            
            Stage stag = (Stage) btnRegisterLogin.getScene().getWindow();
            stag.close();
        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    @FXML
    private void lblOlvidastePasswordOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void txtLoginOnKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            if(!"".equals(txtEmailLogin.getText())){
                txtPasswordLogin.requestFocus();
            } else {
                mostrarInformacion("Información", "Ingresar Email");
            }
        }   
    } 

    @FXML
    private void btnLoginOnAction(ActionEvent event) {
        iniciarSesion();
    }
    
    public void iniciarSesion(){
        try {
            if (!"".equals(txtEmailLogin.getText()) && !"".equals(txtPasswordLogin.getText())) {
                if(validacion.validarEmail(txtEmailLogin.getText())){
                    instanciaSQL.Conectar();
                } else {
                    mostrarInformacion("Información", "Ingrese un correo electronico valido");
                    txtEmailLogin.requestFocus();
                }
            } else {
                mostrarInformacion("Información", "Ingresar Usuario y Password");
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void mostrarInformacion(String titulo, String mensaje) {
        information.setTitle(titulo);
        //Para mostrar una ventana tradicional el parametro sera null del headertext
        information.setHeaderText(null);
        information.setContentText(mensaje);
        information.showAndWait();
    }
    
    public void recibirDatos(String email,String pass){
        txtEmailLogin.setText(email);
        txtPasswordLogin.setText(pass);
    }

    @FXML
    private void txtPassOnKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            if(!"".equals(txtPasswordLogin.getText())){
                iniciarSesion();
            } else {
                mostrarInformacion("Información", "Ingresar Password");
            }
        }
    }
}