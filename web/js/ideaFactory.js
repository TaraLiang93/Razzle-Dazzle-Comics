/**
 * Created by drodrigues on 4/3/16.
 */


$(document).ready(function(){

    $('#wrapper a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    })

});