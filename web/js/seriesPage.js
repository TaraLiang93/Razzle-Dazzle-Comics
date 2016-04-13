/**
 * Created by tara on 4/11/16.
 */

$("document").ready(function(){

    $("#uploadButton").click(function(){
       console.log("click upload new image");
        $("#imgPreview").attr("src",$("#img").attr("src"));
        $("#imgModal").modal("show");
    });
    $("#browseImg").change(function(event){
        console.log("new image: "+event.target.result);
        var reader = new FileReader();
        reader.onload = function (event) {
            var img = new Image();
            img.src = event.target.result;
            img.onload = function () {
                 console.log("image load");
                $("#imgPreview").attr("src",img.src);
            };

        };
        reader.readAsDataURL(event.target.files[0]);
    });
    $("#saveImg").click(function(){
        console.log("update series image");
        $("#img").attr("src",$("#imgPreview").attr("src"));
        $.ajax({
           url:"/create/series/updateSeriesImage",
            data: {img: $("#imgPreview").attr("src")},
            type:"GET",
            error: function(){
                alert("Fail to update series image.");
            }
        });

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
            data: {desc:$("#textboxDescr").val()},
            type:"POST",
            error: function(){
                alert("Fail to update description.");
            }
        });
    });
});