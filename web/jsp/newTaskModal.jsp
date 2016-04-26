<%--
  Created by IntelliJ IDEA.
  User: drodrigues
  Date: 4/25/16
  Time: 10:07 PM
  To change this template use File | Settings | File Templates.
--%>
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
        $('#newTaskModal').modal('show');

    });
</script>

    <input type="hidden" id="newTaskchapterID" value="${param.chapterID}"/>
    <input type="hidden" id="newTaskTotalTasks" value="${param.tasks}"/>

    <div id="newTaskModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">New Chapter</h4>
                </div>
                <div class="modal-body">
                    <div class="center-stage row" style="width:90%; margin:auto; margin-bottom: 5em; ">
                        <div class="row input-group">
                            <span class="input-group-addon" id="title-addon">Title : </span>
                            <input type="text" class="form-control" placeholder="Title" aria-describedby="title-addon" value="Title ${param.tasks + 1}">
                        </div>
                        <div class="row input-group">
                            <span class="input-group-addon" id="desc-addon">Description : </span>
                            <textarea class="form-control" placeholder="Description" aria-describedby="desc-addon" rows="3">
                            </textarea>
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
