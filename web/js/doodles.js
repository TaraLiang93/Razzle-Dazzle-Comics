/**
 * Created by Jason on 4/5/16.
 */

$(document).ready(function() {


    if( $("#loadIntoCanvas").length > 0 )
    {
        $("#doodleTitle").attr("value",$("loadTitleIntoCanvas").text() );
        $("loadTitleIntoCanvas").remove();
        $("#doodleDescription").attr("value",$("loadDescIntoCanvas").text() );
        $("loadDescIntoCanvas").remove();
    }

    $(".saveDoodle").click(function(){

        var doodleTitleTag = $("#doodleTitle").attr("value");
        var doodleDescriptionTag = $("#doodleDescription").attr("value");
        //if(validArgs($("#doodleTitle"), $("#doodleDescription")))
        //{
            $.post("/create/doodle/save", {"canvasImage": JSON.stringify(canvas), "doodleTitle": doodleTitleTag, "doodleDescription": doodleDescriptionTag
                })
                .done(function () {
                    $(location).attr('href', "/create/ideas");
                })
                .fail(function () {
                    console.log("it did not go here");
                });
        
        //}

    });

    $(".doodleButtons a.btn.btn-lg.btn-warning").click(function(){
        if(Stack.length != 0)
            $("#newDoodleModal").modal('toggle');
        else
            canvas.clear();
    });


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
