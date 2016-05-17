/**
 * Created by tara on 4/13/16.
 */
var numPage;


$(document).ready(function(){
    numPage=0;
    var chapterId = $("#chapterId");
    var deleteMember;

    $("#flowContainer").height( $(window).height()-$("body").height() );

   $("#editChapterInfo").click(function(){
       /*
        console.log("edit chapter info clicked");
        $("#Title").val($("#chapterTitle").text());
        $("#myNumber").val($("#chapterString").text());
        $("#imgPreview").attr('src',$("#img").attr('src'));
 */
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
           var selectValue = $(this).attr("title");
           console.log(selectValue);
           var code =
               "<div class='indMemeber' id="+$(this).text()+">" +
               "<div>" +
               "<p class='name'>"+$(this).text()+"</p>" +
               "</div>" +
               "<div>" +
               "<select class='target'>" +
               "<option name='Member' value='Member'>Member</option>" +
               "<option name='Artist'  value='Artist'>Artist</option>" +
               "<option name='Writer' value='Writer'>Writer</option>" +
               "<option name='Owner' value='Owner'>Owner</option>" +
               "<option name='Manager' value='Manager'>Manager</option>" +
               "</select>" +
               "<i class='fa fa-times btn deleteMember' id='"+index+"' aria-hidden='true'></i>" +
               "</div>" +
               "<div class='userName' style='display: none;'>"+$(this).parent().find(".teamMember").text()+"</div>" +
               "</div>";

           $("#teamBody").append(code);
           $(".target:not('.target.added')").val(selectValue).addClass("added");
       });
       $(".target.added").removeClass("added");
       $("#teamModal").modal();
   });

    $("#teamModal").on("shown.bs.modal",function(){
        console.log("team modal open");
        $(".deleteMember").click(deleteMember = function(){
            var selector = "#" +$(".name").get(this.getAttribute("id")).innerHTML + "ID";
            var parent = $(this).parent().parent();
            var userName = parent.find(".userName").text();
            console.log("plz delete "+$(".name").get(this.getAttribute("id")).innerHTML);
            var jsonObj = {"chapterID": chapterId.text(), "removeMember": userName};
            $.post("/create/chapter/removeMember", jsonObj)
                .done(function(){
                    //$(".name").get(this.getAttribute("id")).parentNode.removeChild($(".name").get(this.getAttribute("id")));
                    //var selector = "#"+userName.substring(0,userName.indexOf("@"));
                    //$(selector).remove();

                    $(".memberList .teamMember").each(function(){
                        if($(this).text() == userName ){
                            parent.remove();
                            $(this).parent().remove();
                            return;
                        }
                    });
                    console.log("they gone");

                })
                .fail(function(){
                    alert("Cannot remove user: "+userName);
                });
        });

        $("#addButton").click(function(){

            var newMember = $("#newMember").val();

           console.log("add "+newMember);

            if(newMember.indexOf("@") == -1)
            {
                return;
            }

            $.post("/create/chapter/addMember", {"chapterID": chapterId.text(), "newMemeber": newMember})
                .done(function(){
                    newMember = newMember.substring(0,newMember.indexOf("@"));
                    var code =
                        "<div class='indMemeber' >" +
                        "<div>" +
                        "<p class='name'>"+newMember+"</p>" +
                        "</div>" +
                        "<div>" +
                        "<select id='target' >" +
                        "<option id='Member'>Member</option>" +
                        "<option id='Artist'>Artist</option>" +
                        "<option id='Writer'>Writer</option>" +
                        "<option id='Owner'>Owner</option>" +
                        "<option id='Manager'>Manager</option>" +
                        "</select>" +
                        "<i class='fa fa-times btn deleteMember' id='"+$('.indMemeber').length+"' aria-hidden='true'></i>" +
                        "</div>" +
                        "<div class='userName' style='display: none;'>"+$("#newMember").val()+"</div>" +
                        "</div>";


                    $("#teamBody").append(code);
                    $(".memberList").append(
                        $("<span></span>").append(
                            $("<p></p>").html(newMember).attr("title","Member"),
                            $("<div></div>").html($("#newMember").val()).addClass("teamMember").css("display","none")
                        )

                    );
                    //$(".memberList").append("<p title='Member'>"+newMember+"</p>");
                    //$(".memberList").append("<div class='teamMember' style='display: none'>"+$("#newMember").val()+"</div>");
                    $("#newMember").val("");
                    $(".deleteMember").bind("click",deleteMember);
                    console.log("Team member was added");
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
            $('#imgForm').submit();
        });

    });

    $("#addPage").click(function(){
       console.log("Add Page Clicked");
        $('#newTaskModal').modal('show');
    });

    $('.flow').each(function(){
        storeFlow($(this).attr("id"));
    });

    $("#submitComment").click(function() {
        var comment = $("#reviewTaskText").val();
        var userName = $("#currentUserID").text();

        if(comment == "")
            return;
        $(".reviewComments").append("<p class='content-border commentBox'>"+userName+": "+comment+"</p>");
        $("#reviewTaskText").attr("value","");

    });


    $('#imgPreview').click(function(){
        $('#browseImg').click();
    });

    $('#browseImg').on('change', function(){
        readURL($(this), '#imgPreview');
    });

    $('.flowTable .mItem').each(function(){
        $(this).click(function(){
            loadTask($(this));
        });
    });

});

function loadWriteTask(json){

    $('#writeTaskHiddenPageID').val(json.id);
    $('#writeTaskPageTitle').text(json.title);
    $('#writeTaskAuthor').val(json.author);
    $('#writeTaskSummary').val(json.summary);

    $('#writeTaskcommentsBox').empty();

    for(i=0; i < json.comments.length ; i++){
        comment = json.comments[i];
        var appendText = "<div class=\"well\">"+ comment.user + ": " + comment.comment + "</div>";
        $('#writeTaskcommentsBox').append(appendText);
    }

    $('#writeTaskCarouselContent').empty();

    for(i=0; i < json.scenes.length; i++){
        for(j=0; j < json.scenes[i].dialogue.length; j++) {
            var appendText = "<div class=\"item";
            if (i == 0 && j == 0) appendText += " active ";
            appendText += "\" style=\"text-align:center;\"><p>" + json.scenes[i].dialogue[j] + "</p></div>";

            $('#writeTaskCarouselContent').append(appendText);
        }
    }

    $('#writeTaskModal').modal('show');

}

function loadPreDrawTask(json){

    $('#preDrawTaskHiddenPageID').val(json.id);
    $('#writeTaskPageTitle').text(json.title);
    $('#writeTaskAuthor').val(json.author);
    $('#writeTaskSummary').val(json.summary);

    for(i=0; i < json.comments.length ; i++){
        comment = json.comments[i];
        var appendText = "<div class=\"well\">"+ comment.user + ": " + comment.comment + "</div>";
        $('#preDrawTaskcommentsBox').append(appendText);
    }
    k = 0;
    var appendText ="";
    for(i=0; i < json.scenes.length; i++){
        appendText += "<div class=\"panel panel-default\">" +
            "<div class=\"panel-heading\" role=\"tab\" id=\"heading"+k+"\">" +
            "<h4 class=\"panel-title\">" +
            "<a role=\"button\" data-toggle=\"collapse\" data-parent=\"#preDrawTaskAccordion\" href=\"#collapseOne\" aria-expanded=\"true\" aria-controls=\"collapse"+k+"\"> " +
            "Scene"+(k + 1)+ "</a></h4></div>" +
            "<div id=\"collapse"+k+"\" class=\"panel-collapse collapse in\" role=\"tabpanel\" aria-labelledby=\"headingOne\">" +
            "<div class=\"panel-body\">";

        for(j=0; j < json.scenes[i].dialogue.length; j++, k++) {
            appendText += ("<p>"+json.scenes[i].dialogue[j] +"</p>");
        }
        appendText +="</div></div></div>";

    }
    $('#preDrawTaskAccordion').append(appendText);
    $('#preDrawTaskModal').modal('show');
}

function loadDrawTask(json){

    $('#writeTaskHiddenPageID').val(json.id);
    $('#writeTaskPageTitle').text(json.title);
    $('#writeTaskAuthor').val(json.author);
    $('#writeTaskSummary').val(json.summary);

    $('#writeTaskcommentsBox').empty();

    for(i=0; i < json.comments.length ; i++){
        comment = json.comments[i];
        var appendText = "<div class=\"well\">"+ comment.user + ": " + comment.comment + "</div>";
        $('#writeTaskcommentsBox').append(appendText);
    }

    $('#writeTaskCarouselContent').empty();
    for(i=0; i < json.dialogues; i++){
        var appendText = "<div class=\"item";
        if(i == 0) appendText += " active ";
        appendText += "\" style=\"text-align:center;\"><p>"+json.dialogues[i]+"</p></div>";

        $('#writeTaskCarouselContent').append(appendText);
    }

    $('#writeTaskModal').modal('show');
}

function loadReviewTask(json){

    $('#writeTaskHiddenPageID').val(json.id);
    $('#writeTaskPageTitle').text(json.title);
    $('#writeTaskAuthor').val(json.author);
    $('#writeTaskSummary').val(json.summary);

    $('#writeTaskcommentsBox').empty();

    for(i=0; i < json.comments.length ; i++){
        comment = json.comments[i];
        var appendText = "<div class=\"well\">"+ comment.user + ": " + comment.comment + "</div>";
        $('#writeTaskcommentsBox').append(appendText);
    }

    $('#writeTaskCarouselContent').empty();
    for(i=0; i < json.dialogues; i++){
        var appendText = "<div class=\"item";
        if(i == 0) appendText += " active ";
        appendText += "\" style=\"text-align:center;\"><p>"+json.dialogues[i]+"</p></div>";

        $('#writeTaskCarouselContent').append(appendText);
    }

    $('#writeTaskModal').modal('show');
}

function loadDoneTask(json){

    $('#writeTaskHiddenPageID').val(json.id);
    $('#writeTaskPageTitle').text(json.title);
    $('#writeTaskAuthor').val(json.author);
    $('#writeTaskSummary').val(json.summary);

    $('#writeTaskcommentsBox').empty();

    for(i=0; i < json.comments.length ; i++){
        comment = json.comments[i];
        var appendText = "<div class=\"well\">"+ comment.user + ": " + comment.comment + "</div>";
        $('#writeTaskcommentsBox').append(appendText);
    }

    $('#writeTaskCarouselContent').empty();
    for(i=0; i < json.dialogues; i++){
        var appendText = "<div class=\"item";
        if(i == 0) appendText += " active ";
        appendText += "\" style=\"text-align:center;\"><p>"+json.dialogues[i]+"</p></div>";

        $('#writeTaskCarouselContent').append(appendText);
    }

    $('#writeTaskModal').modal('show');
}

function loadAsyncTask(selector){
    $.ajax({
        url:"/create/page/loadTask",
        data: {pageID: $('#'+selector+'HiddenPageID').val()},
        type:"POST",
        success: function(object){
            if(selector.toUpperCase().includes("WRITE")){
                loadWriteTask(object);
            }
            else if(selector.toUpperCase().includes("PREDRAW")){
                loadPreDrawTask(object);
            }
            else if(selector.toUpperCase().includes("DRAW")){
                loadDrawTask(object);
            }
            else if(selector.toUpperCase().includes("REVIEW")){
                loadReviewTask(object);
            }
            else if(selector.toUpperCase().includes("DONE")){
                loadDoneTask(object);
            }
            else{
                console.log("Selector :" + selector + "no task found");
                alert("Failed to load Task!");
            }
        },
        error: function(){
            alert("Failed to edit the Summary.");
        }
    });
}

function loadTask(item){
    //  Find the parent enclosing div
    //      Find the header for the flow div, and the text corresponding to it
    var title = $(item).closest(".flow").find(".flowTitle:first-child").first().text();
    console.log("Flow : " + title);

    var selector = "";

    if(title.trim().toUpperCase().includes("WRITE")){
        selector = "writeTask";
    }
    else  if(title.trim().toUpperCase().includes("PREDRAW")){
        selector = "preDrawTask";
    }
    else  if(title.trim().toUpperCase().includes("DRAW")){
        selector = "drawTask";
    }
    else  if(title.trim().toUpperCase().includes("REVIEW")){
        selector = "reviewTask";
    }
    else  if(title.trim().toUpperCase().includes("DONE")){
        selector = "doneTask";
    }
    else{
        alert("Could not load task!");
        console.log("Item : " + item);
        return;
    }

    $('#'+selector+'HiddenPageID').val($(item).attr('id'));
    loadAsyncTask(selector);
}

var flowArray = [];
var flowMapNext = {};
var flowMapPrev = {};

function storeFlow(selector){
    var last = flowArray[flowArray.length - 1];
    flowMapNext[last] = selector; //First update the previous last to point to this new one

    var prev = (flowArray.length == 0)? selector : last;
    flowArray.push(selector);
    flowMapPrev[selector] = prev; //The previous is the most recent one, or itself if it's the first one
    flowMapNext[selector] = selector; //Point this new next to itself

}

function doMovePage(pid, url){
    $.ajax({
        url:url,
        data: {pageID:pid},
        type:"POST",
        success: function(data){
            if(data){
                console.log("Move Successful");
            }
        },
        error: function(){
            alert("Failed to Move Page");
        }
    });
}

function movePage(pid, direction){

    if(direction > 0){ // Move forward
        console.log("Moving Page with ID : " + pid + "Forward ");
        doMovePage(pid, "/create/page/moveNext")
    }
    else if (direction < 0){ // Move backwards
        console.log("Moving Page with ID : " + pid + "backwards ");
        doMovePage(pid, "/create/page/movePrev")
    }
    else{
        console.log("Direction == 0 means lets stay still");
    }
}

function swapTasks(map, task){
    var id = $( ".flowTable:has(#"+task+")").attr("id");
    var nextID = map[id]; //Fetch the next one from the map
    if(id === nextID){
        console.log("Nothing to do, ID's are the same, must be illegal move");
    }
    else{
        $(nextID).append($(task)); //Append it to the new one
        $(id).remove($(task));     //Remove it from the previous
        console.log("Done Swapping");
    }

}

function moveNext(task, pid, modal){
    movePage(pid, 1);
    $(modal).modal('hide');
    swapTasks(flowMapNext, task);
}

function movePrev(task, pid, modal){
    movePage(pid, -1);
    $(modal).modal('hide');
    swapTasks(flowMapPrev, task);
}





