/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upiicsa.crudjavafxmaven.model;

import com.upiicsa.crudjavafxmaven.utilerias.Propiedades;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javafx.scene.control.Alert;

/**
 *
 * @author Brian54
 */
public class Conexion {
    private static Conexion instancia = null;
    Propiedades propiedades = new Propiedades();
    Alert pantallaInformacion = new Alert(Alert.AlertType.INFORMATION);
    Connection conexion = null;

    private Conexion() {
    }
 
    public Connection Conectar() throws SQLException{
        try {     
            Properties properties = propiedades.cargarArchivo();
            String user = properties.getProperty("conexion.user");
            String pass = properties.getProperty("conexion.password");
            
            String url = "jdbc:sqlserver://servidor-54.database.windows.net:1433;database=demoSQL;user=" + user + "@servidor-54;password=" + pass + ";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            conexion=DriverManager.getConnection(url);
            
            mostrarVentanaInformativa("Infomación", "Conexion Establecida");            
        } catch (SQLException e) {
            System.out.println(e.toString());
            System.out.println("Error de conexion");
            mostrarVentanaInformativa("Infomación", e.toString());
        } catch (IOException e){
            System.out.println(e.toString());
        } finally {
            conexion.close();
        }
        return conexion;
    }
    
    public static Conexion getInstance(){
        if(instancia == null){
            instancia = new Conexion();
        } 
        return instancia;
    }
    
    public void mostrarVentanaInformativa(String titulo,String mensaje){
        pantallaInformacion.setTitle(titulo);
        pantallaInformacion.setHeaderText(null);
        pantallaInformacion.setContentText(mensaje);
        pantallaInformacion.showAndWait();
    }
}