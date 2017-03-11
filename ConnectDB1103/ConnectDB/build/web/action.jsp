<%-- 
    Document   : insertuser
    Created on : Mar 8, 2017, 8:00:41 PM
    Author     : NGUYEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <h1>Insert new user</h1>
        <form action="processing" method="post">
            ID:<input type="number" name="id" value="1" min="1"> <span style="color:red;">${message1}</span> <p>
            First Name : <input type="text" name="firstname" > <span style="color:red;">${message2}</span> <p></p>
            Last Name :<input type="text" name="lastname" > <span style="color:red;">${message3}</span><p></p>
            Email:<input type="email" name="email"><p></p>
            
            <input type="submit"  name="action" value="Insert"> 
            <input type="submit" name="action" value="Update">
            <input type="submit" name="action" value="Delete"><p></p>
            Search:<input type="text" name="search"> <input type="submit" name="action" value="Search">
            <p style="color:red;">${message}</p>
        </form>
    </body>
</html>
