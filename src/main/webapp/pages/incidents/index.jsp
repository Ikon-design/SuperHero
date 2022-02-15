<%@ page import="classes.UserInformation" %>
<%@ page import="classes.Heroes" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>SUPER ACCUEIL</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/reset.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/main.css">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
          integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A=="
          crossorigin=""/>
    <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"
            integrity="sha512-XQoYMqMTK8LvdxXYG3nZ448hOEQiglfqkJs1NOQV44cWnUrBc8PkAOcXy20w0vlaXaVUearIOBhiXZ5V3ynxwA=="
            crossorigin=""></script>
    <script src="https://npmcdn.com/leaflet-geometryutil"></script>
</head>
<body class="body-style display-flex"
      onload="getGeoLock(<%=UserInformation.latitude%>,<%=UserInformation.longitude %>)">
<div id="map"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/public/script.js"></script>
<% ArrayList<Heroes> list = (ArrayList<Heroes>) request.getAttribute("heroes");
    for (Heroes s : list) {%>
<script>
    hero = L.marker([<%=s.latitude%>, <%=s.longitude%>]).bindPopup("<%=s.name%>")
    distance = L.GeometryUtil.length([userPosition._latlng, hero._latlng])
    probleme = <%=UserInformation.userProbleme%>
    if (distance <= 5000 && <%=s.fIncident%> === probleme || <%=s.sIncident%> === probleme || <%=s.tIncident%> === probleme) {
        hero.addTo(map)
    }
</script>
<%
    }
%>

</body>
</html>
