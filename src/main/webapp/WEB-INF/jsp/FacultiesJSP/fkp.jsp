
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Title</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="prop" var="prop"/>
    <fmt:message bundle="${prop}" key="RussianButton" var="ru_button"/>
    <fmt:message bundle="${prop}" key="EnglishButton" var="en_button"/>
    <fmt:message bundle="${prop}" key="Back" var="back"/>
    <fmt:message bundle="${prop}" key="Exit" var="exit"/>
    <fmt:message bundle="${prop}" key="Speciality" var="speciality"/>
    <fmt:message bundle="${prop}" key="Score" var="score"/>
    <fmt:message bundle="${prop}" key="Places" var="places"/>
    <fmt:message bundle="${prop}" key="PreferentialPlaces" var="prefPlaces"/>
    <fmt:message bundle="${prop}" key="Cost" var="cost"/>
    <fmt:message bundle="${prop}" key="Create" var="create"/>
</head>
<body>
<table>
    <tr>
        <td>${speciality}</td>
        <td>${score}</td>
        <td>${places}</td>
        <td>${prefPlaces}</td>
        <td>${cost}</td>



    </tr>


    <c:forEach var="specialties" items="${requestScope.specialties}">
        <tr>

                <td><a href="MyController?command=GO_TO_REQUEST_PAGE&specialityID=${specialties.id}"><c:out value="${specialties.speciality}"/></a></td>
                <td><c:out value="${specialties.score}"/></td>
                <td><c:out value="${specialties.properties.places}"/></td>
                <td><c:out value="${specialties.properties.priferentPlacec}"/></td>
                <td><c:out value="${specialties.properties.cost}"/></td>



        </tr>
    </c:forEach>

</table>
<c:if test="${sessionScope.role eq 'admin'}">
    <td><a href="MyController?command=GO_TO_SAVE_NEW_SPECIALITY_PAGE&facultyID=${requestScope.facultyID}">${create}</a></td>

</c:if>
<a href="MyController?command=GO_TO_MAIN_PAGE">${back}</a>
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
