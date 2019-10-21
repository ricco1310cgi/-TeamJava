function sendTemperature() {
    var xhttp = new XMLHttpRequest();
    var temperatureInput = document.getElementById("temperatureInput").value;
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 202) {
            document.getElementById("temperatureInput").innerHTML = this.responseText;
        }
    };
    xhttp.open("POST", "http://localhost:8082/api/boiler/temperature/" + temperatureInput + "d", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    console.log(temperatureInput);
}

function postBoilerData() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 202) {
            document.getElementById("demo").innerHTML = this.responseText;
        }
    };
    xhttp.open("POST", "http://localhost:8082/api/boiler/add", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

var myVar = setInterval(myTimer, 2000);

function myTimer() {
    document.getElementById("postData").innerHTML = postBoilerData();
}

function time() {
    var now = new Date();
    return false;
}