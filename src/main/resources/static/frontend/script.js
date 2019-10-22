var boilerAliveStatus = false;

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

function startBoiler(userRole) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var boilerAliveStatus = xhttp.responseText.toString() == "true";

            if (!boilerAliveStatus) {
                var xhttp2 = new XMLHttpRequest();
                xhttp2.onreadystatechange = function () {
                    if (xhttp2.readyState == 4 && xhttp2.status == 200) {
                        console.log("Boiler Started!");
                        if (userRole = "manager") {
                            console.log("Gebruiker manager logged in");
                            open("overzicht.html", "_self");
                        } else if(userRole = "administratie") {
                            console.log("Administratie manager logged in");
                            open("overzicht.html", "_self");
                        } else {
                            console.log("User manager logged in");
                            open("thermometer.html", "_self");
                        }
                    }
                };
                xhttp2.open("GET", "http://localhost:8082/api/boiler/start");
                xhttp2.setRequestHeader("Content-type", "application/json");
                xhttp2.send();
            } else {
                console.log("Boiler already started, redirecting")
                if (userRole = "manager") {
                    console.log("Gebruiker manager logged in");
                    open("overzicht.html", "_self");
                } else if(userRole = "administratie") {
                    console.log("Administratie manager logged in");
                    open("overzicht.html", "_self");
                } else {
                    console.log("User manager logged in");
                    open("thermometer.html", "_self");
                }
            }
        }
    };
    xhttp.open("GET", "http://localhost:8082/api/boiler/alive");
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();

}

function isBoilerAlive(api) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            boilerAliveStatus = this.responseText.toString() == "true";
        }
    };
    xhttp.open("GET", "http://localhost:8082/api/boiler/alive");
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function getData(api) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            serverBoilerArray = JSON.parse(this.responseText);
            serverBoiler = JSON.parse(this.responseText);

            // Gebruiker
            document.getElementById("currentTempInHouse").innerHTML = serverBoiler[0].tempInside;
            document.getElementById("expectedTemp").innerHTML = serverBoiler[0].tempOutside;

        }
    };
    xhttp.open("GET", "http://localhost:8082/api/boilerDesc");
    xhttp.setRequestHeader("Content-type", "application/json");

    xhttp.send();
}

function updateBoiler(api) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

        }
    };
    xhttp.open("POST", "http://localhost:8082/api/boiler/add");
    xhttp.setRequestHeader("Content-type", "application/json");

    xhttp.send();
}

var autoUpdater;

$(document).ready(function () {
    setInterval(() => {
        isBoilerAlive();
    }, 2000);
    autoUpdater = setInterval(() => {
        if (boilerAliveStatus) {
            console.log(document.title);
            if (document.title == "Thermometer huisje") {
                updateBoiler();
                getData();
                if($("#setTimeNow").hasClass("btn-info")) {
                    updateTemp();
                } else {
                    increaseTempByTime();
                }
                
            }
        }
    }, 2000);
});

function devTerminate() {
    clearInterval(autoUpdater);
}

function updateTimes() {
    var times = [
    ]

    console.log(document.getElementById("temperatureDropDown").children)
    var childerenOf = document.getElementById("temperatureDropDown").children

    for (let i = 1; i < childerenOf.length + 1; i++) {
        times[i - 1] = moment().add(i * 30, 'm').startOf('minute').format("ddd, D MMM HH:mm");
    }

    for (let i = 0; i < childerenOf.length; i++) {
        childerenOf[i].innerHTML = times[i];

    }
}

function updateTempToSetMinus() {
    console.log("Button pressed: " + $("#tempToSet").text());
    var currentNumber = Number($("#tempToSet").text());
    currentNumber -= 0.5;
    if (currentNumber < 15) {
        currentNumber = 15;
    }
    console.log(currentNumber);
    $("#tempToSet").text(currentNumber);
    $("#tempToSetModal").text(currentNumber);
}

function updateTempToSetPlus() {
    console.log("Button pressed: " + $("#tempToSet").text());
    var currentNumber = Number($("#tempToSet").text());
    currentNumber += 0.5;
    if (currentNumber > 23.5) {
        currentNumber = 23.5;
    }
    console.log(currentNumber);
    $("#tempToSet").text(currentNumber);
    $("#tempToSetModal").text(currentNumber);
}

function updateTemp() {
    var currentTempLargeInt = Number($("#tempToSet").text())*10;
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

        }
    };
    xhttp.open("POST", "http://localhost:8082/api/boiler/temperature/" + currentTempLargeInt);
    xhttp.setRequestHeader("Content-type", "application/json");

    xhttp.send();
}

var timeToSet = timeToSet = moment().add(1 * 30, 'm').startOf('minute').valueOf();

function updateTimeStamp(number) {
    timeToSet = moment().add(number + 1 * 30, 'm').startOf('minute').valueOf();
    console.log(timeToSet);
}

function increaseTempByTime(){
    var currentTempLargeInt = Number($("#tempToSet").text())*10;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                
            }
            
        }
    };
    xhttp.open("GET", "http://localhost:8082/api/boiler/temperature/" + currentTempLargeInt + "/" + timeToSet);
    xhttp.setRequestHeader("Content-type", "application/json");

    xhttp.send();
}


function setNowActive() { 
    $("#laterButton").removeClass("btn-dark");
    $("#setTimeNow").removeClass("btn-dark");
    $("#laterButton").removeClass("btn-info");
    $("#setTimeNow").removeClass("btn-info");
    $("#laterButton").addClass("btn-dark");
    $("#setTimeNow").addClass("btn-info");
}

function setLaterActive() { 
    $("#laterButton").removeClass("btn-dark");
    $("#laterButton").removeClass("btn-info");
    $("#setTimeNow").removeClass("btn-dark");
    $("#setTimeNow").removeClass("btn-info");
    $("#laterButton").addClass("btn-info");
    $("#setTimeNow").addClass("btn-dark");
}