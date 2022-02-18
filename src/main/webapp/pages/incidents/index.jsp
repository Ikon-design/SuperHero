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
<div class="reset-position display-flex menu">
    <a href="${pageContext.request.contextPath}/resetInformation" onclick="resetPosition()" class="button-style">Changer mes informations</a>
    <a href="${pageContext.request.contextPath}/home" class="button-style">Retour à l'accueil</a>
</div>
<div class="display-flex container-table">
    <table id="heroesTable">
        <tr>
            <th>Super héro</th>
            <th>Téléphone</th>
        </tr>
    </table>
</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/public/script.js"></script>
<% ArrayList<Heroes> list = (ArrayList<Heroes>) request.getAttribute("heroes");
    for (Heroes s : list) {%>
<script>
    hero = L.marker([<%=s.latitude%>, <%=s.longitude%>], {icon: superHeroIcon}).bindPopup("<%=s.name%>")
    distance = L.GeometryUtil.length([userPosition._latlng, hero._latlng])
    if (distance <= 5000) {
        row = table.insertRow();
        heroName = row.insertCell(0);
        heroPhone = row.insertCell(1);
        heroName.innerHTML = '<%=s.name%>';
        heroPhone.innerHTML = '<%=s.phone%>';
        hero.addTo(map)
    }
</script>
<%
    }
%>

</body>
</html>
