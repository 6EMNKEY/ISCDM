package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alumne
 */
public class Usuario {
    
    
    
    private static final String JDBC_URL = "jdbc:derby://localhost:1527/ISDCM";
    private static final String USER = "miquel";
    private static final String PASSWORD = "miquel";
    private static final String TABLE = "usuarios";
    
    
    
    private int id;
    
    private String nombre;
    private String apellidos;
    private String email;
    
    private String nombreUsuario;
    private String password;
    
    
    public Usuario(){
        this.id=-1;
        this.nombre=null;
        this.apellidos = null;
        this.email = null;
        this.nombreUsuario = null;
        this.password =null;
    }
    
    public Usuario(String nombre, String apellidos, String email, String nombreUsuario, String password){
        
        System.out.println("Cargando nuevo usuario: " + nombre + " - " + apellidos);
        
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        
        //Boolean created = crearUsuario();
        
    }
    
    
      public Usuario(String nombreUsuario, String password){
        this.nombre = null;
        this.apellidos = null;
        this.email = null;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }
    
    
    public boolean crearUsuario(){
        boolean result = false;
        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            
            String sql = "INSERT INTO " + TABLE
                    + "( nombre, apellido, correo, username, password)"
                   + " VALUES ('" + this.nombre + "', '" + this.apellidos + "', '" + this.email + "', '" + this.nombreUsuario + "', '" + this.password + "')";
            System.out.println("Sentencia SQL: " + sql);
            stmt.executeUpdate(sql);
            
            result = true;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return result;
    }
    
    public boolean existeUsuario(){
        boolean result = false;
        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT COUNT(*) as COUNT FROM " + TABLE + " WHERE username='" + this.nombreUsuario + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                result = (rs.getInt("COUNT") > 0);
            }
            
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return result;
        
    }
    
    public boolean correctLogin(){
        boolean result = false;
        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT COUNT(*) as COUNT FROM " + TABLE + " WHERE username='" + this.nombreUsuario + "' AND password='" + this.password+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                result = (rs.getInt("COUNT") > 0);
            }
            
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return result;
        
    }
    
    public int getId(){
        int aux = 0;
        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT * FROM " + TABLE + " WHERE username='" + this.nombreUsuario + "'";
            System.out.println("Sentencia SQL: " + sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                aux = rs.getInt("ID");
                System.out.println(aux);
                                
                
            }            
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return aux;
    }
    
 
    
}
