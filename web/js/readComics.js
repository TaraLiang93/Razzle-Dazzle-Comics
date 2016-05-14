/**
 * Created by Jason on 5/14/16.
 */

$(document).ready(function() {

    $(".series").each(function(){// this code needs to be changed
        $(this).click(function(){
            $.ajax({
                url: "/read/" + $(this).text(),
                data : {seriesID : $(this).attr("id")},
                type : "GET",
                async: false

            })
                .done(function(){
                    console.log("wow all the data was sent over");
                })
                .fail(function(){
                    console.log("The data failed to send");
                });
        });
    })

});