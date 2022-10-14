console.log("This script is running");


$(document).ready(function() {
    //var container_width = 160 * $(".movie-suggestion .movie-suggestion-item").length;
    var container_width = 160 * 20;

    $(".movie-suggestion").css("width", container_width);
 });

 $(document).ready(function() {
    var container_width = 160 * $(".cast-suggestion .cast-suggestion-item").length;

    $(".cast-suggestion").css("width", container_width);
 });

 function openCity(evt, cityName) {
   var i, x, tablinks;
   x = document.getElementsByClassName("season");
   for (i = 0; i < x.length; i++) {
     x[i].style.display = "none";
   }
   tablinks = document.getElementsByClassName("tablink");
   for (i = 0; i < x.length; i++) {
     tablinks[i].className = tablinks[i].className.replace(" w3-red", "");
   }
   document.getElementById(cityName).style.display = "block";
   evt.currentTarget.className += " w3-red";
 }