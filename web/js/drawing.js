/**
 * Created by Jason on 4/5/16.
 */

$(document).ready(function() {


    $('#Draw').popover();
    $("#Draw").blur(function() {
        $(this).popover('hide');
    });

});