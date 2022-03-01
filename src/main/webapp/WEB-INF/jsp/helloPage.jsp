<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8" errorPage="error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Welcome</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="prop" var="prop"/>

</head>
<body>
<fmt:message bundle="${prop}" key="Name" var="name"/>
<fmt:message bundle="${prop}" key="Registration" var="registration"/>
<fmt:message bundle="${prop}" key="RussianButton" var="ru_button"/>
<fmt:message bundle="${prop}" key="EnglishButton" var="en_button"/>


<fmt:message bundle="${prop}" key="Login" var="login"/>
<fmt:message bundle="${prop}" key="Password" var="password"/>
<c:set var="url" scope="session" value="${sessionScope.url}"/>



<h2>${login}</h2>
<form action="MyController" method="post">
    <input type="hidden" name="command" value="logination">
    ${login}
    <input type="text" name="login" value=""/>
    <br/>
    ${password}
    <input type="password" name="password" value=""/>
    <br/>
    <input type="submit" value="Press Me"/>
</form>

<br/>

<a href="MyController?command=GO_TO_REGISTRATION_PAGE">${registration}</a>

<form action="MyController" method="post">
    <input type="hidden" name="command" value="GO_TO_REGISTRATION_PAGE">

    <input type="submit" value="${registration}"/>
</form>
<br/>
<form action="MyController" method="post">
    <input type="hidden" name="command" value="ru">
    <input type="submit" value="${ru_button}">
</form>
<form action="MyController" method="post">
    <input type="hidden" name="command" value="en">
    <input type="submit" value="${en_button}">
</form>


</body>
</html>