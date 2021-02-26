function loginAction() {
    document.getElementById("frmRegForg").style.display = "none";
    window.location.reload(false); 
}

function loginOutAction() {
    document.getElementById("frmLogin:lgnUser").hidden = false;
    document.getElementById("frmLogin:lgnPass").hidden = false;
    document.getElementById("frmLogin:lgnActiveUser").hidden = true;
}

function alertSuccess() {
    alert("New password has been sent to your email!");
}

function alertFailure() {
    alert("Wrong username and email combination!");
}


$(document).ready(function () {
    var objForgot = document.getElementById("frmRegForg:lnkFrgPass");
    if (objForgot !== null) {
        objForgot.addEventListener("click", openView);
    }
    function openView() {
        document.getElementById('divForgot').style.display = 'block';
    }

    document.getElementById("btnCancel").addEventListener("click", closeView);
    function closeView() {
        document.getElementById('divForgot').style.display = 'none';
    }
    var frgPass = document.getElementById("divForgot");
    var schDetails = document.getElementById("details");
    window.onclick = function (event) {
        if (event.target === frgPass) {
            frgPass.style.display = "none";
        }
        if (event.target === schDetails) {
            schDetails.style.display = "none";
        }
    };
    
    document.getElementById("new").addEventListener("click", openNew);
    function openNew(){
        document.getElementById('frmDetails:delete').style.display = 'none';
        schDetails.style.display = "block";
        document.getElementById("frmDetails:noteId").value=0;
    }
    document.getElementById("frmDetails:dateFrom").addEventListener("input", convertDateFrom);
    function convertDateFrom(){
         var date=new Date(document.getElementById("frmDetails:dateFrom").value);
         var longFormat = Number(date*1);
         document.getElementById("frmDetails:dateFromVal").value=longFormat;
    }
    
    document.getElementById("frmDetails:dateTo").addEventListener("input", convertDateTo);
    function convertDateTo(){
         var date=new Date(document.getElementById("frmDetails:dateTo").value);
         var longFormat = Number(date*1);
         document.getElementById("frmDetails:dateToVal").value=longFormat;
    }
    
    document.getElementById("color").addEventListener("input", al);
    function al(){
        var newColor=document.getElementById("color").value;
        document.getElementById("frmDetails:colorVal").value=newColor;
    }
});