
<%
    if (String.valueOf(session.getAttribute("USER")).equals("admin") == false) {
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
        <a href="customer.jsp">Edit Customers</a>
        |
        <a href="login.jsp">Log out</a>
        <h1>Products</h1>
        <form action="ControllerProducts">
            Nhap ten SP: <input id="search" type="text" name="txtTenSP" value=""> 
            <input type="submit" name="action" value="Search">
        </form>
        <table border="1">
            <tr>
                <td>Code</td>
                <td>Image</td>
                <td>Name</td>
                <td>Price</td>
                <td>Edit</td>
                <td>Delete</td>
            </tr>
            <c:forEach var="rows" items="${listSP}">
                <form action="ControllerProducts"> <%--Lưu ý: phải đặt form trong c:forEach để có thể lấy giá trị từng dòng--%>
                    <tr>
                        <td> <input type="text" name="txtCode" value="${rows.code}" readonly> </td>
                        <td> 
                            <img src="${rows.image}" style="width: 140px; height: 145px;"><br>
                            <input type="file" name="EditImage" style="width: 70%;">
                        </td>
                        <td> <input type="text" name="txtName" value="${rows.name}"> </td>
                        <td> <input type="text" name="txtPrice" value="${rows.price}"> </td>
                        <td> 
                            <input type="hidden" name="txtSearch" value="${param.txtTenSP}">
                            <input type="submit" name="action" value="Edit">
                        </td>

                        <c:url var="del" value="ControllerProducts">
                            <c:param name="action" value="Delete"/>
                            <c:param name="txtCode" value="${rows.code}"/>
                            <c:param name="txtSearch" value="${param.txtTenSP}"/>
                        </c:url>
                        <td>
                            <a href="${del}">Delete</a>
                        </td>
                    </tr>
                </form>
            </c:forEach>
            <form action="ControllerProducts">
                <tr>
                    <td>
                        <input type="text" name="txtNewCode" value="" placeholder="Nhap ma sp">
                    </td>
                    <td>
                        <input type="file" name="NewImage"/>
                    </td>
                    <td>
                        <input type="text" name="txtNewName" value="" placeholder="Nhap ten sp">
                    </td>
                    <td>
                        <input type="text" name="txtNewPrice" value="" placeholder="Nhap gia sp">
                    </td>
                    <td colspan="2"><input type="submit" name="action" value="Insert" style="width: 100%;"></td>
                </tr>
            </form>
        </table>
        <style>
            td{
                text-align: center;
            }
            input{
                text-align: center;
            }
            a{
                text-decoration: none;
            }
        </style>
    </body>
</html>
