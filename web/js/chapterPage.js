/**
 * Created by tara on 4/13/16.
 */
var numPage;


$(document).ready(function(){
    numPage=0;
    var chapterId = $("#chapterId");

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
               "<div class='indMemeber' id="+$(this).text()+">" +
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
            var jsonObj = {"chapterID": chapterId, "newMemeber": userName};
            $.post("/create/chapter/removeMember", jsonObj)
                .done(function(){
                    $(".name").get(this.getAttribute("id")).parentNode.removeChild($(".name").get(this.getAttribute("id")));
                })
                .fail(function(){
                    alert("Cannot remove user: "+userName);
                });
        });

        $("#addButton").click(function(){
           console.log("add "+$("#newMember").val());
            var jsonObj = {"chapterID": chapterId, "newMemeber": $("#newMember").val()};
            $.post("/create/chapter/addMember", jsonObj)
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
                .fail(function(){
                    alert("Cannot add user: "+$("#newMember").val());
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