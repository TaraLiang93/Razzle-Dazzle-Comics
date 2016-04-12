/**
 * Created by Jason on 4/5/16.
 */
$(document).ready(function() {


    $("#saveDoodle").click(function(){

        $("#jsonCanvas").text(JSON.stringify(canvas));
        $.post("/create/doodle/save", {"canvasImage" : JSON.stringify(canvas), "doodleTitle" : $("#doodleTitle").attr("value"), "doodleDescription": $("#doodleDescription").attr("value") })
            .done(function() {
                $(location).attr('href', "/create/ideas");
            })
            .fail(function() {
            console.log("it did not go here");
        });
    });

});
