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
                            <td><a id="viewlink" href="reproduccion.jsp?videoId=<%= video.getId() %>&p2=<%= video.getAutorId() %>"><%= video.getTitulo() %></a></td>
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
        
        var link = document.getElementById('viewlink');
            
            // Attach a click event listener to the link
            link.addEventListener('click', function() {
                // Prevent the default behavior of the link (e.g., navigating to a new page)
                var titol = link.getAttribute('data-id');
                var authid = link.getAttribute('data-value');
                
                var url = 'http://localhost:8080/ISDCM/RESTservlet';
        var xhr = new XMLHttpRequest(); // Replace with your servlet URL
        var paramValue = '4'; // Replace with your parameter value

        xhr.open('POST', url, true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    console.log('POST request successful');
                    // Handle successful response if needed
                } else {
                    console.error('POST request failed with status:', xhr.status);
                    // Handle failed response if needed
                }
            }
        };
        xhr.send(JSON.stringify({param1 : authid, param2: titol}));
        xhr.onload = function() {
            var videoId = this.responseText;
        };
        
        var xhr = new XMLHttpRequest(); // Replace with your servlet URL
        // // Replace with your parameter value

        xhr.open('PUT', url, true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    console.log('PUT request successful');
                    // Handle successful response if needed
                } else {
                    console.error('PUT request failed with status:', xhr.status);
                    // Handle failed response if needed
                }
            }
        };
        xhr.send(JSON.stringify({param1 : videoId}));
                
                
            });
        
        
        
    
    </script>
    </body>
</html>
