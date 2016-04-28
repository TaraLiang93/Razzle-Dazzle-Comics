/**
 * Created by Jason on 4/5/16.
 */

$(document).ready(function() {

    //$("body").height($(window).height);
    $(".dialog").each(function(){
        $(this).click(function() {
           console.log($(this).text());
        });
    });

    $(".scene").click(function() {

        if(!$(this).hasClass("selected")) {
            // this grabs the data from the selected item and loads it
            var canvasImg = $(this).find(".canvasImage").text();
            var diaglogs = $(this).find(".SceneDialogs").html();
            var setting = $(this).find(".sceneSetting").text();


            // save the data into the new one
            $(".scene.selected").find(".canvasImage").text(JSON.stringify(canvas));
            $(".scene.selected").find(".SceneDialogs").html($(".diaglogs").html());
            $(".scene.selected").find(".sceneSetting").text($("#SettingScene").text());
            $(".scene.selected").removeClass("selected");
            $(this).addClass("selected");

            canvas.loadFromJSON(canvasImg, canvas.renderAll.bind(canvas));
            canvas.renderAll();
            $("#SettingScene").text(setting);
            $(".diaglogs").html(diaglogs);
        }

    });

    $("#saveDrawing").click(function() {
        var jsonObj = {"size" : 0};
        var i = 0;
        $(".scene").each(function() {
            var map = "canvasImage" + i++;
            jsonObj[map] = $(this).find(".canvasImage").text() ;
        });
        jsonObj["size"] = i;
        console.log(jsonObj);


        $.post("/create/drawPage/save", jsonObj )
            .done(function() {
            console.log("Yes");
        })
            .fail(function() {
               console.log("No");
            });
    })

});
