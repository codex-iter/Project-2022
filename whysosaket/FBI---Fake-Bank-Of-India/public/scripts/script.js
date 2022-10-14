
function confirmPassword(){
    let newpin = document.getElementById("newpin").value;

    //check empty password field  
  if(newpin == "") {  
    document.getElementById("message").innerHTML = "**Fill the password please!";  
    return false;  
 }  
  
//minimum password length validation  
 if(newpin.length < 8) {  
    document.getElementById("message").innerHTML = "**Password length must be atleast 8 characters";  
    return false;  
 }  
}