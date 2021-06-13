<%-- 
    Document   : viewCart
    Created on : Mar 22, 2020, 6:24:34 PM
    Author     : KhoaPHD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.ROLE}">
            <font color="red">
                Welcome, ${sessionScope.FULL_NAME}
            </font>
            <a href="logout">Logout</a>
        </c:if>
        
        <h1>View your Cart</h1>
        <c:if test="${not empty sessionScope}">
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${not empty cart}">
                <c:set var="items" value="${cart.items}"/>
                <c:if test="${not empty items}">
                    <form action="cart" method="POST">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Title</th>
                                <th>Quantity</th>
                                <th>Update</th>
                                <th>Selected</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${items}" varStatus="counter">
                                <tr>
                                    <td>
                                        ${counter.count}
                                    .</td>
                                    <td>
                                        ${item.key}
                                        <input type="hidden" name="txtBookTitle" value="${item.key}" />
                                    </td>
                                    <td>
                                        ${item.value}
                                    </td>
                                    <td>
                                        <input type="radio" name="rbUpdate" value="${item.key}" />
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkItem" value="${item.key}" />
                                    </td>
                                </tr>
                            </c:forEach>
                                <tr>
                                    <td colspan="3">
                                        <a href="shopping">Add More Books to Cart</a>
                                    </td>
                                    <td>
                                        <input type="text" name="txtQuantity" value="" size="3" />
                                        <input type="submit" value="Update Quantity" name="btnAction"/>
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove Selected" name="btnAction"/>
                                    </td>
                                </tr>
                        </tbody>
                    </table>
                    </form>
                    <a href="checkout">Checkout Cart</a><br/>
                    <c:set var="list" value="${requestScope.NOT_AVAILABLE_BOOK_LIST}"/>
                    <c:if test="${not empty list}">
                        <font color="red">
                            The following books are out of stock:<br/>
                        </font>
                        <c:forEach var="title" items="${list}" varStatus="counter">
                            ${counter.count}. ${title}<br/>
                        </c:forEach>
                    </c:if>
                </c:if>
            </c:if>
        </c:if>
        <c:if test="${empty items}">
            <h2>
                <font color="red">No cart is existed!!!</font>
            </h2>
            <a href="shopping">Shopping Books Now!!!</a>
        </c:if>
    </body>
</html>
