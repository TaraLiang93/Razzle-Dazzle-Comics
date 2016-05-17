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

    <input id="preDrawTaskHiddenChapterID" type="hidden" name="chapterID" value="${param.chapterID}"/>
    <input id="preDrawTaskHiddenPageID" type="hidden" name="pageID" value=""/>
    <div id="preDrawTaskModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 id="preDrawTaskPageTitle" class="modal-title pull-left"></h4>
                </div>
                <div class="modal-body">
                    <div class="topPreDrawTask center-stage" style="width:95%; margin:auto; ">
                        <div class="row">
                            <div class="pull-left col-md-8">

                                <div class="panel-group" id="preDrawTaskAccordion" role="tablist" aria-multiselectable="true">

                                </div> <!-- End Accordion -->


                            </div> <!-- Left Column-->

                            <div class="pull-right col-xs-4">
                                <jsp:include page="taskComments.jsp">
                                    <jsp:param name="height" value="50%"/>
                                    <jsp:param name="selector" value="preDrawTask"/>
                                </jsp:include>
                            </div> <!-- Right Column -->

                        </div> <!-- row -->


                        <div class="modal-footer">
                            <button id="writingButton" type="button" class="btn btn-default" onclick="movePrev($('#preDrawTaskHiddenPageID').val(), $('#preDrawTaskModal'))">Re-Write</button>
                            <button id="drawButton" type="button" class="btn btn-primary" onclick="moveNext($('#preDrawTaskHiddenPageID').val(), $('#preDrawTaskModal'))">Draw</button>
                        </div>
                    </div> <!-- / End WriteTask -->
                </div> <!-- Modal body -->
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
