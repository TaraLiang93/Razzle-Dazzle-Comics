<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--<script src="http://code.jquery.com/jquery-1.12.0.min.js"></script>--%>
<%--<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>--%>

<%--<!-- Latest compiled and minified CSS -->--%>
<%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">--%>

<%--<!-- Optional theme -->--%>
<%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">--%>

<%--<!-- Latest compiled and minified JavaScript -->--%>
<%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>--%>

<%--<link rel="stylesheet" href="/css/commonStyles.css">--%>

<script>
    $(document).ready(function() {
//        $("#reviewTaskModal").modal('show');
    });
</script>

<style>
    .round-button {
        border-radius: 12px;
    }
    .modal-dialog {
        width: 70%;
    }
    .reviewTaskImg,.reviewTaskDialog {
        margin-bottom: 5%;
    }
    .reviewImage {
        height: 45%;
    }
    .reviewImage img{
        height: 100%;
        width: 100%;
    }
</style>

<%--<!-- Button trigger modal -->--%>
<%--<button type="button" class="btn btn-primary btn-md col-sm-8" data-toggle="modal" data-target="#reviewTaskModal">--%>
<%--Launch demo modal--%>
<%--</button>--%>

<!-- Modal -->
    <div class="modal fade" id="reviewTaskModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <input id="reviewTaskHiddenChapterID" type="hidden" name="chapterID" value="${param.chapterID}"/>
        <input id="reviewTaskHiddenPageID" type="hidden" name="pageID" value=""/>
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body" style="height: 85vh">
                    <div class="col-sm-8" style="height: 100%">
                        <div class="col-sm-12 reviewImage">
                            <div class="col-md-12 content-border" style="height: 35vh;">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="reviewTaskDescription">Description</label>
                            <textarea id="reviewTaskDescription" row="12" class="form-control" name="Description" placeholder="Description of Current Page"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="reviewTaskSetting">Add Comment</label>
                            <textarea id="reviewTaskSetting" row="12" class="form-control" name="Setting" placeholder="Setting of Current Page"></textarea>
                        </div>
                        <div class="btn-inline">
                            <a class="btn btn-warning pull-right round-button col-sm-3" type="button"  href="#" >Redraw</a>
                            <a class="btn btn-primary pull-right round-button col-sm-3" type="button"  href="#" >Approve</a>
                        </div>
                    </div>

                    <div class="col-sm-4" style="height: 100%">
                        <div class="reviewTaskDialog">
                            <label>Comments</label>
                            <div class="content-border col-sm-12 reviewComments" style="height: 55vh; overflow-y:auto; padding: 0">
                            </div>
                        </div>
                            <textarea id="reviewTaskText" row="12" class="form-control" name="Text" placeholder="Add a Comment" style="margin-top: 2%">${reviewTaskDescription}</textarea>
                        <div class="btn-inline">
                            <a class="btn btn-xs-4 btn-info pull-right round-button" id="submitComment" type="button"  href="#" >Submit</a>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>

