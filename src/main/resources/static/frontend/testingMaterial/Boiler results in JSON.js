function getData() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("demo").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "http://localhost:8082/api/boilerDesc");
    xhttp.setRequestHeader("Content-type", "application/json");

    xhttp.send();
    console.log('jojo');
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
    document.getElementById("getData").innerHTML = getData();
    document.getElementById("postData").innerHTML = postBoilerData();
}