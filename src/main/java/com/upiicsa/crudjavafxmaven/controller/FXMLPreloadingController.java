/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upiicsa.crudjavafxmaven.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Brian54
 */
public class FXMLPreloadingController implements Initializable {
    
    @FXML
    private ProgressBar progressBar;
    @FXML
    private AnchorPane pane; 
    /**
     * Initializes the controller class.
     */
    class pb_Thread implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    progressBar.setProgress(i/100.0);
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FXMLPreloadingController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }     
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        splash();
    }  
    
    private void splash(){
        new Thread(){
            public void run(){
                try {
                    Thread th = new Thread(new pb_Thread());
                    th.start();
                    Thread.sleep(10000);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            Stage stage = new Stage();
                            Parent root = FXMLLoader.load(getClass().getResource("/com/upiicsa/crudjavafxmaven/FXMLLogin.fxml"));
        
                            Scene scene = new Scene(root);
                            stage.initStyle(StageStyle.UNDECORATED);
                            stage.setScene(scene);
                            stage.show();
                            //pane.getScene().getWindow().hide();
                            Stage stag = (Stage) pane.getScene().getWindow();
                            stag.close();
                        } catch (IOException e) {
                            System.out.println(e.toString());
                        }
                    }                   
                });
            }
        }.start();
    }   
}