<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8" errorPage="error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Создание специальности</title>
</head>
<body>
<form action="MyController" method="post">
    <input type="hidden" name="command" value="saveNewSpeciality">
    Название специальности
    <input type="text" name="specialityName" value=""/>
    <br/>
    Кол-во льготных мест
    <input type="number" name="specialPlaces" value=""/>
    <br/>
    Общее кол-во мест
    <input type="number" name="allPlaces" value=""/>
    <br/>
    Минимальный проходной
    <input type="number" name="minScore" value=""/>
    <br/>
    Стоимость обучения
    <input type="number" name="cost" value=""/>
    <br/>
    Обязательные предметы ( 1.Математика 2.Физика 3.Руссуий 4.Английский)
    <input type="number" name="firstSub" value=""/>
    <br/>
    <input type="number" name="secondSub" value=""/>
    <br/>
    <input type="number" name="thirdSub" value=""/>
    <br/>
    <input type="hidden" name="facultyID" value="${requestScope.facultyID}">
    <c:set var="facultyID"  scope="request" value="${requestScope.facultyID}"/>
    <input type="submit" value="Press Me"/>
</form>
<a href="MyController?command=GO_TO_MAIN_PAGE">Go Back</a>


</body>
</html>
