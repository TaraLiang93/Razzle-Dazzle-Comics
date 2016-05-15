/**
 * Created by Jason on 5/14/16.
 */

$(document).ready(function(){

    $(".genre").each(function(){
        $(this).click(function(){
            $(location).attr("href","/read/genres/" + $(this).find(".genreName").text());
        });
    })
});