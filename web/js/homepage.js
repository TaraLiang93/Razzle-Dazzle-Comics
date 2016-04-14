/**
 * Created by Jason on 4/4/16.
 */

$(document).ready(function() {
    //alert("hi Jason");

    $("body").height( $(window).height()-$("body").height() );

    $(".reading").click(function() {
        $(location).attr("href","/read");
    });

    $(".creation").click(function() {
        $(location).attr("href","/create");
    });

});