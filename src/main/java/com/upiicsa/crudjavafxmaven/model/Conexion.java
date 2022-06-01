/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upiicsa.crudjavafxmaven.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author Brian54
 */
public class Conexion {
    private static Conexion instancia = null;
    Alert pantallaInformacion = new Alert(Alert.AlertType.INFORMATION);
    Connection conexion = null;
    private String user = "server-54-admin";
    private String pass = "musovespo54$";
    private String url ="jdbc:sqlserver://sql-server54.database.windows.net:1433;database=sql-server;user="+user+"@sql-server54;password="+pass+";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    
    private Conexion() {
    }
 
    public Connection Conectar() throws SQLException{
        try {              
            conexion=DriverManager.getConnection(url);
            mostrarVentanaInformativa("Infomación", "Conexion Establecida");            
        } catch (SQLException e) {
            System.out.println(e.toString());
            System.out.println("Error de conexion");
            mostrarVentanaInformativa("Infomación", e.toString());
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