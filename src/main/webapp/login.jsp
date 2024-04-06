<%-- 
    Document   : login
    Created on : 26 feb 2024, 14:18:34
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="login.css">
    </head>
    <body>
    <div class="login-container">
        <h2>Login</h2>
        <form class="login-form" method="post" action="UserServlet">
            <div class="form-group">
                <label for="username">Nombre de Usuario:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <input type="hidden" name="action" value="login">
                <input type="submit" value="login">
                <label>No tienes cuenta? <a href="registroUsuario.jsp">Registrate</a></label>
            </div>
        </form>
        
        <%-- Verificar si hay un mensaje de error en la solicitud y mostrar el contenedor de errores --%>
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <div class="error-container">
            <p><strong>Error:</strong> <%= error %></p>
        </div>
    <%
        }
    %>
    </div>
</body>
</html>
