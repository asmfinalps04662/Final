
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome, ${sessionScope.USER}!</h1> <br>
        <a href="customer.jsp">Edit Customers</a>
        |
        <a href="products.jsp">Edit Products</a>
        |
        <a href="login.jsp">Log out</a>
        <style>
            a{
                text-decoration: none;
            }
        </style>
    </body>
</html>
