<%-- 
    Document   : showListUser
    Created on : Mar 7, 2017, 7:47:32 PM
    Author     : iviettech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="n"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Show List User</h1>
        <table>
            <tr>
                <th>id</th>
                <th>first name</th>
                <th>last name</th>
                <th>email</th>
            </tr>
            <n:forEach var="user" items="${sessionScope.userList}">
                <tr>
                     <td>${user.userID}</td>
                     <td>${user.fn}</td>
                     <td>${user.ln}</td>
                     <td>${user.email}</td>
                </tr>
            </n:forEach>
        </table><p></p>
        <a href="action.jsp" style="text-decoration: none;"><input type="button" value="Insert" /></a>
    </body>
</html>
