$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/greeting"
    }).then(function(data) {
       $('.greeting-id').append(data);
       $('.greeting-content').append(data);
    });
});