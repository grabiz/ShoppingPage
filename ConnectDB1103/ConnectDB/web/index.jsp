<%-- 
    Document   : index
    Created on : Mar 7, 2017, 7:14:21 PM
    Author     : iviettech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% if(session !=null) {
            session.invalidate();
        } %>
        <h1>Home page</h1>
        <a href="show-user">Show user</a><p></p>
    </body>
</html>
