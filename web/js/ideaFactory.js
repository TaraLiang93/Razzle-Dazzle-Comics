/**
 * Created by drodrigues on 4/3/16.
 */


$(document).ready(function(){

    $('#wrapper a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });

    $("#newScribble").click(function(){
        alert("Load damn you!");
        window.location.href="/create/scribble/load/new";
    });

});