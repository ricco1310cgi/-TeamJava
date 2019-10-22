$("#addUserForm").submit(function (e) { 
    e.preventDefault();
    console.log(this);
    console.log($("#inputEmail").val());
    console.log($("#inputPass").val());
    console.log($("#role").val()[0]);
    var addRequest = {
        username:$("#inputEmail").val(),
        password:$("#inputPass").val(),
        role:$("#role").val()[0]
    }
    addUser(addRequest);
});

$("#deleteUserForm").submit(function (e) { 
    e.preventDefault();
    console.log(this);
    console.log($("#inputEmailDelete").val());
    console.log($("#inputPassDelete").val());
    console.log($("#roleDelete").val()[0]);
    var deleteRequest = {
        username:$("#inputEmailDelete").val(),
        password:$("#inputPassDelete").val(),
        role:$("#roleDelete").val()[0]
    }
    deleteUser(deleteRequest);
});

function addUser(request) {
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                console.log(request);
                alert("Gebruiker succesvol toegevoegd");
                this.open("administratie.html", "_self");
            } else {
                alert("Gebruiker al bekend");
            }

        }
    };
    xhttp.open("POST", "http://localhost:8082/api/auth/adduser");
    xhttp.setRequestHeader("Content-type", "application/json");

    xhttp.send(JSON.stringify(request));
}

function deleteUser(request) {
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                alert("Gebruiker succesvol verwijderd");
                this.open("administratie.html", "_self");
            } else {
                alert("Gebruiker onbekend");
            }

        }
    };
    xhttp.open("POST", "http://localhost:8082/api/auth/deleteuser");
    xhttp.setRequestHeader("Content-type", "application/json");

    xhttp.send(JSON.stringify(request));
}
