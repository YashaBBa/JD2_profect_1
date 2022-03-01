<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8" errorPage="error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Создание специальности</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="prop" var="prop"/>


    <fmt:message bundle="${prop}" key="RussianButton" var="ru_button"/>
    <fmt:message bundle="${prop}" key="EnglishButton" var="en_button"/>

    <fmt:message bundle="${prop}" key="Speciality" var="speciality"/>
    <fmt:message bundle="${prop}" key="PreferentialPlaces" var="prefPlaces"/>
    <fmt:message bundle="${prop}" key="Places" var="places"/>
    <fmt:message bundle="${prop}" key="MinScore" var="minScore"/>
    <fmt:message bundle="${prop}" key="Cost" var="cost"/>
    <fmt:message bundle="${prop}" key="SpecialSubjects" var="specSubjects"/>
    <fmt:message bundle="${prop}" key="Back" var="back"/>



</head>
<body>
<form action="MyController" method="post">
    <input type="hidden" name="command" value="saveNewSpeciality">
    ${speciality}
    <input type="text" name="specialityName" value=""/>
    <br/>
    ${prefPlaces}
    <input type="number" name="specialPlaces" value=""/>
    <br/>
    ${places}
    <input type="number" name="allPlaces" value=""/>
    <br/>
    ${minScore}
    <input type="number" name="minScore" value=""/>
    <br/>
    ${cost}
    <input type="number" name="cost" value=""/>
    <br/>
    ${specSubjects}
    <input type="number" name="firstSub" value=""/>
    <br/>
    <input type="number" name="secondSub" value=""/>
    <br/>
    <input type="number" name="thirdSub" value=""/>
    <br/>
    <input type="hidden" name="facultyID" value="${requestScope.facultyID}">
    <c:set var="facultyID"  scope="request" value="${requestScope.facultyID}"/>
    <input type="submit" value="Create"/>
</form>
<br/>
<form action="MyController" method="post">
    <input type="hidden" name="command" value="ru">
    <input type="submit" value="${ru_button}">
</form>
<form action="MyController" method="post">
    <input type="hidden" name="command" value="en">
    <input type="submit" value="${en_button}">
</form>
<a href="MyController?command=GO_TO_MAIN_PAGE">${back}</a>


</body>
</html>
