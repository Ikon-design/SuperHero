<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="enums.Incident" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/reset.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/main.css">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
          integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A=="
          crossorigin=""/>
    <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"
            integrity="sha512-XQoYMqMTK8LvdxXYG3nZ448hOEQiglfqkJs1NOQV44cWnUrBc8PkAOcXy20w0vlaXaVUearIOBhiXZ5V3ynxwA=="
            crossorigin=""></script>
</head>
<body class="body-style display-flex">
<div id="map"></div>
<form class="form-container" action="${pageContext.request.contextPath}/incidents" method="post">
    <H6 class="full-width-field form-title">SUPER APPEL</H6>
    <div class="full-width-field">
        <label for="name">Votre ville :</label>
        <input type="text" id="name" placeholder="Paris" name="name" oninput="" value="<%=request.getAttribute("name")%>">
    </div>
    <div class="full-width-field">
        <label for="incident">Super incident gérable :</label>
        <select id="incident" name="incident">
            <jstl:forEach var="i" begin="0" end="9">
                <option>${Incident.values()[i]}</option>
            </jstl:forEach>
        </select>
    </div>
    <input type="submit" value="SUPER VALIDATION" class="form-button full-width-field" onclick="">
</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/public/script.js"></script>
</html>
