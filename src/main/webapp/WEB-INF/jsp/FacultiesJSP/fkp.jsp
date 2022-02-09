
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td>Speciality</td>
        <td>Score</td>
        <td>Places</td>
        <td>Preferential places</td>
        <td>Cost</td>



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
    <h1>Чтобы оформить заявку выберите одну из специальностей</h1>

</table>
<c:if test="${sessionScope.role eq 'admin'}">
    <td><a href="MyController?command=GO_TO_SAVE_NEW_SPECIALITY_PAGE&facultyID=${requestScope.facultyID}">Создать новую специальность</a></td>

</c:if>
<a href="MyController?command=GO_TO_MAIN_PAGE">Go Back</a>
</body>
</html>
