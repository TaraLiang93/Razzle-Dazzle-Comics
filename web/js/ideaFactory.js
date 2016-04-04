/**
 * Created by drodrigues on 4/3/16.
 */


$(document).ready(function(){

    $('#wrapper a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });

    $("#newScribble").click(function(){
        window.location.href="/create/scribble/load/new";
    });

    $(".scribble").each(function(){
       $(this).click(function(){
           window.location.href="/create/scribble/load/" + $(this).attr('id');
       });
    });

});