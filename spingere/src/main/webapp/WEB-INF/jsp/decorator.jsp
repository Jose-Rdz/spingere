<!DOCTYPE html>
<%-- 
    Document   : decorator
    Created on : 8/11/2017, 10:40:05 AM
    Author     : g13380
--%>
<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <title>Spingere - <sitemesh:write property="title" /></title>
        <c:set var="contextPath" value="${pageContext.servletContext.contextPath}" />
        <link rel="shortcut icon" type="image/png" href="${contextPath}/resources/images/spingere.png">
        <link rel="stylesheet" href="${contextPath}/resources/css/semantic-ui/semantic.min.css">
        <link rel="stylesheet" href="${contextPath}/resources/css/w2ui-1.5.rc1.min.css">
        <link rel="stylesheet" href="${contextPath}/resources/css/datepick/smoothness.datepick.css">
        <script src="${contextPath}/resources/js/jquery-3.2.1.min.js"></script>
        <script src="${contextPath}/resources/js/semantic.min.js"></script>
        <script src="${contextPath}/resources/js/w2ui/w2ui-1.5.rc1.min.js"></script>
        <script src="${contextPath}/resources/js/datepick/jquery.plugin.min.js"></script>
        <script src="${contextPath}/resources/js/datepick/jquery.datepick.min.js"></script>
        <script src="${contextPath}/resources/js/datepick/jquery.datepick-es.js"></script>
        <script src="${contextPath}/resources/js/datepick/jquery.datepick.ext.min.js"></script>
        <script>
            w2utils.locale('${contextPath}/resources/js/w2ui/es-mx.json');
        </script>
    <sitemesh:write property='head'/>
    <style>
        h2 {
            color: rgb(150,150,150);             
        }
        p.colorAzul1 {
            color: rgb(26,117,187); 
            size: 10px;
        }
    </style>
</head>
<body>
    <header>
        <h2 align="center">
            <img src="${contextPath}/resources/images/LogoSpingere.png" style="min-width:150px; max-width:15%; height:auto;">
            Dimensionamiento y Estimación Profesional de Sowftware
        </h2>    
    </header>
    <main class="ui container">
        <sitemesh:write property='body' />
    </main>
    <div class="ui hidden divider"></div>
    <footer>
        <p class="colorAzul1" align="center">Spingere S.A. de C.V.</p>
        <p class="colorAzul1" align="center">Contacto: <a href="mailto:fulanito@gmail.com">fulanito@gmail.com</a></p>
        <p class="colorAzul1" align="center">Tel: 55-5555-55555</p>
    </footer>
    <%--div id="preloader" style="opacity: .75;">
        <div class="ui indeterminate text large visible inverted loader">Espere un momento por favor.</div>
    </div--%>
</body>
</html>
