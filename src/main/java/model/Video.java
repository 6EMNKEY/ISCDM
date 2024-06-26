/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 *
 * @author alumne
 */
public class Video {
    
    
    
    private static final String JDBC_URL = "jdbc:derby://localhost:1527/ISDCM";
    private static final String USER = "miquel";
    private static final String PASSWORD = "miquel";
    private static final String TABLE = "videos";
    
    
    
    private int id;
    
    private int autorID;
    private String titulo;
    private String autor;
    private Date fecha;
    private Time duracion;
    
    private int reproducciones;
    private String descripcion;
    private String formato;
    
    
    private String URL;
    private String URLINFO;
    private int STREAM;
    
    public Video(){
        this.id = -1;
        this.autorID = -1;
        this.titulo = null;
        this.autor = null;
        this.fecha = null;
        this.duracion = null;
        this.reproducciones = 0;
        this.descripcion = null;
        this.formato = null;
        this.URL = null;
        this.URLINFO = null;
        this.STREAM = 0;
    }  
    
    public Video(int id){
        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM " + TABLE + " WHERE ID=" + id;
            System.out.println("Sentencia SQL: " + sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                // Si se encontró un video con el ID dado, crear un objeto Video con los datos correspondientes
                this.id = rs.getInt("ID");
                this.titulo = rs.getString("titulo");
                this.autor = rs.getString("autor");
                this.fecha = rs.getDate("fecha_creacion");
                this.duracion = rs.getTime("duracion");
                this.descripcion = rs.getString("descripcion");
                this.formato = rs.getString("formato");
                this.URL = rs.getString("url");
                this.reproducciones = rs.getInt("reproducciones");
            }            
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        System.out.println("URL:" + this.URL);
    }

    
    public Video(int autorId, String titulo, String autor, Date fecha, Time duracion, String descripcion, String formato, String url){
        this.autorID = autorId;
        this.titulo = titulo;
        this.autor = autor;
        this.fecha = fecha;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.formato = formato;
        this.reproducciones = 0;
        this.URL = url;
        this.URLINFO="Video local";
        this.STREAM=0;
            
    }
    
    public Video(int autorId, String titulo, String autor, Date fecha, Time duracion, String descripcion, String formato){
        this.autorID = autorId;
        this.titulo = titulo;
        this.autor = autor;
        this.fecha = fecha;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.formato = formato;
        this.reproducciones = 0;
        this.URL = "aux";
        this.URLINFO="Video local";
        this.STREAM=0;
            
    }
    
    public boolean createVideo(){
        boolean result = false;
        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            
            
            String sql = "INSERT INTO " + TABLE 
                    + "(autorid, titulo, AUTOR, fecha_creacion, duracion, reproducciones, descripcion, formato, URL, URL_INFO, STREAM)"
                    + " VALUES (" + 
                    this.autorID + ", '" + this.titulo + "', '" + this.autor + 
                    "', '" + this.fecha + "', '" + this.duracion + "', " + this.reproducciones + 
                    ", '" + this.descripcion + "', '" + this.formato + "', '" + this.URL + 
                    "', '" + this.URLINFO+"', "+ this.STREAM + ")";
            System.out.println("Sentencia SQL: " + sql);
            stmt.executeUpdate(sql);
            
            result = true;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return result;
    }
    
    public String getUrl(){
        return this.URL;
    }
    public int getAutorId(){
        return this.autorID;
    }
    public ArrayList<Video> getVideos(int userId){
       ArrayList<Video> listaVideos = new ArrayList(); 
       
       try {
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT * FROM " + TABLE + " WHERE AUTORID=" + userId;
            if (userId == -1){
                sql = "SELECT * FROM " + TABLE;
            }
            System.out.println("Sentencia SQL: " + sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                listaVideos.add(getVideoFromResultSet(rs));
            }            
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return listaVideos;
    }
    
     private Video getVideoFromResultSet(ResultSet rs) throws SQLException{
        return new Video(
                rs.getInt("AUTORID"),
                rs.getString("TITULO"),
                rs.getString("AUTOR"),
                rs.getDate("FECHA_CREACION"),
                rs.getTime("DURACION"),
                rs.getString("DESCRIPCION"),
                rs.getString("FORMATO"),
                rs.getString("URL"));
    }
     
     
    public boolean existsVideo(){
        boolean result = false;
        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT COUNT(*) as COUNT FROM " + TABLE + " WHERE autorid=" + this.autorID + " AND titulo='" + this.titulo+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                result = (rs.getInt("COUNT") > 0);
            }
            
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return result;
    }
     
    public String getTitulo(){
        return this.titulo;
    }
    
    public String getAutor(){
        return this.autor;
    }
    
    public Date getFecha(){
        return this.fecha;
    }
    
    public Time getDuracion(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        // Formatear la hora y mostrarla por pantalla
        String formattedTime = sdf.format(this.duracion);
        //System.out.println("Hora formateada: " + formattedTime);
        //return formattedTime;
        
        return this.duracion;
    }
    
    public int getReproducciones(){
        return this.reproducciones;
    }
    
    // THIIISI
    public String getStreamB(int id){
        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM VIDEOS WHERE ID=" + id ;
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                int a = rs.getInt("STREAM");
                            System.out.println("INTEFE:" +a);
                if (a == 1){return "Unstream";}else{return "Stream";}
            }
            else{return "Unavaliable";}
            } catch (SQLException err) {
            System.out.println(err.getMessage());
            return "Unavaliable";
        }
    }

    
    public String getDescripcion(){
        return this.descripcion;
    }
    
    public int getId(){
        int aux = 0;
        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT * FROM " + TABLE + " WHERE autorid=" + this.autorID + " AND titulo='" + this.titulo+"'";
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
    
    public int changeStream(int id){
        System.out.println("CHANGING StreamabilITY");
        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            
            String sql = "UPDATE VIDEOS SET STREAM = CASE WHEN STREAM = 1 THEN 0 ELSE 1 END WHERE ID ="  + id  ;
            stmt.executeUpdate(sql);
            System.out.println(sql);
            
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return id;
    }
    
    public boolean delete(int videoId){
        boolean result = false;
        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            
            String sql = "DELETE FROM " + TABLE + " WHERE ID=" + videoId;
            System.out.println("Sentencia SQL: " + sql);
            int filasAfectadas = stmt.executeUpdate(sql);
            if(filasAfectadas > 0)result = true;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return result;
    }
}
