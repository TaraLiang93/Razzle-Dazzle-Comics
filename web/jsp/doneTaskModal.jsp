<%--
  Created by IntelliJ IDEA.
  User: tara
  Date: 5/17/16
  Time: 3:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<input id="doneTaskHiddenChapterID" type="hidden" name="chapterID" value="${param.chapterID}"/>
<input id="doneTaskHiddenPageID" type="hidden" name="pageID" value=""/>
<div id="doneTaskModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 id="doneTaskPageTitle" class="modal-title pull-left"></h4>
            </div>
            <div class="modal-body" style="overflow-y:scroll">
                <div class="topDoneTask center-stage" style="width:95%; margin:auto; ">
                    <div class="row">
                        <div class="pull-left col-md-8 preview">
                                <%--<canvas></canvas>--%>
                        </div> <!-- Left Column-->

                    </div> <!-- row -->

                    <div class="modal-footer">
                        <button id="redoBtn" type="button" class="btn btn-default" onclick="movePrev($('#doneTaskHiddenPageID').val(), $('#doneTaskModal'))">Review</button>
                    </div>
                </div> <!-- / End WriteTask -->
            </div> <!-- Modal body -->
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
