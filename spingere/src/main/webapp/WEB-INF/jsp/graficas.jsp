<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Gráficas</title>
        <c:set var="contextPath" value="${pageContext.servletContext.contextPath}" />
        <script src="${contextPath}/resources/js/owned/graficas.js"></script>
        <script src="${contextPath}/resources/js/canvasjs.min.js"></script>
    </head>
    <body>
        <h2 class="ui header">Ver gráficas</h2>
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
                    <label>Gráficas</label>
                    <select id="dGraficas" class="ui disabled dropdown">
                        <option value="">Elija una gráfica...</option>
                        <c:forEach items="${graficas}" var="g">
                            <option value="${g.idGrafica}">${g.nombreGrafica}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div id="displayGrafica" class="ui rised segment" style="display: none;"></div>
        <div id="displayGraficaBarras" class="ui rised segment" style="display: none;">
            <div class="ui rised segment form">
                <div class="one field">
                    <div class="field">
                        <label>Gráfica</label>
                        <select id="dTipoGraficaBarras" class="ui dropdown">
                            <option value="">Elija una gráfica</option>                            
                        </select>
                    </div>
                </div>
            </div> 
            <div id="chartContainer" style="height: 500px; width: 100%;"></div>
        </div>
    </body>
</html>