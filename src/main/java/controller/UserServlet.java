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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import jakarta.servlet.FilterChain;
//import jakarta.servlet.http.HttpSession;
import model.Usuario;
import model.Video;

/**
 *
 * @author alumne
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
     private void registro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener datos del formulario de registro
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        
        
        
        
        Usuario user = new Usuario(nombre, apellido, correo, username,password);
        Boolean existe = user.existeUsuario();
        Boolean creado = user.crearUsuario();
        
        if(!password.equals(confirmPassword)){
            request.setAttribute("error", "Las constraseñas no coinciden");
            // Redirigir de nuevo al formulario de login
            RequestDispatcher dispatcher = request.getRequestDispatcher("registroUsuario.jsp");
            dispatcher.forward(request, response);
        }
        else if(existe){
            request.setAttribute("error", "El nombre de usuario ya existe");
            // Redirigir de nuevo al formulario de login
            RequestDispatcher dispatcher = request.getRequestDispatcher("registroUsuario.jsp");
            dispatcher.forward(request, response);
        }
        else if(!creado){
            
            System.out.println("Error al crear usuario, indice en la base de datos??");
            request.setAttribute("error", "Error al crear usuario");
            // Redirigir de nuevo al formulario de login
            RequestDispatcher dispatcher = request.getRequestDispatcher("registroUsuario.jsp");
            dispatcher.forward(request, response);
            
        } 
        else{
            
            HttpSession session = request.getSession();
            
            session.setAttribute("username",username);
            session.setAttribute("userID", user.getId());
            
            Video aux = new Video();
            ArrayList<Video> videos = aux.getVideos(user.getId());
            session.setAttribute("userVideos", videos);
            //response.sendRedirect("listadoVideos.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("listadoVideos.jsp");
            dispatcher.forward(request, response);
        }
        // Lógica de registro aquí (puedes almacenar los datos en una base de datos, por ejemplo)
        

// Redirigir a una página de éxito o error
        //response.sendRedirect("registroExitoso.html");
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener datos del formulario de login
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        System.out.println("Nombre usuario: " + username);
        System.out.println("Password: " + password);
        
        Usuario user = new Usuario(username, password);
        Boolean existe = user.existeUsuario();
        Boolean login = user.correctLogin();
        if(login){
            System.out.println("El usuario existe, login correcto");
            HttpSession session = request.getSession();
            
            session.setAttribute("username",username);
            session.setAttribute("userID", user.getId());
            Video aux = new Video();
            ArrayList<Video> videos = aux.getVideos(user.getId());
            session.setAttribute("userVideos", videos);
            //response.sendRedirect("listadoVideos.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("listadoVideos.jsp");
            dispatcher.forward(request, response);
        }
        
        else if(!existe){
            request.setAttribute("error", "Usuario no existe");
            // Redirigir de nuevo al formulario de login
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
        
        else if(!login){
            request.setAttribute("error", "Credenciales incorrectas");
            // Redirigir de nuevo al formulario de login
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String param1 = request.getParameter("param1");
            if (param1 != null && param1.equals("logout")) {
                HttpSession session = request.getSession();
                if (session != null){
                session.invalidate();  
            }
           
            System.out.println("Session finished");
            response.setStatus(201);}
            //THIIIIS
            else if (param1 != null && param1.equals("streamch")){
                String id = request.getParameter("param2");
                Video aux = new Video();
                int a = aux.changeStream(Integer.parseInt(id));
                System.out.println(a + " VIDEO IS NOW STREAMABLE");

            }
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String action = request.getParameter("action");
        System.out.println("Accion: " + action);
        if ("register".equals(action)) {
            registro(request, response);
        } 
        else if ("login".equals(action)) {
            login(request, response);
        } 
        else {
            System.out.println("Error, no se ha detectado accion");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
