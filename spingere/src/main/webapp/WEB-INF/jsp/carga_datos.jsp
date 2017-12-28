<%-- 
    Document   : carga_datos
    Created on : 28/11/2017, 12:25:17 PM
    Author     : AnGeL
--%>

<%@page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Carga de datos</title>
    </head>
    <body>
        <h3>Cargar información desde archivo xls</h3>
        <form action="<c:url value='/xls' />" enctype="multipart/form-data" method="POST" class="ui form">
            <div class="field">
                <input type="file" name="xlsFile" />
            </div>
            <button class="ui button" type="submit" value="Cargar" />
        </form>
    </body>
</html>
