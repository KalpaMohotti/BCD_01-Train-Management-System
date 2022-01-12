
document.getElementById("title").innerHTML = `Welcome to National Train System`;

const tabledata=document.getElementById("table1");


function update() {
    console.log("kalpaaaaa");
}

const wsUri = "ws://localhost:8080/trainAppweb/endpoint";

const websocket = new WebSocket(wsUri);

websocket.onerror = (evt) => {
    console.log(evt);
};

const output = document.getElementById("output");

 
websocket.onopen = function (evt) {
    console.log("open")
};

websocket.onmessage = (evt) => {
    writeToScreen(evt.data)
};

function writeToScreen(message) {
    console.log(message)
    const datasplit=message.split("+");
    datasplit[1];
    var lati=datasplit[1];
    var long=datasplit[2];
    tabledata.innerHTML +="<tr>"+
        "<td>" +datasplit[0]+"</td>"+
        "<td>" +datasplit[1]+"</td>"+
        "<td>" +datasplit[2]+"</td>"+
        "<td>" +datasplit[3]+"Kmph</td>"+
        "<td>" +datasplit[4]+"°C</td>"+
        "<td>" +datasplit[5]+"</td>"+
        "<td><button onclick=\"initMap("+lati+","+long+")\">View Location</button></td>"+
        
         "</tr>";
    
//    output.innerHTML += message + "<br>";

}

function clearScreen() {
    output.innerHTML = "";
}


function initMap(lati,long) {
//      const myLatLng = { lat: 	7.291418, lng: 80.636696 };
       const myLatLng = { lat: 	lati, lng: long };
  const map = new google.maps.Map(document.getElementById("googleMap"), {
    zoom: 10,
    center: myLatLng,
  });
  new google.maps.Marker({
    position: myLatLng,
    map,
    title: "Hello World!",
  });


}




