<%-- 
    Document   : login
    Created on : 16/11/2017, 12:41:14 PM
    Author     : G13380
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style>
            body{
                background-image: url('<c:url value="/resources/images/white_paperboard.png" />');
                background-repeat: repeat;
            }
        </style>
    </head>
    <body onload='document.login.user.focus();'>
        <div class="ui hidden divider"></div>
        <div class="ui basic segment">
            <h1 class="ui center aligned icon header">
                <i class="circular users icon"></i>
                <div class="content">
                    Bienvenido
                    <div class="sub header">Por favor ingrese sus credenciales...</div>
                </div>
            </h1>
        </div>
        <div class="ui rised segment">
            <c:if test="${not empty message}">
                <div class="ui success message">
                    <div class="header">
                        <i class="unlock icon"></i>
                        Éxito
                    </div>
                    <p>${message}</p>
                </div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="ui error message">
                    <div class="header">
                        <i class="lock icon"></i>
                        Error
                    </div>
                    <p>${error}</p>
                </div>
            </c:if>
            <form name='login' action="<c:url value='/login' />" method='POST' class="ui form">
                <div class="field">
                    <label>Usuario</label>
                    <input type="text" name="user" placeholder="Usuario" />
                </div>
                <div class="field">
                    <label>Contraseña</label>
                    <input type='password' name='pass' placeholder="Contrseña" />
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <button class="ui button" type="submit" tabindex="0">Ingresar</button>
            </form>
        </div>
    </body>
</html>
