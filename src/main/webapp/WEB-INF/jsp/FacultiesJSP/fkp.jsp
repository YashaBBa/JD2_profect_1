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
    <c:set var="deadlineTime" value='${requestScope.deadlineTime}'/>

    <c:forEach var="specialties" items="${requestScope.specialties}">
        <tr>
            <c:if test="${deadlineTime eq 'true'}">
                <td><a href="MyController?command=GO_TO_REQUEST_PAGE&specialityID=${specialties.id}"><c:out
                        value="${specialties.speciality}"/></a></td>
            </c:if>
            <c:if test="${deadlineTime eq 'false'}">
                <td><c:out value="${specialties.speciality}"/></td>
            </c:if>
            <c:if test="${sessionScope.role eq 'user'}">
                <td><c:out value="${specialties.score}"/></td>
                <td><c:out value="${specialties.properties.places}"/></td>
                <td><c:out value="${specialties.properties.priferentPlacec}"/></td>
                <td><c:out value="${specialties.properties.cost}"/></td>

            </c:if>
            <c:if test="${sessionScope.role eq 'admin'}">
            <form action="MyController" method="post">
                <input type="hidden" name="command" value="redactSpeciality">
                <input type="hidden" name="specID" value="${specialties.id}">
                <label>
                    <td><input type="text" name="score" value="${specialties.score}"></td>
                </label>
                <label>
                    <td><input type="text" name="places" value="${specialties.properties.places}"></td>
                </label>
                <label>
                    <td><input type="text" name="prefPlaces" value="${specialties.properties.priferentPlacec}"></td>
                </label>
                <label>
                    <td><input type="text" name="cost" value="${specialties.properties.cost}"></td>
                </label>
                <input type="hidden" name="update" value="true">
                <td><input type="submit" value="Update"></td>


            </form>
            <td>
                <form action="MyController" method="post">
                    <input type="hidden" name="command" value="redactSpeciality">
                    <input type="hidden" name="sp" value="${specialties.id}">
                    <input type="hidden" name="delete" value="true}">
                    <input type="submit" value="Delete">

                </form>
                </c:if>

            </td>


        </tr>
    </c:forEach>

</table>
<c:if test="${sessionScope.role eq 'admin'}">
    <td><a href="MyController?command=GO_TO_SAVE_NEW_SPECIALITY_PAGE&facultyID=${requestScope.facultyId}">${create}</a>
    </td>

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
