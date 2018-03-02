<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Modelos</title>
        <c:set var="contextPath" value="${pageContext.servletContext.contextPath}" />
        <script src="${contextPath}/resources/js/owned/modelos.js"></script>
        <script src="${contextPath}/resources/js/canvasjs.min.js"></script>
    </head>
    <body>
        <h2 class="ui header">Modelos</h2>
        <div class="ui rised segment form">
            <div class="three fields">
                <div class="field">
                    <label>Clientes</label>
                    <select id="dClientes" class="ui dropdown">
                        <option value="">Elija un cliente...</option>
                        <c:forEach items="${clientesUsuario}" var="c">
                            <option value="${c.idCliente}">${c.cliente}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="field">
                    <label>Proyectos</label>
                    <select id="dProyectos" class="ui disabled dropdown">
                        <option value="">Elija un proyecto...</option>
                    </select>
                </div>
                <div class="field">
                    <label>Modelos</label>
                    <select id="dModelos" class="ui disabled dropdown">
                        <option value="">Elija un modelo...</option>
                        <c:forEach items="${modelos}" var="m" varStatus="status">
                            <option value="${status.count}">${m}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div id="chartContainer" style="height: 300px; width: 100%;"></div>
    </body>
</html>
