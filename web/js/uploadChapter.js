/**
 * Created by tara on 4/22/16.
 */
var numUpload;
$(document).ready(function(){

    numUpload = 1;


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
            alert("number of upload:" + numUpload);
            numUpload = numUpload+1;

        }

        $("#pageImgPlus").click(function(){
            var code =
                "<div class='uploadEle col-sm-4'>" +
                    "<img id="+numUpload+" name="+numUpload +"src='/img/chapter_default.jpeg'  onclick='$('.uploadPage').click();'>"+
                    "<input class='uploadPage hide' id='input"+numUpload+"' class='hide' type='file' accept='image/*'>"+
                    "<p class='pageNum'>Page"+numUpload+"</p>"+
                "</div>";


            $(".row-fluid").append(code);
            $("#input"+numUpload).bind("change",newPage);

            $("#input"+numUpload).trigger("click");

            console.log('been here right now'+numUpload);
                //$("#p"+numUpload).val("Page "+numUpload);
                //numUpload= numUpload+1;
            console.log('after increase one in numUpload: '+numUpload);





        });

        $("#pageImg").change(function (event){

        });

    });

    });
