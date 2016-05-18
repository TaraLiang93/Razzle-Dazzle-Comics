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
<input id="reviewTaskHiddenChapterID" type="hidden" name="chapterID" value="${param.chapterID}"/>
<input id="reviewTaskCanvasJsonString" type="hidden" name="canvasJson" value=""/>
<input id="reviewTaskHiddenPageID" type="hidden" name="pageID" value=""/>
<div id="reviewTaskModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 id="reviewTaskPageTitle" class="modal-title pull-left"></h4>
            </div>
            <div class="modal-body">
                <div class="topPreDrawTask center-stage" style="width:95%; margin:auto; ">
                    <div class="row">
                        <div class="pull-left col-md-8">

                            <div class="row">
                                <canvas id="reviewCanvas"></canvas>
                            </div>
                            <div class="row">
                                <jsp:include page="taskDescription.jsp">
                                    <jsp:param name="selector" value="drawTask"/>
                                </jsp:include>
                            </div>

                        </div> <!-- Left Column-->

                        <div class="pull-right col-xs-4">
                            <jsp:include page="taskComments.jsp">
                                <jsp:param name="height" value="50%"/>
                                <jsp:param name="selector" value="reviewTask"/>
                            </jsp:include>
                        </div> <!-- Right Column -->

                    </div> <!-- row -->


                    <div class="modal-footer">
                        <button id="reDrawBtn" type="button" class="btn btn-default" onclick="movePrev($('#reviewTaskHiddenPageID').val(), $('#reviewTaskModal'))">Re-Draw</button>
                        <button id="doneBtn" type="button" class="btn btn-primary" onclick="moveNext($('#reviewTaskHiddenPageID').val(), $('#reviewTaskModal'))">Done</button>
                    </div>
                </div> <!-- / End WriteTask -->
            </div> <!-- Modal body -->
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
