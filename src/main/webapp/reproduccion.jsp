<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Video"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reproducción de Video</title>
    <link rel="stylesheet" href="reproduccion.css">
    <link href="https://vjs.zencdn.net/8.10.0/video-js.css" rel="stylesheet" />
</head>
<body>
    <div class="video-container">
        <% 
            String videoId = request.getParameter("videoId");
            Video video = new Video(Integer.parseInt(videoId));
            System.out.println("Url video: " + video.getUrl());
            String authid = request.getParameter("p2");
            String titol = video.getTitulo();
        %>
        <h1 class="video-title"><%= video.getTitulo() %></h1>
        <p class="video-description"><%= video.getDescripcion() %></p>
        <div class="video-info">
            <span class="video-info-item">Fecha de Subida: <%= video.getFecha() %></span>
            <span class="video-info-item">Reproducciones: <%= video.getReproducciones() %></span>
        </div>
        <video  controls preload="auto" width="640" height="360">
            <source src="<%= video.getUrl() %>" type="video/mp4">
            <!-- Otros formatos de video aquí -->
        </video>
    </div>
             <script src="https://vjs.zencdn.net/8.10.0/video.min.js"></script>

             <script> 
            
            
            var authid = '<%=authid  %>';
            var titol = '<%= titol %>';
            var url = 'http://localhost:8080/ISDCM/RESTservlet';
            var xhr = new XMLHttpRequest(); // Replace with your servlet URL
            console.log(authid, titol);

            xhr.open('POST', url, true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

            xhr.send(JSON.stringify({param1 : authid, param2: titol}));
            xhr.onload = function() {
                var videoId = this.responseText;
                console.log("videoID:"+ videoId);
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
            };

            
             </script>
</body>
</html>