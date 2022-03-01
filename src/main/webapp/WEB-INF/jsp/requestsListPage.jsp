<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8" errorPage="error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Список заявок</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="prop" var="prop"/>
    <fmt:message bundle="${prop}" key="RussianButton" var="ru_button"/>
    <fmt:message bundle="${prop}" key="EnglishButton" var="en_button"/>
    <fmt:message bundle="${prop}" key="Passport" var="passport"/>
    <fmt:message bundle="${prop}" key="Surname" var="surname"/>
    <fmt:message bundle="${prop}" key="Name" var="name"/>
    <fmt:message bundle="${prop}" key="Speciality" var="speciality"/>
    <fmt:message bundle="${prop}" key="Subject" var="subject"/>
    <fmt:message bundle="${prop}" key="Score" var="score"/>
    <fmt:message bundle="${prop}" key="Back" var="back"/>
    <fmt:message bundle="${prop}" key="Exit" var="exit"/>



</head>
<body>
<c:set var="specID" value=""/>
<c:set var="passport" value=""/>

<table>
    <tr>
        <td>${passport}</td>
        <td>${name}</td>
        <td>${surname}</td>
        <td>${speciality}</td>
        <td>${subject}</td>
        <td>${score}</td>
        <td></td>
        <td></td>


    </tr>

    <c:forEach var="requests" items="${requestScope.requestList}">

        <tr>
            <c:if test="${specID != requests.specialityID or passport != requests.applicant.passport}">
                <td><c:out value="${requests.applicant.passport}"/></td>

                <td><c:out value="${requests.applicant.name}"/></td>
                <td><c:out value="${requests.applicant.surname}"/></td>
                <td><c:out value="${requests.speciality}"/></td>



            </c:if>


            <c:if test="${specID eq requests.specialityID and passport eq requests.applicant.passport}">
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </c:if>

            <td><c:out value="${requests.subject}"/></td>

            <td><c:out value="${requests.score}"/></td>
            <c:if test="${specID != requests.specialityID or passport != requests.applicant.passport}">
                <td>
                    <a href="MyController?command=APPLAY_REQUEST&requestID=${requests.requestID}&applicantID=${requests.applicant.applicantId}&specialityID=${requests.specialityID}&midlScore=${requests.score}">Принять</a>
                </td>
                <td>
                    <a href="MyController?command=REJECT_REQUEST&requestID=${requests.requestID}&applicantID=${requests.applicant.applicantId}&specialityID=${requests.specialityID}">Отклонить</a>
                </td>

            </c:if>
            <c:set var="specID" value="${requests.specialityID}"/>
            <c:set var="passport" value="${requests.applicant.passport}"/>


        </tr>
    </c:forEach>

</table>
<a href="MyController?command=exit">${exit}</a>
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
