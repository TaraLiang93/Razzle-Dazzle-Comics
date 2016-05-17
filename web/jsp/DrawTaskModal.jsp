<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--
<script src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

<link rel="stylesheet" href="/css/commonStyles.css">

-->
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


<!-- Modal -->
<form id="seriesForm" method="post" action="/create/drawPage/load" enctype="multipart/form-data">
    <input id="drawTaskHiddenChapterID" type="hidden" name="chapterID" value="${param.chapterID}"/>
    <input id="drawTaskHiddenPageID" type="hidden" name="pageID" value=""/>
<div class="modal fade" id="drawTaskModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Draw Task</h4>
            </div>
            <div class="modal-body" style="height: 60%">
                <div class="col-sm-8" style="height: 100%">
                    <h4 class=""></h4>
                    <br>
                    <div class="form-group">
                        <jsp:include page="taskDescription.jsp">
                            <jsp:param name="selector" value="drawTask"/>
                        </jsp:include>
                    </div>
                    <div class="form-inline">
                        <label for="createByLabel" >Created By:</label>
                        <div class="form-group">
                        <input type="text" id="createByLabel" name="createByLabel" class="form-control" value="Jason" readonly/>
                        </div>
                    </div>
                    <div class="form-inline">
                        <label for="createOnLabel" >Created On:</label>
                        <div class="form-group">
                        <input type="date" id="createOnLabel" name="createOnLabel" class="form-control" value="01/12/1990" readonly/>
                        </div>
                    </div>
                    <br/>
                    <div class="form-group">
                        <jsp:include page="taskComments.jsp">
                            <jsp:param name="selector" value="drawTask"/>
                            <jsp:param name="height" value="20%;"/>
                        </jsp:include>
                    </div>
                </div>

                <div class="col-sm-4" style="height: 100%">
                    <div class="col-sm-12 drawTaskImg">
                        <img src="${drawTaskImg eq null ? "http://placehold.it/150x150" : drawTaskImg}" id="drawTaskImage">
                    </div>
                    <div class="col-sm-12 drawTaskDialog">
                    <label>Dialog</label>
                        <div class="content-border col-sm-12" style="height: 125px; overflow-y:auto">
                            <c:forEach var="i" items="">
                                <p class="dialog content-border">${i}</p>
                            </c:forEach>
                        </div>
                    </div>
                        <div class="btn-inline btn-xs-12">
                            <a class="btn btn-xs-4 btn-default round-button" type="button"  onclick="movePrev($('#hiddenPageID').val(), $('#drawTaskModal'));" >Pre-Draw</a>
                            <input class="btn btn-xs-4 btn-info round-button" type="submit" value="Draw"/>
                            <a class="btn btn-xs-4 btn-primary round-button" type="button"  onclick="moveNext($('#hiddenPageID').val(), $('#drawTaskModal'));" >Review</a>
                        </div>
                </div>
            </div>


        </div>
    </div>
</div>
</form>
