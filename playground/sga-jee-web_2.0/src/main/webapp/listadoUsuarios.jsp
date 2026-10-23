<%-- 
    Document   : listadoUsuarios.jsp
    Created on : 22/10/2026, 6:24:10 p. m.
    Author     : Luis Mutis
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado usuarios</title>
    </head>
    <body>
        <h1>!Listado de usuarios 2!</h1>

        <ul>
            <c:forEach items="${usuarios}" var="user">
                <li>${user.username} - ${user.password}</li>
                </c:forEach>
        </ul>

        <br/>
        <br/>
        <a href="index.html">Volver al inicio</a>
    </body>
</html>
