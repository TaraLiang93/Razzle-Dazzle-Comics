<%--
  Created by IntelliJ IDEA.
  User: tara
  Date: 4/11/16
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Series Page"/>
        <jsp:param name="css" value="/css/seriesPage.css"/>
        <jsp:param name="js" value="/js/seriesPage.js"/>
    </jsp:include></head>
<body>
    <div class="container-fluid">
        <div class="container-fluid" id="outer">

            <%--RIGHT SIDE: SERIES INFO--%>
            <div class="pull-right container" id="seriesInfo">
                <div id="seriesImg">
                    <img src="http://placehold.it/250x250" id="img">
                    <a class="btn btn-link" id="uploadButton">
                        <i class="fa fa-upload fa-2x" aria-hidden="true"></i>
                    </a>
                </div>

                <div id="title">
                    <h1>Title Of Series</h1>
                </div>

                <div id="description">
                    <p id="descrText">Description of series goes here.</p>
                    <a class="btn btn-link updateDescr" id="updateDescr">
                        <i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i>
                    </a>
                </div>

                 <button type="button" class="btn btn-default addUnpublish">Add Chapter</button>
                 <button type="button" class="btn btn-default addUnpublish">Unpublish Series</button>
            </div>

             <%--LEFT SIDE: SERIES CHAPTER   --%>
            <div class="pull-left container" id="seriesChapter">
                <div class="chapter">
                    <img class="pull-left" id="chapterImg" src="http://placehold.it/150x150">
                    <div>
                        <h2 id="chapterTitle">Chapter Title</h2>
                    </div>
                    <div>
                        <p class="stringAndStatus">Chapter String</p>
                        <p class="stringAndStatus pull-right">Chapter Status</p>
                    </div>
                    <p id="chapterDescr">Description</p>
                    <a class="btn btn-link updateDescr">
                        <i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <%--MODALS:--%>
    <div class="modal fade" id="descrModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Description</h4>
                </div>
                <div class="modal-body">
                    <textarea id="textboxDescr"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal"id="saveDescr">Save</button>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" id="imgModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Series Image</h4>
                </div>
                <div class="modal-body">
                    <img id="imgPreview" src="">
                    <input id="browseImg" type="file" accept="image/*">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal"id="saveImg">Save</button>
                </div>
            </div>
        </div>
    </div>


</body>
</html>
