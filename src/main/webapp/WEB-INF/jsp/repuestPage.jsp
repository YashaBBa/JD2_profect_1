<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8" errorPage="error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

    <title>Funny page</title>
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
        Средний бал
        <input type="text" name="5" value=""/>
        <input type="submit" value="Отправить"/>
    </form>
</c:if>
<c:if test="${sessionScope.role eq 'admin'}">
    <table>
    <tr>
        <td>Пасспорт</td>
        <td>Фамилия</td>
        <td>Имя</td>



    </tr>
    <tr>
    <c:forEach var="applicants" items="${requestScope.applicantsList}">
        <br/>

        <td><c:out value="${applicants.passport}"/></td>
        <td><c:out value="${applicants.surname}"/></td>
        <td><c:out value="${applicants.name}"/></td>



        </tr>
        </table>


    </c:forEach>

</c:if>
<a href="MyController?command=exit">Exit</a>


</body>
</html>