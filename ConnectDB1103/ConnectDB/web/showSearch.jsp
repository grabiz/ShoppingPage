<%-- 
    Document   : showSearch
    Created on : Mar 9, 2017, 8:10:16 PM
    Author     : iviettech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="n" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <h1>Search Result</h1>
        <table>
            <tr>
                <th>id</th>
                <th>first name</th>
                <th>last name</th>
                <th>email</th>
            </tr>
            <n:forEach var="us" items="${sessionScope.userListSearch}">
                <tr>
                     <td>${us.userID}</td>
                     <td>${us.fn}</td>
                     <td>${us.ln}</td>
                     <td>${us.email}</td>
                </tr>
            </n:forEach>
        </table>
    </body>
</html>
