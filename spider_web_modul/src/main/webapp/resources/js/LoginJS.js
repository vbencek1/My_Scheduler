function loginAction() {
    document.getElementById("frmRegForg").style.display = "none";
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
});