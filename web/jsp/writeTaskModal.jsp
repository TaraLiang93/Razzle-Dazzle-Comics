<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
<script src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="/js/globals.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
--%>

<script>
$(document).ready(function(){

    $('#writeTaskSummary').click(function(){
        $(this).removeAttr("readonly");
        $('#editSummary').removeClass('hide');
    });

    $('#editSummary').click(function(){
        $('#writeTaskSummary').attr("readonly", "readonly");
        $('#editSummary').addClass('hide');
        var summary = $('#writeTaskSummary').val();

        $.ajax({
            url:"/create/page/editSummary",
            data: {summary:summary, pageID: $('#hiddenPageID').val()},
            type:"POST",
            error: function(){
                alert("Failed to edit the Summary.");
            }
        });
    });

    $('#addNewComment').click(function(){
         var text = $('#commentText').val();
         var appendText= "<span class=\"label label-default\">Me : "+text+"</span>";


        $.ajax({
            url:"/create/page/comment/add",
            data: {comment:text, pageID: $('#hiddenPageID').val()},
            type:"POST",
            success: function(data){
                if(data){
                    console.log("Appending Text : text");
                    $('#commentsBox').append(appendText);
                }
            },
            error: function(){
                alert("Failed to add a Comment.");
            }
        });
    });

});

</script>



<form id="writeTaskForm" method="post" action="/create/writePage/load" >
    <input id="hiddenChapterID" type="hidden" name="chapterID" value="${param.chapterID}"/>
    <input id="hiddenPageID" type="hidden" name="pageID" value=""/>
    <div id="writeTaskModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 id="pageTitle" class="modal-title pull-left"></h4>
                </div>
                <div class="modal-body">
                    <div class="topWriteTask center-stage" style="width:95%; margin:auto; ">
                            <div class="row"> <!-- Create Date, Author, Last Edit and Dialogue -->
                                <div class="col-xs-4" style="">
                                    <div class="row" style="padding-bottom:1em;">
                                        <label for="writeTaskCreateDate" style="margin:0px;">Create Date :</label>
                                        <input type="date" id="writeTaskCreateDate" class="pull-right" disabled value="01/14/1990"/>
                                    </div>
                                    <div class="row" style="padding-bottom:1em;">
                                        <label for="writeTaskAuthor">Author :</label>
                                        <input type="text" id="writeTaskAuthor" name="author" class="pull-right" value="Danny" disabled/>
                                    </div>
                                    <div class="row" style="padding-bottom:1em;">
                                        <label for="writeTaskLastEdit">Last Edited :</label>
                                        <input type="date" id="writeTaskLastEdit" name="lastEdit" class="pull-right" value="04/22/2016" disabled/>
                                    </div>
                                </div>
                                <div class="col-md-8">
                                    <label for="dialogueCarousel">Dialogue :</label>

                                    <div id="dialogueCarousel" class="carousel slide" data-ride="carousel" style="background: #C8CFB4;" >

                                        <!-- Wrapper for slides -->
                                        <div id="carouselContent" class="carousel-inner" role="listbox" onclick="$('#writeTaskForm').submit();">
                                            <div class="item active" style="text-align:center;">
                                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam cursus lorem nec turpis interdum,
                                                    ac mattis ipsum ultrices. Phasellus non ex scelerisque, ultricies est in, eleifend mauris.
                                                    Sed nec vulputate quam. Suspendisse nisl risus, sodales vel ante vel, pretium sodales nis</p>
                                            </div>
                                            <div class="item">
                                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam cursus lorem nec turpis interdum,
                                                    ac mattis ipsum ultrices. Phasellus non ex scelerisque, ultricies est in, eleifend mauris.
                                                    Sed nec vulputate quam. Suspendisse nisl risus, sodales vel ante vel, pretium sodales nis</p>
                                            </div>

                                        </div>

                                        <!-- Controls -->
                                        <a class="left carousel-control" href="#dialogueCarousel" role="button" data-slide="prev">
                                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                            <span class="sr-only">Previous</span>
                                        </a>
                                        <a class="right carousel-control" href="#dialogueCarousel" role="button" data-slide="next">
                                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                            <span class="sr-only">Next</span>
                                        </a>
                                    </div> <!-- End Carousel-->

                                </div>

                            </div>
                            <div class="row"> <!-- Summary -->
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Summary : </h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="input-group" style="width:100%;">
                                            <textarea id="writeTaskSummary" readonly="readonly" name="description" class="form-control" style="width:inherit;" maxlength="100">
                                            </textarea>
                                            <span id="editSummary" class="input-group-addon hide">
                                                <a id="editSummaryButton" class="btn btn-default btn-sm" style="padding:0px;">Add Comment</a>
                                            </span>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="row"> <!-- Comments -->
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Comments :</h3>
                                    </div>
                                    <div id="commentsBox" class="panel-body" style="overflow:scroll;">

                                    </div>
                                </div>

                                <div class="input-group" style="width:100%;">
                                    <input id="commentText" type="text" class="form-control" aria-label="Add a comment">
                                    <span class="input-group-addon"><a id="addNewComment" class="btn btn-default btn-sm" style="padding:0px;">Add Comment</a></span>
                                </div>

                            </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button id="redirectWriting" type="submit" class="btn btn-default" onclick="">Start Writing</button>
                        <button id="createButton" type="button" class="btn btn-primary" onclick="formSubmit();">Pre-Draw</button>
                    </div>
                </div> <!-- / End WriteTask -->
            </div> <!-- Modal body -->
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</form>