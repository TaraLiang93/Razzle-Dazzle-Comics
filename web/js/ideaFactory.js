/**
 * Created by drodrigues on 4/3/16.
 */


$(document).ready(function(){

    $('#ideaFactory a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });

    $("#newScribble").click(function(){
        window.location.href="/create/scribble/load/new";
    });

    $("#newDoodle").click(function(){
        window.location.href="/create/doodle/load/new";
    });

    $(".scribble").each(function(){
       $(this).click(function(){
           window.location.href="/create/scribble/load/" + $(this).attr('id');
       });
    });

    $(".doodle").each(function(){
        $(this).click(function(){
            window.location.href="/create/doodle/load/" + $(this).attr('id');
        });
    });

});