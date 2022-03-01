<%@ page language="java"
         import = "com.tc.webapp01.entity.Greeting" 
         contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8" errorPage="error.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>

<title>Insert title here</title>
	<fmt:setLocale value="${sessionScope.locale}"/>
	<fmt:setBundle basename="prop" var="prop"/>
	<fmt:message bundle="${prop}" key="RussianButton" var="ru_button"/>
	<fmt:message bundle="${prop}" key="EnglishButton" var="en_button"/>
	<fmt:message bundle="${prop}" key="Registration" var="registration"/>
	<fmt:message bundle="${prop}" key="Login" var="login"/>
	<fmt:message bundle="${prop}" key="Password" var="password"/>
	<fmt:message bundle="${prop}" key="Name" var="name"/>
	<fmt:message bundle="${prop}" key="Surname" var="surname"/>
	<fmt:message bundle="${prop}" key="Passport" var="passport"/>
	<fmt:message bundle="${prop}" key="Privileges" var="privileges"/>
	<fmt:message bundle="${prop}" key="StudyFormat" var="studyFormat"/>
	<fmt:message bundle="${prop}" key="Back" var="back"/>


</head>
<body>


 <h2>${registration}</h2>
	<form action="MyController" method="post">
	    <input type="hidden"  name="command" value="registration">
		${login}
		<input type="text" name="login" value="" />
		<br/>
		${password}
	    <input type="password" name="password" value="" />
		<br/>
		${name}
		<input type="text" name="name" value=""/>
		<br/>
		${surname}
		<input type="text" name="surname" value=""/>
		<br/>
		${passport}
		<input type="text" name="passport" value=""/>
		<br/>
		${studyFormat}
		<input type="text" name="studyFormat" value=""/>
		<br/>
		${privileges}
		<input type="text" name="privileges" value=""/>
		<br/>
		<input type="submit" value="Finish" />
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
	

<a href="MyController?command=exit">${back}</a>
</body>
</html>