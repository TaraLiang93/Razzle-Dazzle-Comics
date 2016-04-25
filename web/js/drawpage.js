/**
 * Created by Jason on 4/5/16.
 */

$(document).ready(function() {

    //$("body").height($(window).height);
    $(".dialog").each(function(){
        $(this).click(function() {
           console.log($(this).text());
        });
    });

    $(".newScene").click(function() {
        $(this).parent().append("<span class=''>" +
            "<img class='inner content-border'/>" +
            "<div class='canvasJson' style='display: none;'>" +
            "</span>");
    });

});
