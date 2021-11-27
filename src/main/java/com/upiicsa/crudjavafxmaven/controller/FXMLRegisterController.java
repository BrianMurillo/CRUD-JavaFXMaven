/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upiicsa.crudjavafxmaven.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Brian54
 */
public class FXMLRegisterController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtAPaterno;
    @FXML
    private TextField txtAMaterno;
    @FXML
    private TextField txtEdad;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtConfirmarPassword;
    @FXML
    private TextField txtPalabraSecreta;
    @FXML
    private Button btnMinimiza;
    @FXML
    private Button btnMaximiza;
    @FXML
    private Button btnCerrar;
    @FXML
    private Button btnVerContraseñaConfirmar;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnBack;
    Alert information = new Alert(Alert.AlertType.INFORMATION);
    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
    private int contador=2;
    private int verPass=2;
    private int verPass2=2;
    @FXML
    private Button btnVerContraseña;
    @FXML
    private TextField txtVerPassword;
    @FXML
    private TextField txtVerPasswordConfirm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnMinimizaOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnMinimiza.getScene().getWindow();
            stage.setIconified(true);
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
    private void btnVerContraseñaConfirmarOnAction(ActionEvent event) {
        if(verPass2%2 == 0){
            txtConfirmarPassword.setVisible(false);
            txtVerPasswordConfirm.setVisible(true);
            txtVerPasswordConfirm.setText(txtConfirmarPassword.getText());
        } else {
            txtVerPasswordConfirm.setVisible(false);
            txtConfirmarPassword.setVisible(true);
            txtConfirmarPassword.setText(txtVerPassword.getText());
        }
        verPass2++;
    }

    @FXML
    private void btnRegisterOnAction(ActionEvent event) {
    }

    @FXML
    private void btnBackOnAction(ActionEvent event) {
    }   

    @FXML
    private void btnVerContraseñaOnAction(ActionEvent event) {
        if(verPass%2 == 0){
            txtPassword.setVisible(false);
            txtVerPassword.setVisible(true);
            txtVerPassword.setText(txtPassword.getText());
        } else {
            txtVerPassword.setVisible(false);
            txtPassword.setVisible(true);
            txtPassword.setText(txtVerPassword.getText());
        }
        verPass++;
    }
}