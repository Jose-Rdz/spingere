<!DOCTYPE html>

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
        <link rel="stylesheet" href="${contextPath}/resources/css/owned/global.css">
        <script src="${contextPath}/resources/js/jquery-3.2.1.min.js"></script>
        <script src="${contextPath}/resources/js/semantic.min.js"></script>
        <script src="${contextPath}/resources/js/w2ui/w2ui-1.5.rc1.min.js"></script>
        <script src="${contextPath}/resources/js/datepick/jquery.plugin.min.js"></script>
        <script src="${contextPath}/resources/js/datepick/jquery.datepick.min.js"></script>
        <script src="${contextPath}/resources/js/datepick/jquery.datepick-es.js"></script>
        <script src="${contextPath}/resources/js/datepick/jquery.datepick.ext.min.js"></script>
        <script src="${contextPath}/resources/js/owned/global.js"></script>
        <script>
            w2utils.locale('${contextPath}/resources/js/w2ui/es-mx.json');
        </script>
        <sitemesh:write property='head'/>
    </head>
    <body>
        <div class="ui small vertical inverted grey sidebar labeled icon menu left">
            <a href="${contextPath}" class="item"><i class="home icon"></i> Home</a>
            <a href="${contextPath}/usuarios" class="item"><i class="users icon"></i> Usuarios</a>
            <a href="${contextPath}/graficas" class="item"><i class="bar chart icon"></i></i> Gráficas</a>
            <a href="${contextPath}/carga" class="item"><i class="upload icon"></i> Carga Archivos</a>
            <a href="${contextPath}" class="item"><i class="setting icon"></i> Configuración</a>
        </div>
        <div class="ui grey big launch right attached fixed button">
            <i class="content icon"></i>
            <span class="text">Menu</span>
        </div>
        <div class="pusher">
            <header class="ui center aligned attached header">
                <img class="ui image" src="${contextPath}/resources/images/LogoSpingere.png" style="min-width:150px; max-width:15%; height:auto;" />
                <div class="content">
                    Dimensionamiento y Estimación Profesional de Software
                </div>   
            </header>
            <main class="ui container">
                <sitemesh:write property='body' />
            </main>
            <div class="ui section divider"></div>
            <footer class="ui center aligned text container">
                Spingere S.A. de C.V.<br/>
                Contacto: <a href="mailto:fulanito@gmail.com">fulanito@gmail.com</a><br/>
                Tel: 55-5555-55555<br/>
            </footer>
        </div>
        <div id="requestLoader" class="ui dimmer" style="opacity: 0.7">
            <div class="ui indeterminate large text loader">Espere un momento por favor.</div>
        </div>
    </body>
</html>
