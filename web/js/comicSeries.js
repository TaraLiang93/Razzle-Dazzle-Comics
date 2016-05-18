/**
 * Created by tara on 5/13/16.
 */
$(document).ready(function(){
    $(".chapter").each(function(){
        $(this).click(function(){// this should redirect to 1st page of that chapter
            //alert("Clicker");
            $(this).submit();
            //$(location).attr("href","/read/"+$("#seriesTit").text()+"/"+ $(this).attr("id"));

        });
    });
});