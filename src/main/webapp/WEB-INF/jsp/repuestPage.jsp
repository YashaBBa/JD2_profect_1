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
    <fmt:message bundle="${prop}" key="MidlScore" var="midScore"/>
    <fmt:message bundle="${prop}" key="Passport" var="passport"/>
    <fmt:message bundle="${prop}" key="Surname" var="surname"/>
    <fmt:message bundle="${prop}" key="Name" var="name"/>
    <fmt:message bundle="${prop}" key="Privileges" var="privileges"/>
    <fmt:message bundle="${prop}" key="Score" var="score"/>

</head>
<body>
<c:if test="${sessionScope.role eq 'user' or sessionScope.role eq 'accepteduser'}">
    <form action="MyController" method="post">

        <input type="hidden" name="command" value="sendRequest">

        <c:forEach var="subjects" items="${requestScope.listOfSubjects}">
            <br/>
            <c:out value="${subjects.subject}"/>

            <input type="text" name="${subjects.subjectID}" value=""/>

        </c:forEach>
        <br/>
        ${midScore}
        <input type="text" name="5" value=""/>
        <input type="submit" value="Отправить"/>
    </form>
</c:if>
<c:if test="${sessionScope.role eq 'admin'}">
    <table>
    <tr>
        <td>${passport}</td>
        <td>${surname}</td>
        <td>${name}</td>
        <td>${privileges}</td>
        <td>${score}</td>



    </tr>
    <tr>
    <c:forEach var="applicants" items="${requestScope.applicantsList}">
        <br/>

        <td><c:out value="${applicants.passport}"/></td>
        <td><c:out value="${applicants.surname}"/></td>
        <td><c:out value="${applicants.name}"/></td>
        <td><c:out value="${applicants.privileges}"/></td>
        <td><c:out value="${applicants.score}"/></td>




        </tr>
        </table>


    </c:forEach>

</c:if>
<a href="MyController?command=exit">Exit</a>
<c:set var="sorts"  scope="request" value="${requestScope.applicantsList}"/>
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