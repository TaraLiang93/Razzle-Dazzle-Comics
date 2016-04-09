/**
 * Created by Jason on 4/5/16.
 */
$(document).ready(function() {


    $("#saveDoodle").click(function(){
        //console.log("about to save doodle");
        //var canvas = document.getElementById("canvas");
        //canvas = fabric.Canvas('canvas');
        //canvas.clear();

        //console.log();
        //console.log(canvas);
        //
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
