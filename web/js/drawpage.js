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

});
