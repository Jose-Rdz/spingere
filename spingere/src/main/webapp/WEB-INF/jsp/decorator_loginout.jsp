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
        <script src="${contextPath}/resources/js/jquery-3.2.1.min.js"></script>
        <script src="${contextPath}/resources/js/semantic.min.js"></script>
        <style>
            body {
                background-color: #FFFAFA;
            }
        </style>
        <sitemesh:write property='head'/>
    </head>
    <body>
        <sitemesh:write property='body' />
    </body>
</html>
