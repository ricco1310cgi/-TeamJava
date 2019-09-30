function getData(api) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("demo").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "http://localhost:8082/api/boiler");
    xhttp.setRequestHeader("Content-type", "application/json");
    
    xhttp.send();
    console.log('jojo');
}