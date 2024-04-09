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
    <link rel="stylesheet" href="listadoVideos.css">
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

<table id="SearchTable"" >
  <thead>
    <tr>
      <th>TITULO</th>
      <th>AUTOR</th>
      <th>FECHA_CREACION</th>
      <th>DESCRIPCION</th>
      <th>REPRODUCCIONES</th>
    </tr>
  </thead>
  <tbody> </tbody>
</table>
  

</body>

<script> 
    document.getElementById("search-form").addEventListener("submit", function(event) {
        event.preventDefault();
        var xhr = new XMLHttpRequest();
        var searchInput = document.getElementById("search-input").value;
        var query = encodeURIComponent(searchInput);
        var url = "RESTservlet?searchval=" + query;
        console.log(url);
        xhr.open("GET",url, true);
        xhr.send();
        xhr.onload = function() {
                var tbody = document.querySelector("#SearchTable tbody" );
                // Remove all child elements (rows) from tbody
                while (tbody.firstChild) {
                  tbody.removeChild(tbody.firstChild);
                }
                if (this.status === 200) {

                var lines = this.responseText.split(",UWU,");

                // Iterate over lines to create table rows
                lines.forEach(function(line) {
                  var rowData = line.split(", ");
                  var row = document.createElement("tr");
                  rowData.forEach(function(cellData) {
                    var cellParts = cellData.split(": ");
                    var cell = document.createElement("td");
                    cell.textContent = cellParts[1];
                    row.appendChild(cell);
                  });
                  document.querySelector("#SearchTable tbody").appendChild(row);
                });
                    console.log(this.responseText);
    } else {console.log(this.status)}
    xhr.close();
};
        
        var url = 'http://localhost:8080/ISDCM/RESTservlet';
        var xhr = new XMLHttpRequest(); // Replace with your servlet URL
        var paramValue = '3'; // Replace with your parameter value

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
        xhr.send('param1=' + encodeURIComponent(paramValue));
        });
        
</script>

</html>
