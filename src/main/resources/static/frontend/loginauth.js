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