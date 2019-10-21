function startBoiler(api) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("boilerStatus").innerHTML = this.responseText;
            document.getElementById("startBoilerButton").disabled = true;
        }
    };
    xhttp.open("GET", "http://localhost:8082/api/boiler/start");
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}