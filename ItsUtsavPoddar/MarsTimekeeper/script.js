var tai_offset = 37;

function h_to_hms(h) {
    var x = h * 3600;
    var hh = Math.floor(x / 3600);
    if (hh < 10) hh = "0" + hh;
    var y = x % 3600;
    var mm = Math.floor(y / 60);
    if (mm < 10) mm = "0" + mm;
    var ss = Math.round(y % 60);
    if (ss < 10) ss = "0" + ss;
    return hh + ":" + mm + ":" + ss;
}

function add_commas(n) {
    n += "";
    var x = n.split(".");
    var x1 = x[0];
    var x2 = x.length > 1 ? "." + x[1] : "";
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, "$1" + "," + "$2");
    }
    return x1 + x2;
}

f = function () {

var d = new Date();
document.getElementById("localTime").innerHTML = d;

var earthTime =  d.toUTCString();
document.getElementById("earthTime").innerHTML = earthTime;

var millis = d.getTime();
document.getElementById("millis").innerHTML = add_commas(millis);

var jd_ut = 2440587.5 + (millis / 8.64E7);
jd_ut= jd_ut.toFixed(5);
document.getElementById("jd_ut").innerHTML = add_commas(jd_ut);

var jd_tt = parseFloat(jd_ut) + (tai_offset + 32.184) / 86400;
jd_tt= jd_tt.toFixed(5);
document.getElementById("jd_tt").innerHTML = add_commas(jd_tt);

var j2000 = jd_tt - 2451545.0;
document.getElementById("j2000").innerHTML = add_commas(j2000.toFixed(5));


var msd = (((j2000 - 4.5) / 1.027491252) + 44796.0 - 0.00096);
document.getElementById("msd").innerHTML = add_commas(msd.toFixed(5));

var mtc = (24 * msd) % 24;
document.getElementById("mtc").innerHTML = h_to_hms(mtc)

// console.log(h_to_hms(mtc));

setInterval(f , 10);
}