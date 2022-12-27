<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Login</h1>
    <h4>${msg}</h4>
    <form action="ServletLogin" method="post">
        <input type="hidden" value="<%= request.getParameter("url") %>" name="url">
        <table>
            <tr>
                <td><label>Login</label></td>
                <td><input name="login" type="text"></td>
            </tr>
            <tr>
                <td><label>Senha</label></td>
                <td><input name="senha" type="password"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Enviar"></td>
            </tr>
        </table>
    </form>
</body>
</html>