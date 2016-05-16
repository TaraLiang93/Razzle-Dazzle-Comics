/**
 * Created by Jason on 5/13/16.
 */


$(document).ready(function() {

    $(".series").each(function(){
        $(this).click(function(){
            $(this).submit();
        });
    })

});