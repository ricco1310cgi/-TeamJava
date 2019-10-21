var serverBoilerArray = [];

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

            // Gebruiker
            document.getElementById("overzichtBinnenTemp").innerHTML = serverBoiler[serverBoilerArray.length - 1].tempInside;
            document.getElementById("overzichtBuitenTemp").innerHTML = serverBoiler[serverBoilerArray.length-1].tempOutside;

            // Admin
            document.getElementById("adminBinnenTemp").innerHTML = serverBoiler[serverBoilerArray.length - 1].tempInside;
            document.getElementById("adminBoilerDruk").innerHTML = serverBoiler[serverBoilerArray.length - 1].boilerPressure;
            document.getElementById("adminBuitenTemp").innerHTML = serverBoiler[serverBoilerArray.length-1].tempOutside;
            var date = new Date(serverBoiler[serverBoilerArray.length-1].timeMovementRecord * 1000);
            document.getElementById("adminLaatstThuis").innerHTML = date.toLocaleString();
            document.getElementById("adminGasGebruik").innerHTML = serverBoiler[serverBoilerArray.length-1].gasUsage;
            var deurStatus = serverBoiler[serverBoilerArray.length-1].doorClosed;
            console.log(deurStatus);
            if (deurStatus == "true") {
                document.getElementById("adminDeurStatus").innerHTML = "Open"
            } else {
                document.getElementById("adminDeurStatus").innerHTML = "Dicht"
            }
            //document.getElementById("overzichtDeurStatus").innerHTML = serverBoiler[serverBoilerArray.length-1].doorClosed;
            date = new Date(serverBoiler[serverBoilerArray.length-1].timeRecorder * 1000);
            document.getElementById("adminBoilerTijd").innerHTML = date.toLocaleString();
            console.log(this.responseText);
            //document.getElementById("demo").innerHTML = this.responseText;
            
        }
    };
    xhttp.open("GET", "http://localhost:8082/api/boiler");
    xhttp.setRequestHeader("Content-type", "application/json");

    xhttp.send();
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
}

function getAverageTemp(api) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            serverBoilerArray = JSON.parse(this.responseText);           
            serverBoiler = JSON.parse(this.responseText);

            var barChart = document.getElementById("barChart");

            barChart.width = 300;
            barChart.height = 200;
  
            var ctx = barChart.getContext("2d");

            ctx.clearRect(0, 0, barChart.width, barChart.height);

            ctx.save();

            ctx.beginPath();
            ctx.moveTo(0, 0);
            ctx.lineTo(0, barChart.height - 20);
            ctx.lineTo(barChart.width, barChart.height - 20);
            ctx.strokeStyle = "red";
            ctx.stroke();

            ctx.restore();

            ctx.font = "12px Arial";

            for (var i = 0; i < 2; i++) {
                console.log(serverBoiler[i]);
                ctx.save();
                ctx.fillRect(i * 50 + 10, barChart.height - 40, 40, -serverBoiler[i]*20);
                ctx.restore();    
                ctx.save();
                ctx.fillText(serverBoiler[i], i * 50 + 20, barChart.height - 25);   
                ctx.restore();   
                ctx.save();
                ctx.fillText("Dag: " + i , i * 50 + 10, barChart.height - 5);   
                ctx.restore();        
            }
            

            //document.getElementById("averageTemp").innerHTML = this.responseText;
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