/**
 * Created by tara on 4/22/16.
 */

$(document).ready(function(){

    var numUpload = 1;


    $("#uploadChapterModal").on("shown.bs.modal", function(){

        $("#chapterIcon").click(function(){
            $(".browseChapIcon").trigger("click");
        });

       $(".browseChapIcon").change(function(event){
           console.log("new image: "+event.target.result);
           var reader = new FileReader();
           reader.onload = function (event) {
               var img = new Image();
               img.src = event.target.result;
               img.onload = function () {
                   console.log("image load");
                   $("#chapterIcon").attr("src",img.src);
               };

           };
           imgForm= new FormData();
           console.log("This is input "+imgForm);
           reader.readAsDataURL(event.target.files[0]);
       });

        function newPage(event){
            console.log("new image: "+event.target.result);
            var reader = new FileReader();
            reader.onload = function (event) {
                var img = new Image();
                img.src = event.target.result;
                img.onload = function () {
                    console.log("image load");
                    $("#"+numUpload).attr("src",img.src);
                };

            };
            imgForm= new FormData();
            console.log("This is input "+imgForm);
            reader.readAsDataURL(event.target.files[0]);
        }

        $("#pageImgPlus").click(function(){
            var code =
                "<div class='uploadEle col-sm-4'>" +
                    "<img id="+numUpload+" name="+numUpload +"src=''  onclick='$('.uploadPage').click();'>"+
                    "<input class='uploadPage hide' class='hide' type='file' accept='image/*'>"+
                    "<p class='pageNum'></p>"+
                "</div>";



            $(".row-fluid").append(code);
            $(".uploadPage").bind("change",newPage);

            $(".uploadPage").trigger("click");




        });

        $("#pageImg").change(function (event){

        });

    });

    });
