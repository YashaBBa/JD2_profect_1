<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8" errorPage="error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Список заявок</title>
</head>
<body>
<c:set var="specID" value=""/>
<c:set var="passport" value=""/>

<table>
    <tr>
        <td>Пасспорт</td>
        <td>Имя</td>
        <td>Фамилия</td>
        <td>Специальность</td>
        <td>Предмет</td>
        <td>Балы</td>
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
                    <a href="MyController?command=APPLAY_REQUEST&requestID=${requests.requestID}&applicantID=${requests.applicant.applicant_id}&specialityID=${requests.specialityID}&midlScore=${requests.score}">Принять</a>
                </td>
                <td>
                    <a href="MyController?command=REJECT_REQUEST&requestID=${requests.requestID}&applicantID=${requests.applicant.applicant_id}&specialityID=${requests.specialityID}">Отклонить</a>
                </td>

            </c:if>
            <c:set var="specID" value="${requests.specialityID}"/>
            <c:set var="passport" value="${requests.applicant.passport}"/>


        </tr>
    </c:forEach>

</table>
<a href="MyController?command=exit">Exit</a>
<a href="MyController?command=GO_TO_MAIN_PAGE">Back</a>
</body>
</html>
