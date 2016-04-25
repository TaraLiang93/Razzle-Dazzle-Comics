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
        $(".scene.selected").removeClass("selected");
        $(this).addClass("selected");

        var canvasImg = $(this).find(".canvasImage").text();
        var diaglogs = $(this).find(".SceneDialogs").html();
        var setting = $(this).find(".sceneSetting").text();


        canvas.loadFromJSON(canvasImg, canvas.renderAll.bind(canvas));
        canvas.renderAll();

        $("#SettingScene").text(setting);
        $(".diaglogs").html(diaglogs);

    });

});
