let btn =document.getElementById("btn");
let result=document.getElementById("output");
btn.addEventListener("click",function()
{
    let input1=document.getElementById("userinput1").value;
    let input2=document.getElementById("userinput2").value;
    let input3=document.getElementById("userinput3").value;
    let input4=document.getElementById("userinput4").value;
    if(input1 > Math.pow(input2,input3))
    {
        result.innerHTML="Θ(n^"+Math.log(input1)+")";
    }
    else if((input1 === Math.pow(input2,input3)))
    {
        if(input4 > -1)
        {
        result.innerHTML="Θ(n^"+Math.log(input1)+"log^"+(input4+1)+"n)";
        }
        else if(input4 === -1)
        {
            result.innerHTML="Θ(n^"+Math.log(input1)+"loglogn)";
        }
        else
        {
            result.innerHTML="Θ(n^"+Math.log(input1)+")";
        }
    }
    else 
    {
        if(input4 >= 0)
        {
            result.innerHTML="Θ(n^"+(input3)+"log^"+(input4)+"n)";
        }
        else
        {
            result.innerHTML="Θ(n^"+(input3)+")";
        }
     }
}) ;