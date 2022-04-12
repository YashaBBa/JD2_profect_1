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
            <td><a href="MyController?command=showFacultyInfo&facultyId=${faculties.id}"><c:out
                    value="${faculties.faculty}"/></a></td>

        </tr>
    </c:forEach>
    <c:set var="role" value="${sessionScope.role}"/>
    <c:if test="${not empty role and role eq 'admin'}">
        <a href="MyController?command=GO_TO_REQUEST_LIST_PAGE">GO</a>
        <br/>
        <form action="MyController" method="post">
            <input type="hidden" name="command" value="changeDate"/>
            <input type="text" name="date" value=""/>
            <input type="submit" value="Изменить дату"/>
        </form>

    </c:if>
    <a href="MyController?command=exit">${exit}</a>


</table>
<h2>Дата окончания приёма заявок</h2>
<c:set var="message" value="${param.message}"/>
<c:if test="${not empty message and message eq 'error'}">
    <h5>Некоректный ввод</h5>
</c:if>
<h1>${requestScope.date}</h1>
<br/>
<c:if test="${not empty role and role eq 'user'}">
    <form action="MyController" method="post">
        <input type="hidden" name="command" value="GO_TO_APPLICANTSDATA_PAGE">
        <input type="submit" value="Profile Page">
    </form>
</c:if>
<c:if test="${not empty role and role eq 'admin'}">
    <form action="MyController" method="post">
        <input type="hidden" name="command" value="ApplyApplicants">
        <input type="submit" value="Apply">
    </form>
</c:if>

<form action="MyController" method="post">
    <input type="hidden" name="command" value="ru">
    <input type="submit" value="${ru_button}">
</form>
<form action="MyController" method="post">
    <input type="hidden" name="command" value="en">
    <input type="submit" value="${en_button}">
</form>
<br/>
<c:set var="update" value="${param.registrationInfo}"/>
<c:if test="${not empty update and message eq 'ERROR'}">
    <h5>Некоректный ввод</h5>
</c:if>
<c:if test="${not empty update and message eq 'GOOD'}">
    <h5>Данные обновлены</h5>
</c:if>

</body>
</html>