/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Path; 
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import model.Video;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


@WebServlet(name= "RESTservlet",urlPatterns = {"/RESTservlet"})
public class RESTservlet extends HttpServlet {
    
    String RestUrl = "http://localhost:8080/REST/resources/RESTful";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String searchVal = request.getParameter("searchval"); // Value for searchval parameter
            URL url = new URL(RestUrl + "?searchval=" + searchVal);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            //connection.setRequestProperty("Accept", "application/json");
            

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder message = new StringBuilder();
            
            while ((line = reader.readLine()) != null) {
                message.append(line);
            }
            reader.close();
            
            PrintWriter out = response.getWriter();
            out.println(message);
            out.close();
            

            System.out.println(message);
            
        } catch (Exception e) {
            System.out.println("Error while getting values from REST (RESTServlet)");
            e.printStackTrace();
        }
        System.out.println("dsods");
        
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String videoId = request.getParameter("param1");
             // Value for videoid parameter
            URL url = new URL("http://localhost:8080/REST/resources/RESTful");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);

            String requestBody = "videoid=" + videoId;
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestBody.getBytes());
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
   
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String author = "John Doe";
            String title = "Example Title";

            URL url = new URL("http://example.com/api/resource");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            String requestBody = "author=" + author + "&title=" + title;
            System.out.println("LOCURAA");
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestBody.getBytes());
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            // RETORNA EL VIDEO ID
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
