

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            session.setAttribute("USER", null);
        %>
        <a href="index.jsp" style="text-decoration: none;">Home</a>
        <h1>Login</h1>
        <table >
            <form action="ControllerCustomers">
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="txtUser" value=""></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="text" name="txtPass" value=""></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Login" style="margin-left: 50px;">
                        <input type="reset" name="reset" value="Reset">
                    </td>
                </tr>
            </form>
        </table>
    </body>
</html>
