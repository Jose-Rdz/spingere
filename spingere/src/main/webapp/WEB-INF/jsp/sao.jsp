<%@page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>SAO - Ciclo de Vida</title>
        <c:set var="contextPath" value="${pageContext.servletContext.contextPath}" />
        <script src="${contextPath}/resources/js/owned/carga_datos.js"></script>
    </head>
    <body>
        <div class="ui rised segment">
            <img class="ui image" src="${contextPath}/resources/images/proceso_alto_nivel_demo.png">
        </div>
    </body>
</html>
