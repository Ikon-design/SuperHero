<%@ page import="enums.Incident" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core" %>
<% %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/reset.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/main.css">
</head>
<body class="form-body body-style">

<form class="form-container" action="./heroes" method="post">
    <H6 class="full-width-field form-title">SUPER FORMULAIRE DE SUPER INSCRIPTION</H6>
    <div class="name-field full-width-field">
        <label for="name">Super nom :</label>
        <input type="text" id="name" placeholder="Super crote" name="name">
    </div>
    <div class="phone-field full-width-field">
        <label for="phone">Super téléphone :</label>
        <input type="text" id="phone" placeholder="Super crote" name="phone">
    </div>
    <div class="position-field left-position-field half-width-field">
        <label for="longitude">Longitude :</label>
        <input type="text" id="longitude" placeholder="40.203203" name="longitude">
    </div>
    <div class="position-field right-position-field position-field">
        <label for="latitude">Latitude :</label>
        <input type="text" id="latitude" placeholder="40.203203" name="latitude">
    </div>
    <div class="full-width-field first-incident">
        <label for="incident1">Super incident gérable :</label>
        <select id="incident1" name="fIncident">
            <jstl:forEach var="i" begin="0" end="9">
                <option>${Incident.values()[i]}</option>
            </jstl:forEach>
        </select>
    </div>
    <div class="full-width-field second-incident">
        <label for="incident2">Super incident gérable :</label>
        <select id="incident2" name="sIncident">
            <jstl:forEach var="i" begin="0" end="9">
                <option>${Incident.values()[i]}</option>
            </jstl:forEach>
        </select>
    </div>
    <div class="full-width-field third-incident">
        <label for="incident3">Super incident gérable :</label>
        <select id="incident3" name="tIncident">
            <jstl:forEach var="i" begin="0" end="9">
                <option>${Incident.values()[i]}</option>
            </jstl:forEach>
        </select>
    </div>
    <input type="submit" value="SUPER VALIDATION" class="form-button full-width-field">
</form>
</body>
</html>
