<%-- 
    Document   : subirVideo
    Created on : 11 mar 2024, 19:02:40
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subir video</title>
        <link rel="stylesheet" href="subirVideo.css">

    </head>
    <body>
       
        <h2>Subir Video</h2>

        <form id="upload-form" action="ServletListado" method="post" enctype="multipart/form-data">
            <!-- Botón personalizado para abrir el selector de archivos -->
            <label for="file-input" id="upload-btn">Seleccionar Video</label>
            <!-- Campo de entrada de archivos oculto -->
            <input type="file" id="file-input" name="videoFile" accept="video/*" onchange="updateVideoInfo()">

            <!-- Campos adicionales que aparecerán al seleccionar un video -->
            <div class="form-group">
                <label for="video-name">Nombre del Video:</label>
                <input type="text" id="video-name" name="videoName" required>
            </div>

            <div class="form-group">
                <label for="video-duration">Duración:</label>
                <input type="text" id="video-duration" name="videoDuration" readonly>
            </div>

            <div class="form-group">
                <label for="video-description">Descripción:</label>
                <textarea id="video-description" name="videoDescription" rows="4"></textarea>
            </div>

            <div class="form-group">
                <label for="video-author">Nombre del Autor:</label>
                <input type="text" id="video-author" name="videoAuthor" required>
            </div>
            
            <input type="hidden" name="action" value="upload">
            <input type="hidden" id="videoDur" name="duration">
            <input type="hidden" id="videoFor" name="format">

            <!-- Botón de envío del formulario -->
            <input type="submit" value="upload">
        </form>
         <%
        String error = (String) request.getAttribute("error");
        System.out.println(error);
        if (error != null) {
        %>
            <div class="error-container">
                <p><strong>Error:</strong> <%= error %></p>
            </div>
        <%
            }
        %>
        
        <script>
            function updateVideoInfo() {
                var fileInput = document.getElementById('file-input');
                var videoNameInput = document.getElementById('video-name');
                var videoDurationInput = document.getElementById('video-duration');
                var videoDur = document.getElementById('videoDur');
                var videoFor = document.getElementById('videoFor');
                if (fileInput.files.length > 0) {
                    var videoFile = fileInput.files[0];
                    videoNameInput.value = videoFile.name.slice(0, videoFile.name.indexOf("."));;
                    videoFor.value = videoFile.name.slice(videoFile.name.indexOf(".")+1);
                    console.log(videoFor.value);
                    // Obtener la duración del video utilizando MediaSource API (requiere navegador moderno y formato de video compatible)
                    var video = document.createElement('video');
                    video.preload = 'metadata';
                    video.onloadedmetadata = function() {
                        var duration = video.duration;
                        videoDur.value = duration;
                        console.log(duration);
                        var minutes = Math.floor(duration / 60);
                        var seconds = Math.round(duration % 60);
                        videoDurationInput.value = padZero(minutes) + ':' + padZero(seconds);
                        URL.revokeObjectURL(video.src); // Liberar recursos
                    };

                    video.src = URL.createObjectURL(videoFile);
                } else {
                    // Limpiar los campos si no se selecciona ningún archivo
                    videoNameInput.value = '';
                    videoDurationInput.value = '';
                }
            }

            function padZero(num) {
                return (num < 10 ? '0' : '') + num;
            }
        </script>

    </body>
</html>
