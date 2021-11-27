/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upiicsa.crudjavafxmaven.utilerias;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Brian54
 */
public class Propiedades {
    
    public Properties cargarArchivo() throws FileNotFoundException, IOException{
        Properties propiedades = new Properties();
        //Leeremos el archivo
        InputStream archivo = new FileInputStream("src\\main\\java\\config.properties");
        propiedades.load(archivo);
        return propiedades;
    }
    
}
