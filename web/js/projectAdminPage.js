/**
 * Created by Jason on 4/13/16.
 */

$(document).ready(function() {

    $(".series").each(function() {
        $(this).click(function() {
           $(location).attr("href","/create/series/load/" + $(this).attr("id"));
        });
    });

});