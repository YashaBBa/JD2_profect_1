
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Data</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="prop" var="prop"/>
    <fmt:message bundle="${prop}" key="Name" var="name"/>
    <fmt:message bundle="${prop}" key="Surname" var="surname"/>
    <fmt:message bundle="${prop}" key="Passport" var="passport"/>
    <fmt:message bundle="${prop}" key="StudyFormat" var="studyFormat"/>
    <fmt:message bundle="${prop}" key="Exit" var="exit"/>
    <fmt:message bundle="${prop}" key="Back" var="back"/>


    <fmt:message bundle="${prop}" key="RussianButton" var="ru_button"/>
    <fmt:message bundle="${prop}" key="EnglishButton" var="en_button"/>
</head>
<body>
<form action="MyController" method="post">
    <input type="hidden" name="command" value="saveApplicantData">
    <input type="hidden" name="changeData" value="true">
    ${name}
    <label>
        <input type="text" name="name" value="${requestScope.applicantData.name}"/>
    </label>
    <br/>
    ${surname}
    <label>
        <input type="text" name="surname" value="${requestScope.applicantData.surname}"/>
    </label>
    <br/>
    ${passport}
    <label>
        <input type="text" name="passport" value="${requestScope.applicantData.passport}"/>
    </label>
    <br/>
    ${studyFormat}
    <label>
        <input type="text" name="studyFormat" value="${requestScope.applicantData.studyFormat}"/>
    </label>

    <br/>
    <input type="submit" value="Chang Data"/>
</form>
<form>
    <input type="hidden" name="command" value="saveApplicantData">
    <input type="hidden" name="changePassword" value="true">
    Old Password
    <label>

        <input type="text" name="password" value=""/>
    </label>
    New Password
    <label>

        <input type="text" name="password1" value=""/>
    </label>
    <input type="submit" value="Chang Password"/>
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
<a href="MyController?command=exit">${exit}</a>
<a href="MyController?command=">${back}</a>
</body>
</html>
