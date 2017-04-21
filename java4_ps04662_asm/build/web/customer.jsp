
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
        <a href="products.jsp">Edit Products</a>
        |
        <a href="login.jsp">Log out</a>
        <h1>Customers</h1>
        <form action="ControllerCustomers">
            Ten KH: <input type="text" name="txtTenKH" value="">
            <input type="submit" name="action" value="Search">
        </form>

        <table border="1">
            <tr>
                <td>Username</td>
                <td>Anh</td>
                <td>Password</td>
                <td>HoTen</td>
                <td>GioiTinh</td>
                <td>Email</td>
                <td>VaiTro</td>             
            </tr>
            <%--Do du lieu vao table--%>
            <c:forEach var="rows" items="${listKH}">
                <form action="ControllerCustomers"> <%--Luu y: phải dat form trong c:forEach để có thể lấy giá trị từng dòng--%>
                    <tr>
                        <td> <input type="text" name="txtUser" value="${rows.username}" readonly> </td>
                        <td> 
                            <img src="${rows.image}" style="width: 140px; height: 145px;"><br>
                            <input type="file" name="EditImage" style="width: 70%;">
                        </td>
                        <td> <input type="text" name="txtPass" value="${rows.password}"> </td>
                        <td> <input type="text" name="txtHoTen" value="${rows.hoten}"> </td>
                        <td> 
                            <%-- <input type="text" name="txtGioiTinh" value="${rows.gioitinh}"> --%>
                            <select name="Gioitinh">
                                <option value="1" ${rows.gioitinh == '1' ? 'selected' : ''}>Nam</option>
                                <option value="0" ${rows.gioitinh == '0' ? 'selected' : ''}>Nu</option>
                            </select>
                        </td>
                        <td> <input type="text" name="txtEmail" value="${rows.email}"> </td>
                        <td> 
                            <%-- <input type="text" name="txtRole" value="${rows.role}"> --%>
                            <select name="Vaitro">
                                <option value="1" ${rows.role == '1' ? 'selected' : ''}>Customer</option>
                                <option value="0" ${rows.role == '0' ? 'selected' : ''}>Admin</option>
                            </select>
                        </td>
                        <td> 
                            <input type="hidden" name="txtSearch" value="${param.txtTenKH}">
                            <input type="submit" name="action" value="Edit">
                        </td>

                        <c:url var="del" value="ControllerCustomers">
                            <c:param name="action" value="Delete"/>
                            <c:param name="txtDelUser" value="${rows.username}"/>
                            <c:param name="txtSearch" value="${param.txtTenKH}"/>
                        </c:url>
                        <td>
                            <a href="${del}">Delete</a>
                        </td>
                    </tr>
                </form>
            </c:forEach> 
            <%--Them du lieu moi--%>
            <form action="ControllerCustomers">
                <tr>
                    <td>
                        <input type="text" name="txtNewUser" value="" placeholder="Nhap Username">
                    </td>
                    <td>
                        <input type="file" name="NewImage">
                    </td>
                    <td>
                        <input type="text" name="txtNewPass" value="" placeholder="Nhap Password">
                    </td>
                    <td>
                        <input type="text" name="txtNewHoTen" value="" placeholder="Nhap HoTen">
                    </td>
                    <td>
                        <select name="NewGioitinh">
                            <option value="1">Nam</option>
                            <option value="0">Nu</option>
                        </select>
                    </td>
                    <td>
                        <input type="text" name="txtNewEmail" value="" placeholder="Nhap Email">
                    </td>
                    <td>
                        <select name="NewVaitro">
                            <option value="1">Customer</option>
                            <option value="0">Admin</option>
                        </select>
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
