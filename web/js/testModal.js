/**
 * Created by tara on 4/22/16.
 */

$(document).ready(function(){
   $("#uploadChap").click(function(){
       console.log("upload chapter click");
       $("#uploadChapterModal").modal("show");
   });

    $("#uploadChapterModal").on("shown.bs.modal", function(){

       $("#browseChapIcon").change(function(event){
           console.log("new image: "+event.target.value);
           var reader = new FileReader();
           reader.onload = function (event) {
               console.log("inside of browse onload");
               var img = new Image();
               img.src = event.target.value;
               img.onload = function () {
                   console.log("image load");
                   $("#chapterIcon").attr("src", img.src);
               };
           };
           reader.readAsDataURL(event.target.files[0]);

       });
    });

    });
