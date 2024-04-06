<%-- 
    Document   : busqueda
    Created on : 4 abr 2024, 10:53:21
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Busqueda de videos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        header {
            background-color: #333;
            color: #fff;
            padding: 10px 20px;
            text-align: center;
        }
        #search-container {
            text-align: center;
            margin-top: 50px;
        }
        #search-container input[type="text"] {
            padding: 10px;
            width: 300px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
        }
        #search-container input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        #result-container {
            margin-top: 20px;
            text-align: center;
        }
        #result-container iframe {
            width: 800px;
            height: 450px;
        }
    </style>
</head>
<body>

<header>
    <h1>Busqueda de videos</h1>
</header>

<div id="search-container">
    <form id="search-form">
        <input type="text" id="search-input" placeholder="Paraules clau del video...">
        <input type="submit" value="Search">
    </form>
</div>

<div id="result-container">
    <iframe id="video-frame" src="" frameborder="0" allowfullscreen></iframe>
</div>
</body>
<script> 
    document.getElementById("search-form").addEventListener("submit", function(event) {
        event.preventDefault();
        var searchInput = document.getElementById("Search").value;
        var query = encodeURIComponent(searchInput);
        var url = "http://your-server-url:port/your-servlet-path?query=" + query;
        fetch(url)
            .then(response => response.json())
            .then(data => {
                if(data.length > 0) {
                        videosArray = data;
                        for (int i = 0; i < videosArray.size(); ++i){
                        out.println("<tr>");
                        out.println("<td>" + video.getTitulo() + "</td>"); 
                        out.println("<td>" + video.getAutor()+ "</td>"); 
                        out.println("<td>" + video.getFecha()+ "</td>"); 
                        out.println("<td>" + video.getDuracion()+ "</td>"); 
                        out.println("<td>" + video.getReproducciones()+ "</td>"); 
                        out.println("<td>" + video.getDescripcion()+ "</td>"); 
                        out.println("</tr>");}
                }});
        });
        
</script>
</html>
