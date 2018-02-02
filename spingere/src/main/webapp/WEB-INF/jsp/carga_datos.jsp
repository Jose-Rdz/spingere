<%@page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Carga de datos</title>
        <c:set var="contextPath" value="${pageContext.servletContext.contextPath}" />
        <script src="${contextPath}/resources/js/owned/carga_datos.js"></script>
    </head>
    <body>
        <h3>Cargar información desde archivo xls</h3>
        <form class="ui form" action="<c:url value='/xlsx' />" enctype="multipart/form-data" method="POST">
            <div class="two fields">
                <div class="field">
                    <input type="file" name="xlsxFile" accept=".xlsx" />
                </div>
                <div class="field">
                    <button class="ui primary icon button" type="submit">
                        <i class="upload icon"></i>
                        Cargar
                    </button>
                </div>
            </div>
            <%-- spring security logout --%>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
        <div id="progressBar" class="ui indicating progress">
            <div class="bar">
                <div class="progress"></div>
            </div>
            <div id="alertMsg" class="label"></div>
        </div>
    </body>
</html>
