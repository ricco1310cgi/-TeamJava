$(document).ready(function () {
    //alert("Hallo!");

    if (checkIfLocalstorageExists()) {
        console.log("Er is data")        
    } else {
        console.log("Nog geen data");
        localStorage.setItem("username", "a");
        localStorage.setItem("role", "a");
        localStorage.setItem("token", "a");
    }

    var checkRequest = {
        username:localStorage.getItem("username"),
        role:localStorage.getItem("role"),
        token:localStorage.getItem("token")
    }

    var checkAnswer = {
        username:"",
        token:"",
        role:""
    }

    console.log(checkRequest);
    console.log(JSON.stringify(checkRequest));


    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                checkAnswer = JSON.parse(this.responseText);
                if (document.title == "Overzicht") {
                    if (checkAnswer.role == "user") {
                        open("thermometer.html", "_self");
                    }
                }

                if (document.title == "Administratie SBP") {
                    if (checkAnswer.role == "user") {
                        open("thermometer.html", "_self");
                    }
                }
                //alert(this.responseText + " : " + checkAnswer.username + " : " + checkAnswer.token + " + " + checkAnswer.role);
            }
            else if (this.status == 404) {
                alert("Gebruiker niet ingelogd");
                open("loginpage.html", "_self")
            }
            else {
                //alert("Externe foutmelding");
            }
        }
    }
    xhttp.open("POST", "http://localhost:8082/api/auth/checkloginstate");
    xhttp.setRequestHeader("Content-type", "application/json");

    xhttp.send(JSON.stringify(checkRequest));


});

function checkIfLocalstorageExists() {
    return localStorage.getItem("username") != null && localStorage.getItem("username") != ""
        && localStorage.getItem("token") != null && localStorage.getItem("token") != ""
        && localStorage.getItem("role") != null && localStorage.getItem("role") != ""
}

function logout() {
    var logoutRequest = {
        username:localStorage.getItem("username"),
        role:localStorage.getItem("role"),
        token:localStorage.getItem("token")
    }

    localStorage.setItem("username", "a");
    localStorage.setItem("role", "a");
    localStorage.setItem("token", "a");
    

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                checkAnswer = JSON.parse(this.responseText);
                open("loginpage.html", "_self")
                //alert(this.responseText + " : " + checkAnswer.username + " : " + checkAnswer.token + " + " + checkAnswer.role);
            }
            else if (this.status == 404) {
                alert("Gebruiker niet ingelogd");
                open("loginpage.html", "_self")
            }
            else {
                //alert("Externe foutmelding");
            }
        }
    }
    xhttp.open("POST", "http://localhost:8082/api/auth/signout");
    xhttp.setRequestHeader("Content-type", "application/json");

    xhttp.send(JSON.stringify(logoutRequest));
}
