function check(form) {
    if (form.userid.value == "Royschuiveling@gmail.com" && form.pswrd.value === "123" ||
        form.userid.value == "Jusreiza@gmail.com" && form.pswrd.value === "456" ||
        form.userid.value == "Faruk@gmail.com" && form.pswrd.value === "789") {
        window.open();
        //window.location.replace("")
    } else {
        alert("The username and password you entered don't match");
    }
}

function saveToLocalDB(form) {
    var loginRequest = {
        username: form.userid.value,
        password: form.pswrd.value
    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                checkAnswer = JSON.parse(this.responseText);
                localStorage.setItem("username", checkAnswer.username);
                localStorage.setItem("token", checkAnswer.authToken);
                localStorage.setItem("role", checkAnswer.role);
                if (checkAnswer.role == "user") {
                    startBoiler("user");
                } else if(checkAnswer.role == "administratie") {
                    startBoiler("administratie");
                } else {
                    startBoiler("manager");
                }
            }
            else if (this.status == 401) {
                alert("Gebruiker bestaat niet");
            }
            else if (this.status == 403) {
                alert("Gebruiker is al ingelogd");
            }
        }
    }
    xhttp.open("POST", "http://localhost:8082/api/auth/signin");
    xhttp.setRequestHeader("Content-type", "application/json");
    console.log(loginRequest);
    console.log(JSON.stringify(loginRequest));
    xhttp.send(JSON.stringify(loginRequest));

}