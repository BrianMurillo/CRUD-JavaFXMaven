/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upiicsa.crudjavafxmaven.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    private Button btnVerContraseña;
    @FXML
    private Button btnVerContraseñaConfirmar;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnMinimizaOnAction(ActionEvent event) {
    }

    @FXML
    private void btnMaximizaOnAction(ActionEvent event) {
    }

    @FXML
    private void btnCerrarOnAction(ActionEvent event) {
    }

    @FXML
    private void btnVerContraseñaOnAction(ActionEvent event) {
    }

    @FXML
    private void btnVerContraseñaConfirmarOnAction(ActionEvent event) {
    }

    @FXML
    private void btnRegisterOnAction(ActionEvent event) {
    }

    @FXML
    private void btnBackOnAction(ActionEvent event) {
    }   
}