/**
 * Created by Jason on 4/5/16.
 */

$(document).ready(function() {

    $("[data-toggle='popover']").click(function() {
        $(this).popover();
        //$("[data-toggle='popover']").addClass('on');

    });

    $("#Image-file").change(function() {

        $(this).attr("value", $(this).attr("value"));

    });

    //$(window).resize(function() {
    //
    //    var canvas = $("#canvas").get(0);
    //
    //    var newHeight = $(window).height();
    //    var newWidth = $(window).width()-$(".toolbar").width()-20;
    //    console.log(newHeight + " " + newWidth);
    //
    //    canvas.height =(newHeight);
    //    canvas.width = (newWidth);
    //    //canvas.calcOffset();
    //    canvas.renderAll();
    //});


});