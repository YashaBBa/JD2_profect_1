<%@ page language="java"
         import = "com.tc.webapp01.entity.Greeting" 
         contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8" errorPage="error.jsp"%>

<html>
<head>

<title>Insert title here</title>
</head>
<body>


 <h2>Registration</h2>
	<form action="MyController" method="post">
	    <input type="hidden"  name="command" value="registration">
	    Логин
		<input type="text" name="login" value="" />
		<br/>
		Пароль
	    <input type="password" name="password" value="" />
		<br/>
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
		<input type="submit" value="Press Me" />
	</form>
	
	

<a href="MyController?command=exit">Back</a>
</body>
</html>