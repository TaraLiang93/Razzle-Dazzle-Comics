/**
 * Created by tara on 4/13/16.
 */
var numPage;


$(document).ready(function(){
    numPage=0;

   $("#editChapterInfo").click(function(){
        console.log("edit chapter info clicked");
        $("#Title").val($("#chapterTitle").text());
        $("#myNumber").val($("#chapterString").text());
        $("#imgPreview").attr('src',$("#img").attr('src'));
        $("#infoModal").modal("show");
   });

   $("#addTeam").click(function(){
       console.log("add team clicked");
       var node = document.getElementById("teamBody");
       if(node != null){
           while(node.firstChild){
               node.removeChild(node.firstChild);
           }
       }
       $(".memberList p").each(function(index){
           var code =
               "<div class='indMemeber' >" +
               "<div>" +
               "<p class='name'>"+$(this).text()+"</p>" +
               "</div>" +
               "<div>" +
               "<select id='target' >" +
               "<option id='Member'>Member</option>" +
               "<option id='Artist'>Artist</option>" +
               "<option id='Writer'>Writer</option>" +
               "<option id='Owner'>Owner</option>" +
               "<option id='Manager'>Manager</option>" +
               "</select>" +
               "<i class='fa fa-times btn deleteMember' id='"+index+"' aria-hidden='true'></i>" +
               "</div>" +
               "</div>";

           $("#teamBody").append(code);
       });
       $("#teamModal").modal();
   });

    $("#teamModal").on("shown.bs.modal",function(){
        console.log("team modal open");
        $(".deleteMember").click(function(){
            var userName = $(".name").get(this.getAttribute("id")).innerHTML;
            console.log("plz delete "+$(".name").get(this.getAttribute("id")).innerHTML);

        });
        $("#addButton").click(function(){
           console.log("add "+$("#newMember").val());
            jsonObj["memberName"]=$("#newMember").val();
            $.post("create/chapterPage/addMember",jsonObj)
                .done(function(){
                    var code =
                        "<div class='indMemeber' >" +
                        "<div>" +
                        "<p class='name'>"+$("#newMember").val()+"</p>" +
                        "</div>" +
                        "<div>" +
                        "<select id='target' >" +
                        "<option id='Member'>Member</option>" +
                        "<option id='Artist'>Artist</option>" +
                        "<option id='Writer'>Writer</option>" +
                        "<option id='Owner'>Owner</option>" +
                        "<option id='Manager'>Manager</option>" +
                        "</select>" +
                        "<i class='fa fa-times btn deleteMember' id='"+index+"' aria-hidden='true'></i>" +
                        "</div>" +
                        "</div>";

                    $("#teamBody").append(code);
                })

            jsonObj["doodleId"] = $("#loadDoodleId").text();
            $.post("/create/doodle/save", jsonObj)
                .done(function () {
                    $(location).attr('href', "/create/ideas");
                })
                .fail(function () {
                    console.log("it did not go here");
                });

        });
    });

    $("#infoModal").on("shown.bs.modal", function(){
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

        $("#saveInfo").click(function(){
           console.log("save chapter information");
        });

    });

    $("#addPage").click(function(){
       console.log("add page clicked");
        var code =
            "<div><button class='btn pageTask' id="+numPage+">Page" +numPage+
            "</button></div>";

        $("#writer").append(code);
        numPage++;
    });

});