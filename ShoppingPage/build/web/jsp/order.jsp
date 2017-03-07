<%-- 
    Document   : order
    Created on : Mar 5, 2017, 11:09:09 AM
    Author     : NGUYEN
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <h1>Your cart</h1>  
        <form action="orderProcessing" method="post">
        <table>
                <tr>
                    <th>Remove</th>
                    <th>Product Name</th>
                    <th>Product Price</th>
                    <th>Product Description</th>
                    <th>Quantity</th>
                </tr>
          
                <c:forEach var="product" items="${sessionScope.prodAddedList}">
                            <tr>
                                <td><input type="checkbox" name="checkbox" value="0"></td>
                                <td>${product.prodName} </td>
                                <td>${product.prodPrice} </td>
                                <td>${product.prodDescription} </td>
                                <td><input type="number" name="quantity" ></td>
                            </tr>
                </c:forEach>
            </table>
            <input type="submit" name="action" value="Update cart">
            <input type="submit" name="action" value="Remove product">
            <input type="submit" name="action" value="Continue shopping">
            <input type="submit" name="action" value="Checkout">
        </form>
    </body>
</html>
