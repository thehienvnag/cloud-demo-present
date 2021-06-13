<%-- 
    Document   : bookStore
    Created on : Mar 22, 2020, 2:15:18 PM
    Author     : KhoaPHD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.ROLE}">
            <font color="red">
                Welcome, ${sessionScope.FULL_NAME}
            </font>
            <a href="logout">Logout</a>
        </c:if>
        
        <h1>Book Store</h1>
        <h3>Database: <c:out value = "${System.getenv('SQLSERVERCONNSTR_AWS_DB')}" /></h3>
        <c:set var="bookList" value="${requestScope.BOOK_LIST}"/>
        <c:if test="${not empty bookList}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Add</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${bookList}" varStatus="counter">
                        <form action="addToCart">
                        <tr>
                            <td>
                                ${counter.count}
                            .</td>
                            <td>
                                ${item}
                                <input type="hidden" name="txtBookTitle" value="${item}" />
                            </td>
                            <td>
                                <input type="submit" value="Add to Cart" />
                            </td>
                        </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty bookList}">
            <h1>No book is available!!!</h1>
        </c:if>
        <form action="viewCart">
            <input type="submit" value="View Cart" />
        </form>
    </body>
</html>
