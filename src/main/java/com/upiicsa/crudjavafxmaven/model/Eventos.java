/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upiicsa.crudjavafxmaven.model;
//Libreria de java AWT
import java.awt.event.KeyEvent;
import javafx.scene.control.TextField;

/**
 *
 * @author Brian54
 */
public class Eventos {
    //Solo permitimos caracteres
    public void keyTextPressed(KeyEvent event){
        //Se declara una variable char y le asignamos un evento
        //Car=caracter
        char car = event.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car != (char) KeyEvent.VK_BACK_SPACE) && (car != (char) KeyEvent.VK_SPACE)) {
            event.consume();
        }
    }
    //Solo se permiten numeros
    public void numberKeyPress(KeyEvent event) {
        // declaramos una variable y le asignamos un evento
        char car = event.getKeyChar();
        if ((car < '0' || car > '9') && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            event.consume();
        }
    }
    //Solo se permite decimales
    public void numbreDecimalKeyPressed(KeyEvent event, TextField textField){
        // declaramos una variable y le asignamos un evento
        char car = event.getKeyChar();
        if ((car < '0' || car > '9') && textField.getText().contains(".") && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            event.consume();
        } else if ((car < '0' || car > '9') && (car != '.') && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            event.consume();
        }       
    }
}