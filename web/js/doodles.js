/**
 * Created by Jason on 4/5/16.
 */
$(document).ready(function() {


    $("#saveDoodle").click(function(){
        console.log("about to save doodle");
        var canvas = $("#canvas").get(0);
        $.post("/create/dooddle/save", JSON.stringify(canvas));
    });

});
