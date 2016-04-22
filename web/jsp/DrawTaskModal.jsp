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
        $("#drawTaskModal").modal('hide');
    });
</script>

<style>
    .round-button {
        border-radius: 12px;
    }
    .modal-dialog {
        width: 70%;
    }
    .drawTaskImg,.drawTaskDialog {
        margin-bottom: 5%;
    }
</style>

<%--<!-- Button trigger modal -->--%>
<%--<button type="button" class="btn btn-primary btn-md col-sm-8" data-toggle="modal" data-target="#drawTaskModal">--%>
    <%--Launch demo modal--%>
<%--</button>--%>

<!-- Modal -->
<form id="seriesForm" method="post" action="#" enctype="multipart/form-data">
<div class="modal fade" id="drawTaskModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Draw Task</h4>
            </div>
            <div class="modal-body" style="height: 60%">
                <div class="col-sm-8" style="height: 100%">
                    <h4 class="">${drawTaskPageTitle eq null ? "Page Title" : drawTaskPageTitle}</h4>
                    <br>
                    <div class="form-group">
                        <label for="drawTaskDescription">Description</label>
                        <textarea id="drawTaskDescription" row="12" class="form-control" name="Description" placeholder="Description of Current Page">${drawTaskDescription}</textarea>
                    </div>
                    <div class="form-inline">
                        <label for="createByLabel" >Created By:</label>
                        <div class="form-group">
                        <input type="text" id="createByLabel" name="createByLabel" class="form-control" value="${drawTaskAuthor}"/>
                        </div>
                    </div>
                    <div class="form-inline">
                        <label for="createOnLabel" >Created On:</label>
                        <div class="form-group">
                        <input type="text" id="createOnLabel" name="createOnLabel" class="form-control" value="${drawTaskDate}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="drawTaskComments">Comments</label>
                        <textarea id="drawTaskComments" row="12" class="form-control" name="comments" placeholder="comments from the beginning of the chapter" style="height: 85px;">${drawTaskComment}</textarea>
                    </div>
                    <a class="btn btn-default pull-right round-button" type="button"  href="#" >Add Comment</a>
                </div>

                <div class="col-sm-4" style="height: 100%">
                    <div class="col-sm-12 drawTaskImg">
                        <img src="${drawTaskImg eq null ? "http://placehold.it/150x150" : drawTaskImg}" id="drawTaskImage">
                    </div>
                    <div class="col-sm-12 drawTaskDialog">
                    <label>Dialog</label>
                        <div class="content-border col-sm-12" style="height: 125px; overflow-y:auto">
                            <c:forEach var="i" items="${drawTaskDialogs}">
                                <p class="dialog content-border">${i}</p>
                            </c:forEach>
                        </div>
                    </div>
                        <div class="btn-inline btn-xs-12">
                            <a class="btn btn-xs-4 btn-warning round-button" type="button"  href="#" >Pre-Draw</a>
                            <a class="btn btn-xs-4 btn-primary round-button" type="button"  href="#" >Draw</a>
                            <a class="btn btn-xs-4 btn-info round-button" type="button"  href="#" >Review</a>
                        </div>
                </div>
            </div>


        </div>
    </div>
</div>
</form>
