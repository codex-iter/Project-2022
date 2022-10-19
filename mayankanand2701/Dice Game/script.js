
var randomNumber1=Math.floor(1+6*Math.random());
var vab="images/dice"+randomNumber1+".png";
var image1=document.querySelectorAll("img")[0];
image1.setAttribute("src",vab);

var randomNumber2=Math.floor(1+6*Math.random());
var va="images/dice"+randomNumber2+".png";
var image2=document.querySelectorAll("img")[1];
image2.setAttribute("src",va);

if(randomNumber1>randomNumber2)
{
    document.querySelector("h1").innerHTML="🚩 Player 1 Wins";
}
else if(randomNumber1<randomNumber2)
{
    document.querySelector("h1").innerHTML=" Player 2 Wins 🚩";
}
else if(randomNumber1==randomNumber2)
{
    document.querySelector("h1").innerHTML="🚩 Draw! 🚩";
}