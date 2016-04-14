/**
 * Created by Jason on 4/13/16.
 */

$(document).ready(function() {

    $("#newSeries").click(function() {
       $(location).attr("href","/");
    });

    $(".series").each(function() {
        $(this).click(function() {
           $(location).attr("href","homepage/" + $(this).attr("id"));
        });
    });

});