<%--
  Created by IntelliJ IDEA.
  User: Romulo
  Date: 30/12/2022
  Time: 06:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tela que mostra os erros</title>
</head>
<body>
    <h1>Mensagem de erro, entre em contato com o suporte.</h1>
    <%
        out.print(request.getParameter("msg"));
    %>
</body>
</html>
