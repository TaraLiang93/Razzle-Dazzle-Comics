/**
 * Created by tara on 4/11/16.
 */
var input;
var imgForm;

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
        imgForm= new FormData();
        imgForm.append("imgSrc", event.target.files[0]);

        console.log("This is input "+imgForm);
        reader.readAsDataURL(event.target.files[0]);
    });
    $("#saveImg").click(function(){
        console.log("update series image");
        $("#img").attr("src",$("#imgPreview").attr("src"));
        $("#imgForm").submit();

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

    $(".chapter").each(function(){
        $(this).click(function(){
            $(location).attr("href","/" + $(this).attr("id"));

        });
    });

});