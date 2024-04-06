<%-- 
    Document   : listadoVideos
    Created on : 7 mar 2024, 14:53:59
    Author     : alumne
--%>
<%@page import="model.Video"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <button id = "logoutButton">Logout</button> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="listadoVideos.css">
    </head>
    <body>

        <div class="welcome-message">
            <h2>Bienvenido, <%= session.getAttribute("username") %>!</h2>
            <p>¡Has iniciado sesión con éxito!</p>

        </div>
        <a href="subirVideo.jsp" class="add-button">Añadir nuevo video</a>

        <h2>Listado de Videos</h2>
        <a href="busqueda.jsp"> Buscar Videos </a>
        <table>
            <thead>
                <tr>
                    <th>Título</th>
                    <th>Autor</th>
                    <th>Fecha</th>
                    <th>Duración</th>
                    <th>Reproducciones</th>
                    <th>Descripción</th>
                    <th>Streamable</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList videosArray = (ArrayList) session.getAttribute("userVideos");

                    for (int i = 0; i < videosArray.size(); ++i){
                        Video video = (Video) videosArray.get(i);
                %>
                        <tr>
                            <td><%= video.getTitulo() %></td>
                            <td><%= video.getAutor() %></td>
                            <td><%= video.getFecha() %></td>
                            <td><%= video.getDuracion() %></td>
                            <td><%= video.getReproducciones() %></td>
                            <td><%= video.getDescripcion() %></td>
                            <td>
                                <button onclick="streamVideo('<%= video.getId() %>' )"><%= video.getStreamB(video.getId())%></button>
                            </td>
                        </tr>
                <%
                    }                                    
                %>
            </tbody>
         </table>
    <script>
        document.getElementById("logoutButton").addEventListener("click", function(){
            var xhr = new XMLHttpRequest();
            var url = 'UserServlet?param1=' + encodeURIComponent("logout");
            xhr.open("GET",url, true);
            xhr.send();
            xhr.onload =  function() {if (xhr.status === 201){
                window.location.href = "busqueda.jsp";
        }};
        });
        
        function streamVideo(videoId) {
            var xhr = new XMLHttpRequest();
            var videoId2 = ""+videoId;
            var url = 'UserServlet?param1=' + encodeURIComponent("streamch") + "&param2=" +encodeURIComponent(videoId2);
            console.log(url);
            xhr.open("GET",url, true);
            xhr.send();
        }
        
    
    </script>
    </body>
</html>
