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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
    private Button buttonMinimiza;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCerrarOnAction(ActionEvent event) {
    }

    @FXML
    private void btnMaximizaOnAction(ActionEvent event) {
    }

    @FXML
    private void buttonMinimizaOnAction(ActionEvent event) {
    }
    
    @FXML
    private void btnVerPassLoginOnAction(ActionEvent event) {
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
                Alert information = new Alert(Alert.AlertType.INFORMATION);
                information.setTitle("Información");
                //Para mostrar una ventana tradicional el parametro sera null del headertext
                information.setHeaderText("Ingrese un correo electronico");
                information.setContentText("Ejemplo:  bmurillo@gmail.com");
                information.showAndWait();
            }
        }   
    }
}