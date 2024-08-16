<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="mainHeader.label.logOut" var="signOut"/>
<fmt:message bundle="${naming}" key="mainHeader.label.profile" var="profile"/>
<fmt:message bundle="${naming}" key="mainHeader.label.home" var="home"/>
<fmt:message bundle="${naming}" key="mainHeader.label.lang" var="lang"/>
<fmt:message bundle="${naming}" key="mainHeader.label.administrate" var="administrate"/>
<fmt:message bundle="${naming}" key="admin.label.orders" var="orders"/>
<fmt:message bundle="${naming}" key="mainHeader.label.signIn" var="signIn"/>

<html lang="${sessionScope.language}">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/mainHeaderStyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="navigationBar">

    <c:if test="${not empty sessionScope.role}">
        <div class="optionalButton">
            <a href="${pageContext.servletContext.contextPath}/controller?command=signOut">${signOut}</a>
        </div>
    </c:if>

    <div class="dropDown">
        <button class="dropButton-language">${sessionScope.language}
        </button>
        <c:set var="test" value="${pageContext.servletContext.contextPath}"/>
        <div class="dropDownContent-language">
            <a href="${pageContext.servletContext.contextPath}/controller?command=changeLanguage&lang=RU&current${pageContext.request.queryString}">Русский</a>
            <a href="${pageContext.servletContext.contextPath}/controller?command=changeLanguage&lang=EN&current${pageContext.request.queryString}">English</a>
        </div>
    </div>

</div>

</body>
</html>
