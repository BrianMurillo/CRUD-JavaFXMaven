/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upiicsa.crudjavafxmaven.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Brian54
 */
public class UserDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private User user = new User();
    private Conexion conexion = Conexion.getInstance();
    
    public boolean registrarUsuario(User user){
        String sql="INSERT INTO usuarios(nombre,apellido_paterno,apellido_materno,edad,telefono,email,password,palabra_secreta,rol) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            con = conexion.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellidoPaterno());
            ps.setString(3, user.getApellidoMaterno());
            ps.setInt(4, user.getEdad());
            ps.setString(5, user.getTelefono());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getPassword());
            ps.setString(8, user.getPalabraSecreta());
            ps.setString(9, "Empleado");
            ps.executeUpdate();
            System.out.println("Registro Exitoso");
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } catch (NullPointerException e){
            System.out.println(e.toString());
        } finally {
            try {               
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            } catch (NullPointerException e){
                System.out.println(e.toString());
            } 
        }
        return false;
    }
    
    public User iniciarSesion(String email){
        String sql="SELECT * FROM usuarios WHERE email=?";
        User user = new User();
        try {
            con = conexion.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (NullPointerException e){
            System.out.println(e.toString());
        } finally{
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            } catch (NullPointerException e){
                System.out.println(e.toString());
            }
        }
        return user;
    }
}
