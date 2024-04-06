/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

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
import java.util.ArrayList;


@WebServlet("/restservlet")
public class RESTservlet extends HttpServlet {
    

    private final String url = "jdbc:derby://localhost:1527/ISDCM";
    private final String user = "miquel";
    private final String password = "miquel";
    private static final String table = "videos";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*try {
            // Establish database connection
            Connection connection = DriverManager.getConnection(url, user, password);
            
            // Create SQL statement
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM your_table";
            
            // Execute SQL query
            ResultSet resultSet = statement.executeQuery(sql);
            
            // Process ResultSet and generate response
            StringBuilder responseData = new StringBuilder();
            while (resultSet.next()) {
                responseData.append("ID: ").append(resultSet.getInt("id"))
                            .append(", Name: ").append(resultSet.getString("name"))
                            .append(", Age: ").append(resultSet.getInt("age"))
                            .append("\n");
            }
            
            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
            
            // Set response type and write data
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.println(responseData.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching data");
        }*/
    }
}
