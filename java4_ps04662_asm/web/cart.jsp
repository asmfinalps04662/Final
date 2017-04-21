
<%
    if (session.getAttribute("USER") == null) {
        String path = request.getContextPath();
        String url = path + "/login.jsp";
        response.sendRedirect(url);
    }
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${sessionScope.USER}'s Cart</h1>
        <table border="1">
            <tr>
                <td>Ma SP</td>
                <td>Anh</td>
                <td>Ten SP</td>
                <td>Gia</td>
                <td>So luong</td>
            </tr>
            <form action="ControllerProducts">
                <c:forEach var="item" items="${listCart}">

                    <tr>
                        <td>
                            <%--${sessionScope.listCart[1].code}--%>
                            ${item.code}
                        </td>
                        <td><img src="${item.image}" style="width: 140px; height: 145px;"</td>
                        <td>${item.name}</td>
                        <td>${item.price}</td>
                        <td><input type="text" name="txtSL" value="1" style="width: 50%;"></td>

                        <c:url var="del" value="ControllerProducts">
                            <c:param name="action" value="DelCart"/>
                            <c:param name="txtCode" value="${item.code}"/>
                        </c:url>
                        <td>
                            <a href="${del}" style="font-size: 30px;text-decoration: none;">X</a>
                        </td>
                    </tr>

                </c:forEach>
                <tr>
                    <td><input type="button" name="action" value="Buy" onclick="alert('...')"></td>
                    <td>
                        <a href="index.jsp">Back to Shop</a>
                    </td>
                </tr>
            </form>
        </table>
        <style>
            td{
                text-align: center;
            }
            a{
                text-decoration: none;
            }
        </style>
    </body>
</html>
