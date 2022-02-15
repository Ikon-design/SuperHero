let dialog = document.getElementById("dialog");
let map;
let userPosition;
let latitude;
let longitude;
let distance;
let hero;
let probleme;

if (localStorage.getItem('latitude') === null || localStorage.getItem("longitude") === null) {
    map = L.map('map').setView([48.857235, 2.308396], 13);
    L.marker([48.857235, 2.308396]).addTo(map)
        .bindPopup('Notre QG')
        .openPopup();
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy;OpenStreetMap'
    }).addTo(map);

} else {
    longitude = localStorage.getItem("longitude");
    latitude = localStorage.getItem('latitude');
    map = L.map('map').setView([latitude, longitude], 13);
    userPosition = L.marker([latitude, longitude]).addTo(map)
        .bindPopup('Votre position')
        .openPopup();
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy;OpenStreetMap'
    }).addTo(map);

}

function getGeoLock(lat, lon) {
    if (lat !== 91 && lon !== 91 && longitude === undefined && latitude === undefined) {
        localStorage.setItem("latitude", lat);
        localStorage.setItem("longitude", lon);
        location.reload();
    } else {
        if (navigator.geolocation && localStorage.getItem('latitude') === null || localStorage.getItem("longitude") === null) {
            navigator.geolocation.getCurrentPosition(setLocation, showError);
        }
    }
}

function setLocation(position) {
    localStorage.setItem("latitude", position.coords.latitude);
    localStorage.setItem("longitude", position.coords.longitude);
    location.reload();
}

function showError() {
    if (sessionStorage.getItem("latitude") === null || sessionStorage.getItem("longitude") === null) {
        dialog.style.display = 'flex';
        dialog.open = true;
    }
}

