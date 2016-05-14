/**
 * Created by Jason on 4/5/16.
 */


$(document).ready(function() {


    if( $("#loadIntoCanvas").length > 0 )
    {
        $("#doodleTitle").attr("value",$("#loadTitleIntoCanvas").text() );
        $("#loadTitleIntoCanvas").remove();
        $("#doodleDescription").attr("value",$("#loadDescIntoCanvas").text() );
        $("#loadDescIntoCanvas").remove();
    }

    $(".saveDoodle").click(function(){

        var doodleTitleTag = $("#doodleTitle").attr("value");
        var doodleDescriptionTag = $("#doodleDescription").attr("value");
        //if(validArgs($("#doodleTitle"), $("#doodleDescription")))
        //{
        var jsonObj = {"canvasImage": JSON.stringify(canvas), "doodleTitle": doodleTitleTag, "doodleDescription": doodleDescriptionTag};
        if($("#loadDoodleId").text() != "")
            jsonObj["doodleId"] = $("#loadDoodleId").text();
            $.post("/create/doodle/save", jsonObj)
                .done(function () {
                    $(location).attr('href', "/create/ideas");
                })
                .fail(function () {
                    console.log("it did not go here");
                });
        
        //}

    });

    $(".doodleButtons a.btn.btn-lg.btn-warning").click(function(){
        if(Stack.length != 0)// prompt the user to save
        {

            $("#newDoodleModal").modal('toggle');
        }
        else
            $(location).attr("href","/create/doodle/new");
    });

    $(".yesDoodle").click(function(){

        var doodleTitleTag = $("#doodleTitle").attr("value");
        var doodleDescriptionTag = $("#doodleDescription").attr("value");
        //if(validArgs($("#doodleTitle"), $("#doodleDescription")))
        //{
        var jsonObj = {"canvasImage": JSON.stringify(canvas), "doodleTitle": doodleTitleTag, "doodleDescription": doodleDescriptionTag};
        if($("#loadDoodleId").text() != "")
            jsonObj["doodleId"] = $("#loadDoodleId").text();
        $.post("/create/doodle/save", jsonObj)
            .done(function(){
                console.log("it saved successfully");
                $(location).attr("href","/create/doodle/new");
            });

    });

    $(".noDoodle").click(function() {
        $(location).attr("href","/create/doodle/new");
    })


});

function validArgs(doodleTitleTag,doodleDescriptionTag) {

    var isValid = true;

    if(doodleTitleTag.val() == "")
    {
        doodleTitleTag.parent().addClass("has-error");
        isValid = false;
    }

    if(doodleDescriptionTag.val() == ""){
        doodleDescriptionTag.parent().addClass("has-error");
        isValid = false;
    }

    return isValid;

}
