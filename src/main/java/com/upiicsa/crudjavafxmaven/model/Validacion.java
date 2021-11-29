/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upiicsa.crudjavafxmaven.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Brian54
 */
public class Validacion {

    public Validacion() {
    }
    
    public boolean validarEmail(String email){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}