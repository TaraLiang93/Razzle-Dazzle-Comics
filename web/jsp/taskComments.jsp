<script>
    $(document).ready(function(){

        $('#${param.selector}addNewComment').click(function(){
            var text = $('#${param.selector}commentText').val();
            var appendText= "<div class=\"well\">Me: "+text+"</div>";

            $.ajax({
                url:"/create/page/comment/add",
                data: {comment:text, pageID: $('#${param.selector}HiddenPageID').val()},
                type:"POST",
                success: function(data){
                    if(data){
                        console.log("Appending Text : text");
                        $('#${param.selector}commentsBox').append(appendText);
                        $('#${param.selector}commentText').val("");
                    }
                },
                error: function(){
                    alert("Failed to add a Comment.");
                }
            });
        });

    });

</script>


<div class="row"> <!-- Comments -->
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Comments :</h3>
        </div>
        <div id="${param.selector}commentsBox" class="panel-body" style="overflow:scroll; height:${param.height};">

        </div>
    </div>

    <div class="input-group" style="width:100%;">
        <input id="${param.selector}commentText" type="text" class="form-control" aria-label="Add a comment">
        <span class="input-group-addon"><a id="${param.selector}addNewComment" class="btn btn-default btn-sm" style="padding:0px;">Add Comment</a></span>
    </div>

</div>