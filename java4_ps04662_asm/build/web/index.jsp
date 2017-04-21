
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Product" %>
<%@page import="Model.Products" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="index.jsp">Home</a>
        |
        <c:choose>
            <c:when test="${sessionScope.USER != null}">
                    Welcome, ${sessionScope.USER}!
                    |
                    <a href="login.jsp">Log out</a>
            </c:when>
            <c:otherwise>
                <a href="login.jsp">Log in</a>
            </c:otherwise>
        </c:choose>
                
        <h1>Shop</h1>
        <form action="ControllerProducts">
            <table border="1">
                <tr>
                    <td>Ma SP</td>
                    <td>Anh</td>
                    <td>Ten SP</td>
                    <td>Gia</td>
                    <td>Mua</td>
                </tr>
                <%
                    Products listSP = new Products();
                    List<Product> list = listSP.showProduct("");

                    List<Product> listCart = new ArrayList<Product>();
                    listCart = (List<Product>) session.getAttribute("listCart");
                    //if (listCart != null) {
                     //   for (Product i : listCart) {
                     //       out.println(i.getCode());
                      //  }
                    //}
                %>
                
                <% for (Product item : list) {%>
                <tr>
                    <td> <%=item.getCode()%> </td>
                    <td> <img src="<%=item.getImage()%>" style="height: 150px; width: 130px;"> </td>
                    <td> <%=item.getName()%> </td>
                    <td> <%=item.getPrice()%> </td>
                    <%
                        boolean kt = false;
                        if (listCart != null) {                         
                            for (Product i : listCart) {
                                if (item.getCode().equals(i.getCode())) {
                                    kt = true;
                                    break;
                                }
                            }
                        }
                    %>
                    <c:set var="kt" value="<%= kt %>"/>
                    <td><input type="checkbox" name="ckbSP" value="<%=item.getCode()%>" ${kt == true ? 'checked' : ''} ></td>
                </tr>
                <% }%>
            </table>
            <input type="submit" name="action" value="Add to cart">
        </form>
            
            <style>
                a{
                    text-decoration: none;
                }
            </style>
    </body>
</html>
