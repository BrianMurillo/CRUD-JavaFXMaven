/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upiicsa.crudjavafxmaven.controller;

import com.upiicsa.crudjavafxmaven.model.Conexion;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
                information.setTitle("Información");
                //Para mostrar una ventana tradicional el parametro sera null del headertext
                information.setHeaderText("Ingrese un correo electronico");
                information.setContentText("Ejemplo:  bmurillo@gmail.com");
                information.showAndWait();
            }
        }   
    } 

    @FXML
    private void btnLoginOnAction(ActionEvent event) {
        try {
            instanciaSQL.Conectar();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } catch (NullPointerException e){
            System.out.println(e.toString());
        }
    }
}