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
    $('#chapterModal').modal('hide');

});

    function formSubmit(){
        $('#chapterModal').modal('hide');
        var selected = $('.flowButtons input:radio:checked').val();
        $('#flow').val(selected);
        $('#chapterForm').submit();
    }
</script>

<form id="chapterForm" method="post" action="${uploadAction}" enctype="multipart/form-data">
    <input type="hidden" name="seriesID" value="${param.seriesID}"/>
    <div id="chapterModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">New Chapter</h4>
                </div>
                <div class="modal-body">
                    <div class="topNewChapter center-stage row" style="width:90%; margin:auto; margin-bottom: 5em; ">
                            <div class="pull-left" style="width:40%;">
                                <input id="fileUpload" type="file" name="chapterImage" accept="image/*" style="display:none;" onchange="readURL(this, '#chapterImage')">
                                <img id="chapterImage" src="/img/profile_default.png" alt="Profile Image"
                                     style="width:80%; height: inherit; border:3px solid black;" onclick="$('#fileUpload').click();"/>
                            </div>
                            <div class="chapterText pull-right" style="width:60%;">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <label for="chapterTitle">Chapter Title :</label>
                                        <input type="text" id="chapterTitle" name="title" size="50"/>
                                    </div>
                                    <div class="col-xs-2">
                                        <label for="chapterID"># :</label>
                                        <input type="text" id="chapterID" name="chapterID" maxlength="5" size="5"/>
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <label for="chapterTitle">Description : </label>
                                        <textarea id="chapterDescription" name="description" style="width:100%;" maxlength="300"></textarea>
                                    </div>
                                </div>
                        </div>
                    </div>
                    <div class="bottomNewChapter row" style="width:inherit; overflow:visible;">
                        <div class="pull-right" style="margin-right:30px;">
                            <label style="font-weight: bold;">Flow :</label>
                            <div class="flowButtons btn-group" data-toggle="buttons">
                                <input type="hidden" id="flow" name="flow" value="stuff"/>
                                <label class="btn btn-default active">
                                    <input type="radio" name="option" autocomplete="off" checked value="1"> Plan
                                </label>
                                <label class="btn btn-default">
                                    <input type="radio" name="option" autocomplete="off" value="5"> Free-Draw
                                </label>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button id="createButton" type="submit" class="btn btn-primary" onclick="formSubmit();">Create Chapter</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</form>