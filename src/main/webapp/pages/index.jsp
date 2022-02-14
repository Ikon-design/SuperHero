<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="enums.Incident" %>
<%@ page import="classes.Heroes" %>
<%@ page import="java.util.ArrayList" %>
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
</head>
<body class="body-style" onload="getGeoLock()">
    <div id="map"></div>
    <dialog id="dialog" class="flex-direction-column">
        <h4 class="font-family-raleway">Il semblerait que nous ne trouvons pas votre position</h4>
        <h6 class="font-family-raleway">Merci de renseigner le nom de votre ville.</h6>
        <form class="display-flex flex-direction-column" action="./home" method="post">
            <input type="text" id="name" placeholder="Votre ville" required name="name">
            <input type="submit" onclick="setPosition()">
        </form>
    </dialog>
    <script type="text/javascript" src="${pageContext.request.contextPath}/public/script.js"></script>
    <% ArrayList<Heroes> list = (ArrayList<Heroes>) request.getAttribute("heroes");
        for (Heroes s:list){%>
            <script>
                L.marker([<%=s.latitude%>,<%=s.longitude%>]).bindPopup("<%=s.name%>").addTo(map)
            </script>
        <%}
    %>
</body>
</html>
<!--<a class="button-style submit-button" href="${pageContext.request.contextPath}/heroes">SUPER INSCRIPTION</a>
<a class="button-style alert-button" href="${pageContext.request.contextPath}/incidents">APPELER À <br>LA RESCOUSSE</a>
<button class="button-style alert-button"  onclick="getGeoLock()">Coucou je suis la</button>