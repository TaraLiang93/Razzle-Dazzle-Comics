/**
 * Created by Jason on 5/15/16.
 */

$(document).ready(function() {

    $(".genre").each(function(){
       $(this).click(function(){
            $(this).submit();
       });
    });
});