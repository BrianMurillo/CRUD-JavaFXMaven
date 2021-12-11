/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upiicsa.crudjavafxmaven.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;

/**
 *
 * @author Brian54
 */
public class Validacion {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public Validacion() {
    }
    
    public boolean validarEmail(String email){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
    
    public boolean validarNombre(String text){
        return text.matches("^([A-Z]{1}[a-z]+[ ]?){1,2}$");
    }  
    
    public boolean validarApellido(String text){
        return text.matches("^([A-Z]{1}[a-z]{3,15})$");
    }  
    
    public boolean validarEdad(String text){
        return text.matches("^([1-9]{1}[0-9]?{1}[0-9]?{1})$");
    }
    
    public boolean validarTelefono(String text){
        if(text.length() == 10){
            return true;
        } else {
            return false;
        }
    }
    
    public void mostrarInformacion(String titulo,String encabezado,String mensaje){
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    public boolean checkNumeric(String value) {
        String number = value.replaceAll("\\s+", "");
        for (int j = 0; j < number.length(); j++) {
            if (!(((int) number.charAt(j) >= 47 && (int) number.charAt(j) <= 57))) {
                return false;
            }
        }
        return true;
    }
}