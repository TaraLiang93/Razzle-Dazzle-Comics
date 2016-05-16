/**
 * Created by tara on 4/11/16.
 */
var input;
var imgForm;

$("document").ready(function(){

    $("#uploadButton").click(function(){
       console.log("click upload new image");
        $('#browseImg').click();
    });
    $("#browseImg").change(function(event){
        console.log("new image: "+event.target.result);
        readURL($(this), '#imgPreview');
        $('#imgForm').submit();
    });


    $("#updateDescr").click(function(){
        console.log("descr text: "+$("#descrText").html());
        console.log("textbook "+        $("#textboxDescr").val());
        $("#textboxDescr").val(($("#descrText").html()));
        console.log("textbook "+        $("#textboxDescr").val());

        $("#descrModal").modal("show");
    });

    $("#saveDescr").click(function(){
        console.log("Description click save");
        $("#descrText").text($("#textboxDescr").val());
        $.ajax({
            url:"/create/series/updateDescription",
            data: {desc:$("#textboxDescr").val(), seriesID: $("#series").val()},
            type:"POST",
            error: function(){
                alert("Fail to update description.");
            }
        });
    });

    $(".chapter").each(function(){
        $(this).click(function(){
            $(location).attr("href","/create/chapter/load/" + $(this).attr("id"));

        });
    });


    $('#addChapter').click(function(){
         $('#chapterModal').modal('show');
    });

    $("#uploadChapter").click(function(){
        console.log("upload chapter click");
        $("#uploadChapterModal").modal("show");
    });

    $(".publishToggle").click(function(){

        var current = $(this);
        $.post("/create/publish",{seriesID : $("#seriesID").text()})
            .done(function(){
                console.log("the series has been publshed");
                var swap1 = current.text();
                var swap2 = current.attr("title");

                current.text(swap2);
                current.attr("title",swap1);
            })
            .fail(function(){
                console.log("Failed to unpublish page");
            });
    })

});