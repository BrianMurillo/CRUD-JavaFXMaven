/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upiicsa.crudjavafxmaven.controller;

import com.upiicsa.crudjavafxmaven.model.User;
import com.upiicsa.crudjavafxmaven.model.UserDAO;
import com.upiicsa.crudjavafxmaven.model.Validacion;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private User user = new User();
    private UserDAO userDAO;
    //Scene Login
    FXMLLoginController fxmlLoginController;
    //Scene Register
    FXMLRegisterController fxmlRegisterController;
    Validacion validacion = new Validacion();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.userDAO = new UserDAO();
        fxmlRegisterController = this;
    }    

    @FXML
    private void btnMinimizaOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnMinimiza.getScene().getWindow();
            stage.setIconified(true);
    }

    @FXML
    private void btnMaximizaOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnMaximiza.getScene().getWindow();
        if (contador % 2 == 0) {
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
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void btnVerContraseñaConfirmarOnAction(ActionEvent event) {
        if (verPass2 % 2 == 0) {
            txtConfirmarPassword.setVisible(false);
            txtVerPasswordConfirm.setVisible(true);
            txtVerPasswordConfirm.setText(txtConfirmarPassword.getText());
        } else {
            txtVerPasswordConfirm.setVisible(false);
            txtConfirmarPassword.setVisible(true);
            txtConfirmarPassword.setText(txtVerPasswordConfirm.getText());
        }
        verPass2++;
    }

    @FXML
    private void btnRegisterOnAction(ActionEvent event) {
        registrarUsuario();
    }

    public void registrarUsuario() {
        boolean resultadoRegistro;
        if (!"".equals(txtNombre.getText()) && !"".equals(txtAPaterno.getText()) && !"".equals(txtAMaterno.getText()) && !"".equals(txtEdad.getText()) && !"".equals(txtEmail.getText()) && !"".equals(txtPassword.getText()) && !"".equals(txtPalabraSecreta.getText())) {
            if (txtPassword.getText().equals(txtConfirmarPassword.getText())) {
                user.setNombre(txtNombre.getText());
                user.setApellidoPaterno(txtAPaterno.getText());
                user.setApellidoMaterno(txtAMaterno.getText());
                user.setEdad(Integer.parseInt(txtEdad.getText()));
                user.setTelefono(txtTelefono.getText());
                if (validacion.validarEmail(txtEmail.getText())) {
                    user.setEmail(txtEmail.getText());
                    //Hasheamos el pass con Argon2
                    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
                    if (verPass % 2 == 0) {
                        user.setPassword(txtPassword.getText());
                        String hash = argon2.hash(1, 1024, 1, user.getPassword());
                        user.setPassword(hash);
                    } else {
                        user.setPassword(txtVerPassword.getText());
                        String hash = argon2.hash(1, 1024, 1, user.getPassword());
                        user.setPassword(hash);
                    }
                    user.setPalabraSecreta(txtPalabraSecreta.getText());
                    resultadoRegistro = userDAO.registrarUsuario(user);
                    if (resultadoRegistro) {
                        mostrarInformacion("Infomación", "Registro Exitoso");
                        abrirSceneLogin();
                    } else {
                        mostrarInformacion("Información", "Error al registrar usuario");
                    }
                } else {
                    errorValidacionCorreo();
                }
            } else {
                mostrarInformacion("Información", "Las dos contraseñas deben ser iguales");
            }
        } else {
            mostrarInformacion("Información", "Ingresar datos para completar el registro");
        }
    }

    @FXML
    private void btnBackOnAction(ActionEvent event) {
        abrirSceneLogin();
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
    
    public void mostrarInformacion(String titulo, String mensaje) {
        information.setTitle(titulo);
        //Para mostrar una ventana tradicional el parametro sera null del headertext
        information.setHeaderText(null);
        information.setContentText(mensaje);
        information.showAndWait();
    }
    
    public void recibirDatos(String email,String pass){
         txtEmail.setText(email);
         txtPassword.setText(pass);
         txtConfirmarPassword.setText(pass);
    }
    
    public void abrirSceneLogin(){
        try {
            Stage stage2 = new Stage();
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane)loader.load(getClass().getResource("/com/upiicsa/crudjavafxmaven/FXMLLogin.fxml").openStream());
            fxmlLoginController = (FXMLLoginController)loader.getController();
            fxmlLoginController.recibirDatos(txtEmail.getText(), txtPassword.getText());
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/com/upiicsa/crudjavafxmaven/css/style.css").toExternalForm());
            stage2.setScene(scene);
            stage2.alwaysOnTopProperty();
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.show();
            
            Stage stag = (Stage) btnBack.getScene().getWindow();
            stag.close();
        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @FXML
    private void txtNombreOnKeyPressed(KeyEvent event) {      
        if(event.getCode() == KeyCode.ENTER){
            if(!"".equals(txtNombre.getText())){               
                txtAPaterno.requestFocus();
            } else {
                mostrarInformacion("Información", "Ingresar Nombre");
            }
        }  
    }

    @FXML
    private void txtAPaternoOnKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            if(!"".equals(txtAPaterno.getText())){
                txtAMaterno.requestFocus();
            } else {
                mostrarInformacion("Infomación", "Ingresar Apellido Paterno");
            }
        } 
    }

    @FXML
    private void txtAMaternoOnKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            if(!"".equals(txtAMaterno.getText())){
                txtEdad.requestFocus();
            } else {
                mostrarInformacion("Información", "Ingresar Apellido Materno");
            }
        } 
    }

    @FXML
    private void txtEdadOnKeypressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            if(!"".equals(txtEdad.getText())){
                txtTelefono.requestFocus();
            } else {
                mostrarInformacion("Información", "Ingresar Edad");
            }
        } 
    }

    @FXML
    private void txtTelefonoOnKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            txtEmail.requestFocus();
        }
    }

    @FXML
    private void txtEmailOnKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            if(!"".equals(txtEmail.getText())){
                if(validacion.validarEmail(txtEmail.getText())){
                    txtPassword.requestFocus();
                } else {
                    errorValidacionCorreo();
                }
            } else {
                mostrarInformacion("Información", "Ingresar Email");
            }
        } 
    }

    @FXML
    private void txtPassAOnKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            if(!"".equals(txtPassword.getText())){
                txtConfirmarPassword.requestFocus();
            } else {
                mostrarInformacion("Información", "Ingresar Password");
            }
        }
    }

    @FXML
    private void txtPassBOnKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            if(!"".equals(txtConfirmarPassword.getText())){
                txtPalabraSecreta.requestFocus();
            } else {
                mostrarInformacion("Información", "Ingresar Password");
            }
        }
    }

    @FXML
    private void txtPalabraSecretaOnKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            if(!"".equals(txtPalabraSecreta.getText())){
                registrarUsuario();
            } else {
                mostrarInformacion("Información", "Ingresar una palabra clave, la cual sera necesaria para cuando requiera cambiar contraseña");
            }
        }
    }
    
    private void errorValidacionCorreo() {
        validacion.mostrarInformacion("Información", "Correo electronico invalido", "Ejemplo: muso@gmail.com");
        txtEmail.requestFocus();
    }
}