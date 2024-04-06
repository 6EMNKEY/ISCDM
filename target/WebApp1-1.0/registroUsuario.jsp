    <%-- 
    Document   : login
    Created on : 22 feb 2024, 15:55:06
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" href="registroUsuario.css"/>
    </head>
    <body>
        <div class="register-container">
            <h2>Registro de Usuarios</h2>
            <form class="register-form" method="post" action="UserServlet">
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" required>
                </div>
                <div class="form-group">
                    <label for="apellido">Apellido:</label>
                    <input type="text" id="apellido" name="apellido" required>
                </div>
                <div class="form-group">
                    <label for="correo">Correo:</label>
                    <input type="email" id="correo" name="correo" required>
                </div>
                <div class="form-group">
                    <label for="username">Nombre de Usuario:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Contraseña:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <label for="confirm-password">Repetir Contraseña:</label>
                    <input type="password" id="confirm-password" name="confirm-password" required>
                </div>
                <div class="form-group">
                    <input type="hidden" name="action" value="register">
                    <input type="submit" value="Registrarse">
                    <label>Ya tienes una cuenta? <a href="login.jsp">Identificate</a></label>
                </div>
            </form>
            
            
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
