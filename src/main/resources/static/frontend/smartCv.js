var serverBoilerArray = new Array;

var serverBoiler = {
    "id": -1,
    "boilerPressure": -1,
    "tempInside": -1.1,
    "tempOutside": -1.1,
    "timeMovementRecord": -12,
    "gasUsage": -1.1,
    "timeRecorder": -10000,
    "doorClosed": false
}

//var date = new Date();

function getData(api) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            serverBoilerArray = JSON.parse(this.responseText);           
            serverBoiler = JSON.parse(this.responseText);
            document.getElementById("overzichtBinnenTemp").innerHTML = serverBoiler[serverBoilerArray.length - 1].tempInside;
            document.getElementById("overzichtBoilerDruk").innerHTML = serverBoiler[serverBoilerArray.length - 1].boilerPressure;
            document.getElementById("overzichtBuitenTemp").innerHTML = serverBoiler[serverBoilerArray.length-1].tempOutside;
            var date = new Date(serverBoiler[serverBoilerArray.length-1].timeMovementRecord * 1000);
            document.getElementById("overzichtLaatstThuis").innerHTML = date.toLocaleString();
            document.getElementById("overzichtGasGebruik").innerHTML = serverBoiler[serverBoilerArray.length-1].gasUsage;
            document.getElementById("overzichtDeurStatus").innerHTML = serverBoiler[serverBoilerArray.length-1].doorClosed;
            date = new Date(serverBoiler[serverBoilerArray.length-1].timeRecorder * 1000);
            document.getElementById("overzichtBoilerTijd").innerHTML = date.toLocaleString();
            document.getElementById("demo").innerHTML = this.responseText;
            
        }
    };
    xhttp.open("GET", "http://localhost:8082/api/boiler");
    xhttp.setRequestHeader("Content-type", "application/json");

    xhttp.send();
    console.log('jojo');
}

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
    console.log('nono');
}

function getAverageTemp(api) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            serverBoilerArray = JSON.parse(this.responseText);           
            serverBoiler = JSON.parse(this.responseText);

            var barChart = document.getElementById("barChart");

            barChart.width = 300;
            barChart.height = 400;
  
            var ctx = barChart.getContext("2d");

            ctx.clearRect(0, 0, barChart.width, barChart.height);

            for (var i = 0; i < 2; i++) {
                console.log(serverBoiler[i]);
                console.log(serverBoiler[i] == typeof Number)
                    ctx.save();
                    ctx.fillRect(i * 30, barChart.height, 20, -serverBoiler[i]*10);
                    ctx.restore();                
            }
            

            document.getElementById("averageTemp").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "http://localhost:8082/api/boiler/average");
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    console.log('test123');
}

function getTempInside(api) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("tempInside").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "http://localhost:8082/api/boiler/temperature");
    xhttp.setRequestHeader("Content-type", "application/json");

    xhttp.send();
    console.log('test');
}

function drawBar(ctx, upperLeftCornerX, upperLeftCornerY, width, height,color){
    ctx.save();
    ctx.fillStyle=color;
    ctx.fillRect(upperLeftCornerX,upperLeftCornerY,width,height);
    ctx.restore();
}