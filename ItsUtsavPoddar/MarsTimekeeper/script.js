f = function () {

var d = new Date();
document.getElementById("localTime").innerHTML = d;

var earthTime =  d.toUTCString();
document.getElementById("earthTime").innerHTML = earthTime;

var millis = d.getTime();
document.getElementById("millis").innerHTML = millis.toLocaleString('en-US');
setInterval(f , 10);
}