/**
 * Created by tara on 4/13/16.
 */

$(document).ready(function(){
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

        });
    });


});