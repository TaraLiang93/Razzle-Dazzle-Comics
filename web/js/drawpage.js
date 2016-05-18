/**
 * Created by Jason on 4/5/16.
 */

var placeDialog;

$(document).ready(function() {

    //$("body").height($(window).height);
    $(".dialog").each(function(){
        $(this).click(placeDialog=function() {
           console.log($(this).text());
            addTextStr($(this).text());
        });
    });

    $(".scene").click(function() {

        if(!$(this).hasClass("selected")) {
            // this grabs the data from the selected item and loads it
            var canvasImg = $(this).find(".canvasImage").text();
            var diaglogs = $(this).find(".SceneDialogs").html();
            var setting = $(this).find(".sceneSetting").text();


            // save the data into the new one
            saveSelectedPage();
            canvas.clear();
            $(".scene.selected").removeClass("selected");
            $(this).addClass("selected");

            canvas.loadFromJSON(canvasImg, canvas.renderAll.bind(canvas));
            canvas.renderAll();
            $("#SettingScene").text(setting);
            $(".diaglogs").html(diaglogs);

            $(".dialog").each(function(){
                $(this).on("click",placeDialog);
            });
        }

    });

    $("#saveDrawing").click(function() {
        var jsonObj = {"size" : 0, "pageID": $("#pageID").text()};
        var i = 0;
        saveSelectedPage();
        $(".scene").each(function() {
            var cID = "canvasID" + i;
            var map = "canvasImage" + i++;
            jsonObj[map] = $(this).find(".canvasImage").text() ;
            jsonObj[cID] = $(this).find(".canvasID").attr("title");
        });
        jsonObj["size"] = i;
        console.log(jsonObj);


        $.post("/create/drawPage/save", jsonObj )
            .done(function() {
            console.log("it saved successfully");
                $(location).attr("href","/create/chapter/load/" + $("#chapterID").text());
        })
            .fail(function() {
               console.log("it failed");
            });
    })

});

function saveSelectedPage(){
    $(".scene.selected").find(".canvasImage").text(JSON.stringify(canvas));
    $(".scene.selected").find(".SceneDialogs").html($(".diaglogs").html());
    $(".scene.selected").find(".sceneSetting").text($("#SettingScene").text());
}