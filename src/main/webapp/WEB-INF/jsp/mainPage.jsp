<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8" errorPage="error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>

    <title>Funny page</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="prop" var="prop"/>
    <fmt:message bundle="${prop}" key="RussianButton" var="ru_button"/>
    <fmt:message bundle="${prop}" key="EnglishButton" var="en_button"/>
    <fmt:message bundle="${prop}" key="Exit" var="exit"/>

</head>
<body>



<table>
    <tr>
        <td>Faculties</td>

    </tr>


    <c:forEach var="faculties" items="${requestScope.facultyList}">
        <tr>
            <td><a href="MyController?command=showFacultyInfo&facultyId=${faculties.id}"><c:out value="${faculties.faculty}"/></a></td>

        </tr>
    </c:forEach>
    <c:set var="role" value="${sessionScope.role}"/>
    <c:if test="${not empty role and role eq 'admin'}">
        <a href="MyController?command=GO_TO_REQUEST_LIST_PAGE">GO</a>
    </c:if>
    <a href="MyController?command=exit">${exit}</a>

</table>
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