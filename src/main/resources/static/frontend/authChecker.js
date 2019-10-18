$(document).ready(function () {
    alert("Hallo!");

    if (checkIfLocalstorageExists()) {
        console.log("Er is data")        
    } else {
        console.log("Nog geen data");
        localStorage.setItem("name", "a");
        localStorage.setItem("role", "a");
        localStorage.setItem("token", "a");
    }

    var checkRequest = {
        name:localStorage.getItem("name"),
        role:localStorage.getItem("role"),
        token:localStorage.getItem("token")
    }

    var checkAnswer = {
        name:"",
        token:"",
        role:""
    }

    console.log(checkRequest);
    console.log(checkRequest.toString());


    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            checkAnswer = JSON.parse(this.responseText);
            alert(this.responseText + " : " + checkAnswer.name + " : " + checkAnswer.token + " + " + checkAnswer.role);
        }
        if (this.readyState == 4 && this.status == 404) {
            alert("Gebruiker niet ingelogd");
        }
    }
    xhttp.open("POST", "http://localhost:8082/api/auth/checkloginstate");
    xhttp.setRequestHeader("Content-type", "application/json");

    xhttp.send(JSON.stringify(checkRequest));


});

function checkIfLocalstorageExists() {
    return localStorage.getItem("name") != null && localStorage.getItem("name") != ""
        && localStorage.getItem("token") != null && localStorage.getItem("token") != ""
        && localStorage.getItem("role") != null && localStorage.getItem("role") != ""
}