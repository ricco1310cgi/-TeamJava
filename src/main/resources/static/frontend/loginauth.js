function check(form) {
    if(form.userid.value == "Royschuiveling@gmail.com" && form.pswrd.value === "123") {
        window.open();
    } else {
        alert("The username and password you entered don't match");
    }
}