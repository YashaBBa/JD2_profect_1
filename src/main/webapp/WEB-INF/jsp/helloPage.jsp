<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8" errorPage="error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>

<h2>Login</h2>
<form action="MyController" method="post">
    <input type="hidden" name="command" value="logination">
    Login:
    <input type="text" name="login" value=""/>
    <br/>
    Password:
    <input type="password" name="password" value=""/>
    <br/>
    <input type="submit" value="Press Me"/>
</form>

<br/>

<a href="MyController?command=GO_TO_REGISTRATION_PAGE">Registraion</a>

<form action="MyController" method="post">
    <input type="hidden" name="command" value="GO_TO_REGISTRATION_PAGE">

    <input type="submit" value="Press Me"/>
</form>
<a href="MyController?command=GO_TO_FUNNY_PAGE">FunnyPage</a>

</body>
</html>