/**
 * Created by Jason on 5/14/16.
 */

$(document).ready(function(){

    $(".genre").each(function(){
        $(this).click(function(){
            $(this).submit();
        });
    })
});