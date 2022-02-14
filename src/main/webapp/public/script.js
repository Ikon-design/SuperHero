var dialog = document.getElementById("dialog")
var cityName = document.getElementById("name")
function getGeoLock(){
    if (navigator.geolocation){
        navigator.geolocation.getCurrentPosition(showPosition, showError);
    }else {
        console.log("Geolocation is not supported by this browser.");
    }
}

function showPosition(position){
    console.log(position.coords.latitude)
    console.log("enculé")
}

function showError(){
   // if (sessionStorage.getItem("position") === null){
   //     dialog.style.display = 'flex';
   //     dialog.open = true;
   // }
}

function setPosition(){
    //sessionStorage.setItem("position", cityName.value)
}

var map = L.map('map').setView([48.857235, 2.308396], 13);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

L.marker([48.857235, 2.308396]).addTo(map)
    .bindPopup('Notre QG')
    .openPopup();