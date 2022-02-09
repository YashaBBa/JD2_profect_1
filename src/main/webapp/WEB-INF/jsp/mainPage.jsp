<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8" errorPage="error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

    <title>Funny page</title>
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
    <a href="MyController?command=exit">Exit</a>

</table>
</body>
</html>