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

        function editPage(event, page){

        }

        function newPage(event){
            console.log("new image: "+event.target.result);
            var current = event.data.pageNum;
            var reader = new FileReader();
            reader.onload = function (event) {
                var img = new Image();
                img.src = event.target.result;
                img.onload = function () {
                    console.log("image load "+$("#"+current));
                    if($("#"+current).attr("src") !=null){
                        $("#"+current).attr("src",img.src);
                    }
                    else{
                        $("#"+numUpload).attr("src",img.src);
                        numUpload = numUpload+1;
                    }
                };

            };
            imgForm= new FormData();
            console.log("This is input "+imgForm);
            reader.readAsDataURL(event.target.files[0]);

        }

        $("#pageImgPlus").click(function(){
            var current = numUpload;
            var code =
                "<div class='uploadEle col-sm-4'>" +
                    "<img id="+current+" name="+current +"src='/img/chapter_default.jpeg'  onclick='$('#'"+current+").click(newPage);'>"+
                    "<input class='uploadPage hide' id='input"+current+"' name='file"+current+"' class='hide' type='file' accept='image/*'>"+
                    "<p class='pageNum'>Page"+current+"</p>"+
                "</div>";


            $(".row-fluid").append(code);
            $("#input"+current).bind("change",{pageNum: current},newPage);
            $("#"+current).bind("click", function(){
                console.log("image is clicked and triggering input tag");
                $("#input"+current).trigger("click");
            });

            $(".pageNum").bind("click", function(){
                console.log("image is clicked and triggering input tag");
                $("#input"+current).trigger("click");
            });

            $("#input"+current).trigger("click");




        });

        $("#pageImg").change(function (event){

        });

    });

    });
