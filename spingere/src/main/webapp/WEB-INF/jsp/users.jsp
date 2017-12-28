<%-- 
    Document   : catalogo_cliente
    Created on : 15/07/2015, 10:50:32 AM
    Author     : g13380
--%>
<%@page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <c:set var="contextPath" value="${pageContext.servletContext.contextPath}" />
        <title>Usuarios</title>
        <script src="${contextPath}/resources/js/owned/users.js"></script>
    </head>
    <body>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <div class="ui clearing basic segment">
                <h4 class="ui left floated header">${fecha}</h4>
                <a href="javascript:document.getElementById('logout').submit()" class="ui right floated teal icon button">
                    <i class="power icon"></i>
                    Cerrar Sesión
                </a>
            </div>
        </c:if>        
        <h1 class="ui header">Usuarios registrados:</h1>
        <div id="grid-clientes" style="height: 220px;"></div>        
        <div id="divFormaAlta" style="visibility: hidden">        
        <form id="formUser" class="ui form">                            
            <h1>Alta / Modificación</h1>

            <div class="ui right aligned basic segment">
                <button id="btnHideForm" class="ui secondary button">                
                    Ocultar
                </button>                
            </div>
            <div class="required field">
                <label>Cliente:</label>
                <select id="tipoCliente" name="tipoCliente" class="ui dropdown"> </select>
            </div>
            <div class="two fields">
                <div class="two wide field">
                    <label>Id Usuario:</label>
                    <input name="idUsuario" disabled />
                </div>
                <div class="fourteen wide required field">
                    <label>Usuario</label>
                    <input name="usuario" placeholder="Usuario" />
                </div>
            </div>            
            <div class="three required fields">
                <div class="field">
                    <label>Nombre</label>
                    <input name="nombre" placeholder="Nombre" />
                </div>
                <div class="field">
                    <label>Apellido Paterno</label>
                    <input name="apPaterno" placeholder="Apellido Paterno" />
                </div>
                <div class="field">
                    <label>Apellido Materno</label>
                    <input name="apMaterno" placeholder="Apellido Materno" />
                </div>
            </div>
            <div class="two required fields">
                <div class="field">
                    <label>Email</label>
                    <input name="email" placeholder="Email" />
                </div>
                <div class="field">
                    <label>Contraseña</label>
                    <input name="contrasena" placeholder="Contraseña" />
                </div>
            </div>
        </form>
        <div class="ui hidden divider"></div>
        <div class="ui right aligned basic segment">
            <button id="btnGuardar" class="ui teal left labeled icon button">
                <i class="save icon"></i>
                Registrar
            </button>                
        </div>
        </div>
        <%-- spring security logout --%>
        <form id="logout" action="<c:url value='/logout' />" method="POST" >
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </body>
</html>
