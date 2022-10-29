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
        result.innerHTML="Θ(n<sup>"+Math.log(input1)+"</sup>)";
    }
    else if((input1 === Math.pow(input2,input3)))
    {
        if(input4 > -1)
        {
        result.innerHTML="Θ(n<sup>"+Math.log(input1)+"</sup>log<sup>"+(input4+1)+"</sup>n)";
        }
        else if(input4 === -1)
        {
            result.innerHTML="Θ(n<sup>"+Math.log(input1)+"</sup>loglogn)";
        }
        else
        {
            result.innerHTML="Θ(n<sup>"+Math.log(input1)+"<s/up>)";
        }
    }
    else 
    {
        if(input4 >= 0)
        {
            result.innerHTML="Θ(n<sup>"+(input3)+"</sup>log<sup>"+(input4)+"</sup>n)";
        }
        else
        {
            result.innerHTML="Θ(n<sup>"+(input3)+"</sup>)";
        }
     }
}) ;