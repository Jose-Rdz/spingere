<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
    <head>
        <title>Login</title>
        <style>
            body > .grid {
                height: 100%;
            }
            .column {
                max-width: 450px;
            }
        </style>
    </head>
    <body onload='document.login.user.focus();'>
        <div class="ui middle aligned center aligned grid">
            <div class="column">
                <div class="ui basic segment">
                    <h1 class="ui center aligned icon header">
                        <i class="circular users icon"></i>
                        <div class="content">
                            Bienvenido
                            <div class="sub header">Por favor ingrese sus credenciales...</div>
                        </div>
                    </h1>
                </div>
                <c:if test="${not empty message}">
                    <div class="ui success message">
                        <div class="header">
                            <i class="checkmark icon"></i>
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
                <form name='login' action="<c:url value='/login' />" method='POST' class="ui large form">
                    <div class="ui stacked segment">
                        <div class="field">
                            <div class="ui left icon input">
                                <i class="user icon"></i>
                                <input type="text" name="user" placeholder="Usuario" />
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui left icon input">
                                <i class="lock icon"></i>
                                <input type='password' name='pass' placeholder="Contraseña" />
                            </div>
                        </div>
                        <security:csrfInput />
                        <button class="ui large teal fluid button" type="submit" tabindex="0">Ingresar</button>
                    </div>
                    <div class="ui message">
                        ¿No tienes cuenta? <a href="#">Regístrate</a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
