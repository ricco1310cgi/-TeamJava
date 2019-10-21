function check(form) {
    if(form.userid.value == "Royschuiveling@gmail.com" && form.pswrd.value === "123" ||
    form.userid.value == "Jusreiza@gmail.com" && form.pswrd.value === "456" ||
    form.userid.value == "Faruk@gmail.com" && form.pswrd.value === "789" ) {
        window.open();
        //window.location.replace("")
    } else {
        alert("The username and password you entered don't match");
    }
}

function saveToLocalDB(form) {
    var loginRequest = {
        name: form.userid.value,
        password: form.pswrd.value
    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                checkAnswer = JSON.parse(this.responseText);
                alert(this.responseText + " : " + checkAnswer.name + " : " + checkAnswer.token + " + " + checkAnswer.role);
            }
            else if (this.status == 401) {
                alert("Gebruiker bestaat niet");
            }
            else if (this.status == 403) {
                alert("Gebruiker is al ingelogd");
            } else {
                alert("Externe fout, probeer het later opnieuw")
            }
        }
    }
    xhttp.open("POST", "http://localhost:8082/api/auth/signin");
    xhttp.setRequestHeader("Content-type", "application/json");

    xhttp.send(JSON.stringify(loginRequest));
    open("homeScreen.html", "_blank");
}