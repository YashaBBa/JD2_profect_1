<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 01.02.2022
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Data</title>
</head>
<body>
<form action="MyController" method="post">
    <input type="hidden" name="command" value="saveApplicantData">
    Имя
    <input type="text" name="name" value=""/>
    <br/>
    Фамилия
    <input type="text" name="surname" value=""/>
    <br/>
    Паспортные данные
    <input type="text" name="passport" value=""/>
    <br/>
    Предпочетаемый формат обчуения
    <input type="text" name="studyFormat" value=""/>
    <br/>
    <input type="submit" value="Press Me"/>
</form>
<a href="MyController?command=exit">Exit</a>
<a href="MyController?command=">Go Back</a>
</body>
</html>
