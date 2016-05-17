<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
<script src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

--%>
<script>
    function formSubmit(){
        $('#seriesModal').modal('hide');
        var selected = $('.flowButtons input:radio:checked').val();
//        if($("#fileUpload").val()==""){
//            $("#seriesImage").attr("src","/img/chapter_default.jpeg");
//            $("#fileUpload").val("/img/chapter_default.jpeg");
//        }
        $('#flow').val(selected);
        $('#seriesForm').submit();
    }
</script>

<form id="seriesForm" method="post" action="${uploadAction}" enctype="multipart/form-data">
    <div id="seriesModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Create New Series</h4>
                </div>
                <div class="modal-body">
                    <div class="topNewChapter center-stage row" style="width:90%; margin:auto; margin-bottom: 5em; ">
                        <div class="seriesImage pull-left" style="width:40%;">
                            <input id="fileUpload" type="file" name="seriesImage" accept="image/*" style="display:none;" onchange="readURL(this, '#seriesImage');" src="/img/chapter_default.jpeg">
                            <img id="seriesImage" src="/img/chapter_default.jpeg" alt="Profile Image" style="width:80%; height: inherit; border:3px solid black;"
                                 onclick="$('#fileUpload').click();"/>
                        </div>
                        <div class="seriesText pull-right" style="width:60%;">
                            <div class="row">
                                <div class="col-xs-10">
                                    <label for="seriesTitle">Series Title :</label>
                                    <input type="text" id="seriesTitle" name="title" size="69"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6">
                                    <label for="seriesAuthor">Author : </label>
                                    <input id="seriesAuthor" type="text" name="author" style="width:100%;"/>
                                </div>
                                <div class="col-xs-6">
                                    <label for="seriesArtist">Artist : </label>
                                    <input id="seriesArtist" type="text" name="artist" style="width:100%;"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <label for="seriesDescription">Description : </label>
                                    <textarea id="seriesDescription" type="text" name="description" style="width:inherit;"></textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <c:forEach var="genre" items="${genres}">
                                        <div class="checkbox">
                                            <label class="col-xs-4"><input type="checkbox" name=${genre.name} value="${genre.name}">${genre.name}</label>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button id="createButton" type="submit" class="btn btn-primary" onclick="formSubmit();">Create Series</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</form>