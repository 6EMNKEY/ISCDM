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
import java.util.ArrayList;
/**
 *
 * @author alumne
 */
@WebServlet(name = "ServletListado", urlPatterns = {"/ServletListado"})
@MultipartConfig
public class ServletListado extends HttpServlet {
    
        private static final String localFolder = "/home/alumne/Documentos/Videos/";
        private static final Path path = Paths.get(localFolder);

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
            out.println("<title>Servlet servletListado</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servletListado at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    
    private void upload(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String name = request.getParameter("videoName");
            
            String format = request.getParameter("format");
            
            System.out.println(format);
            
            String timeString = request.getParameter("duration");
            String description = request.getParameter("videoDescription");
            String author = request.getParameter("videoAuthor");
            
            int authorId = (Integer) request.getSession().getAttribute("userID");
            
            
            java.util.Date utilDate = new Date(Calendar.getInstance().getTimeInMillis());
            Date fechaSQL = new Date(utilDate.getTime());
            
            Long aux = (long)(Double.parseDouble(timeString)*1000);//pasam a milisegons
            Time sqlTime = new Time(aux);
            String urlinfo = request.getParameter("urlinfo");
            
            
            Part filePart = request.getPart("videoFile");
            String fileName = getSubmittedFileName(filePart);
            System.out.println("fileName: " + fileName);
            
            if (!Files.exists(path)) {
                System.out.println("Creando directorio");
                Files.createDirectories(path);
            }
            else{
                System.out.println("Directorio ya existe");
            }
            
            Path filePath = path.resolve(fileName);
            System.out.println("filePath: " + filePath.toString());
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            Video video = new Video(authorId, name, author, fechaSQL, sqlTime, description, format, urlinfo);
            if(video.createVideo()){
                System.out.println("Video añadido con exito");
                HttpSession session = request.getSession();
                ArrayList videos = (ArrayList) session.getAttribute("userVideos");
                videos.add(video);
                session.removeAttribute("userVideos");
                session.setAttribute("userVideos", videos);
                //response.sendRedirect("listadoVideos.jsp");
                RequestDispatcher dispatcher = request.getRequestDispatcher("listadoVideos.jsp");
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
        processRequest(request, response);
  
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
        System.out.println("Do post servletListado");
        System.out.println(request.getParameterMap());
        String action = request.getParameter("action");
        System.out.println(action);
        if("upload".equals(action)){
            upload(request,response);
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

    private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // Para manejar nombres de archivo con rutas completas
            }
        }
        return null;
    }

}
