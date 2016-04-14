/**
 * Created by drodrigues on 4/1/16.
 */

$(document).ready(function(){

    if($("#isAuth").text() == "false")//redirecting the user if they load onto a page without logging in
    {
        $(location).attr("href","/login");
    }

});
