<%@page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Acceso Denegado</title>
        <style>
            body{
                font-family: 'Ropa Sans', sans-serif;
                margin-top: 30px;
                background-color: #504945;
                text-align: center;
                color: #fff;
            }
            .error-heading{
                margin: 50px auto;
                width: 250px;
                border: 5px solid #fff;
                font-size: 126px;
                line-height: 126px;
                border-radius: 30px;
                text-shadow: 6px 6px 5px #000;
            }
            .error-heading img{
                width: 100%;
            }
            .error-main h1{
                font-size: 72px;
                margin: 0px;
                color: #FAFAFA;
                text-shadow: 0px 0px 5px #fff;
            }
            .error-main a {
                color: #F5B041;
            }
        </style>
    </head>
    <body>
        <div class="error-main">
            <h1>Oops!</h1>
            <div class="error-heading">403</div>
            <h2 class="ui header">¡No tienes permisos para ver esta página!</h2>
            <h3><a href="<c:url value='/login' />">Regresar</a></h3>
	</div>
    </body>
</html>
