function showConfForm() {
    var x = document.getElementById("codeConfirmation");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}
$(document).ready(function () {
    document.getElementById("form1:email").addEventListener("input", checkEmail);
    var correctEmail=false;
    var correctUsername=false;
    var correctPassword=false;
    function checkEmail() {
        var element = document.getElementById("form1:email");
        var regex = /(.+)@(.+){2,}\.(.+){2,}/;
        var match = regex.test(element.value);
        if (match) {
            element.style.borderColor = "green";
            correctEmail=true;
        } else {
            element.style.borderColor = "red";
            correctEmail=false;
        }
    }
    document.getElementById("form1:username").addEventListener("input", checkUsername);
    function checkUsername() {
        var element = document.getElementById("form1:username");
        if (element.value.length >3 && element.value.length <16) {
            element.style.borderColor = "green";
            correctUsername=true;
        } else {
            element.style.borderColor = "red";
            correctUsername=false;
        }
    }
    document.getElementById("form1:password").addEventListener("input", checkPassword);
    function checkPassword() {
        var element = document.getElementById("form1:password");
        if (element.value.length >4 && element.value.length <20) {
            element.style.borderColor = "green";
            correctPassword=true;
        } else {
            element.style.borderColor = "red";
            correctPassword=false;
        }
    }
    document.getElementById("form1:btnRegister").addEventListener("mouseover", checkText);
    document.getElementById("form1:btnRegister").addEventListener("click", disableBtn);
    document.getElementById("form1:btnRegister").disabled=true;
    function checkText(){
        if(correctEmail && correctPassword && correctUsername){
            document.getElementById("form1:btnRegister").disabled=false;
        }else{
            document.getElementById("form1:btnRegister").disabled=true;
        }
    }
    function disableBtn(){
        document.getElementById("form1:btnRegister").disabled=true;
        correctEmail=false;
        correctPassword=false;
        correctUsername=false;
        document.getElementById("form1:username").disabled=true;
        document.getElementById("form1:email").disabled=true;
        document.getElementById("form1:password").disabled=true;
    }
});
