<!DOCTYPE html>

<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
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
            <security:authorize access="isAuthenticated()">
                <div class="item">
                    <i class="user circular icon"></i>
                    @<security:authentication property="principal.username" />
                </div>
            </security:authorize>
            <a href="${contextPath}" class="item"><i class="home icon"></i> Home</a>
            <security:authorize access="hasAnyRole('ADMIN', 'USER')">
                <a href="${contextPath}/sao" class="item"><i class="globe icon"></i></i> SAO - Ciclo de vida</a>
                <a href="http://enki.work:80/MicroStrategy/servlet/mstrWeb?evt=3186&src=mstrWeb.3186&subscriptionID=55C3ED8811E81AA3C4310080EF45EC30&Server=172.31.20.42&Project=POC&Port=0&share=1" target="_blank" class="item"><i class="tasks icon"></i> Estimador</a>
                <a href="${contextPath}/graficas" class="item"><i class="bar chart icon"></i></i> Gráficas</a>
                <a href="${contextPath}/modelos" class="item"><i class="area chart icon"></i></i> Modelos</a>
            </security:authorize>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <a href="${contextPath}/usuarios" class="item"><i class="users icon"></i> Usuarios</a>
                <a href="${contextPath}/carga" class="item"><i class="upload icon"></i> Carga Archivos</a>
                <a href="${contextPath}" class="item"><i class="setting icon"></i> Configuración</a>
            </security:authorize>
        </div>
        <div class="ui grey big launch right attached fixed button">
            <i class="content icon"></i>
            <span class="text">Menu</span>
        </div>
        <div class="pusher">
            <header class="ui center aligned header">
                <img class="ui image" src="${contextPath}/resources/images/LogoSpingere.png" style="min-width:150px; max-width:15%; height:auto;" />
                <div class="content">Dimensionamiento y Estimación Profesional de Software</div>
            </header>
            <main class="ui container" style="padding: 1em 0;">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <div class="ui clearing basic segment">
                        <a href="javascript:document.getElementById('logout').submit()" class="ui right floated basic teal circular icon button">
                            <i class="power icon"></i>
                            Cerrar Sesión
                        </a>
                    </div>
                </c:if> 
                <sitemesh:write property='body' />
            </main>
            <div class="ui section divider"></div>
            <footer class="ui center aligned text container">
                Insurgentes Sur 1431, piso 10<br/>                
                Colonia Insurgentes Mixcoac, C.P. 03920<br/>
                México, CDMX<br/>
                Tel. +52 55 8852 2736<br/>
                email: <a href="mailto:contacto@spingere.com.mx">contacto@spingere.com.mx</a><br/>                
            </footer>
        </div>
        <div id="requestLoader" class="ui dimmer" style="opacity: 0.7">
            <div class="ui indeterminate large text loader">Espere un momento por favor.</div>
        </div>
        <%-- spring security logout --%>
        <form id="logout" action="<c:url value='/logout' />" method="POST" >
            <security:csrfInput />
        </form>
    </body>
</html>