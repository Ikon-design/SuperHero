<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="enums.Incident" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/reset.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/main.css">
</head>
<body class="body-style">
<form class="form-container" action="./incidents" method="post">
    <H6 class="full-width-field form-title">SUPER APPEL</H6>
    <div class="full-width-field">
        <label for="name">Nom :</label>
        <input type="text" id="name" placeholder="Paris" name="name" oninput="setCity()">
    </div>
    <div class="full-width-field">
        <label for="incident">Super incident gérable :</label>
        <select id="incident">
            <jstl:forEach var="i" begin="0" end="9">
                <option>${Incident.values()[i]}</option>
            </jstl:forEach>
        </select>
    </div>
    <input type="submit" value="SUPER VALIDATION" class="form-button full-width-field" onclick="setPosition()">
</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/public/script.js"></script>
</html>
