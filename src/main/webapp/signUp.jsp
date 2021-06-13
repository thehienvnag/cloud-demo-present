<%-- 
    Document   : signUp
    Created on : Mar 24, 2020, 2:39:08 PM
    Author     : KhoaPHD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        <h1>Sign Up</h1>
        <form action="createNewAccount" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" /> (6 - 30 chars)<br/>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                    ${errors.usernameLengthError}<br/>
                </font>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" /> (6 - 20 chars)<br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                    ${errors.passwordLengthError}<br/>
                </font>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                    ${errors.confirmNotMatched}<br/>
                </font>
            </c:if>
            Full name* <input type="text" name="txtFullName" value="${param.txtFullName}" /> (2 - 50 chars)<br/>
            <c:if test="${not empty errors.fullNameLengthError}">
                <font color="red">
                    ${errors.fullNameLengthError}<br/>
                </font>
            </c:if>
            <input type="submit" value="Create New Account" />
            <input type="reset" value="Reset" />
        </form>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                    ${errors.usernameIsExisted}
                </font>
            </c:if>
    </body>
</html>
