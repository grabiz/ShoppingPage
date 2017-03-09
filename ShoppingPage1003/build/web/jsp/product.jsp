<%-- 
    Document   : product
    Created on : Mar 5, 2017, 11:09:25 AM
    Author     : NGUYEN
--%>

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
        <h1>Manga List!</h1>
        
            <table>
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Product Price</th>
                    <th>Product Description</th>
                    <th>Add</th>
                </tr>

                <c:forEach var="product" items="${sessionScope.prodList}">
                    <tr>
                        <td> ${product.prodID}</td>
                        <td>${product.prodName} </td>
                        <td>${product.prodPrice} </td>
                        <td>${product.prodDescription} </td>
                        <td class="lastColumn"><form action="add" method="post">
                                <input type="hidden" name="getValue" value="${product.prodID}"/>
                                <input type="submit" id="submit" value="Add to cart"></form> </td>
                    </tr>
                </c:forEach>
            </table>
    </body>
</html>
