/**
 * Created by Jason on 4/5/16.
 */
$(document).ready(function() {


    $(".saveDoodle").click(function(){

        var doodleTitleTag = $("#doodleTitle").attr("value");
        var doodleDescriptionTag = $("#doodleDescription").attr("value");
        if(validArgs($("#doodleTitle"), $("#doodleDescription")))
        {
            $.post("/create/doodle/save", {"canvasImage": JSON.stringify(canvas), "doodleTitle": doodleTitle, "doodleDescription": doodleDescription
                })
                .done(function () {
                    $(location).attr('href', "/create/ideas");
                })
                .fail(function () {
                    console.log("it did not go here");
                });
        }

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
