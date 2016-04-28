<%--
  Created by IntelliJ IDEA.
  User: tara
  Date: 4/21/16
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/uploadChapter.css">
    <script src="/js/uploadChapter.js"></script>
</head>
<body>

<%--MODAL FOR TEAM--%>
<div class="modal fade" id="uploadChapterModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Upload Chapter</h4>
            </div>
            <div class="modal-body" id="uploadBody">
                <form action="${uploadChapterAction}" enctype="multipart/form-data" style="height:100%; width:100%;">
                    <div id="uploadInfo">
                        <div class="pull-left" id="imgInfo">
                            <label for="chapterIcon">Chapter Icon:</label>
                            <img class="form-control" id="chapterIcon" src="/img/chapter_default.jpeg" >
                            <%--onclick="$('.browseChapIcon').click();"--%>
                            <input name="chapterIcon"  class="browseChapIcon hide" type="file" accept="image/*">
                        </div>
                        <div class="chapterInfo pull-right">
                            <div class="info">
                                <div class="half">
                                    <label for="chapterTitle">Chapter Title:</label>
                                    <input class="text form-control" id="chapterTitle" name="chapterTitle" value="Chapter Title">
                                </div>
                                <div class="half">
                                    <label for="chapterStr">Chapter String:</label>
                                    <input class="text form-control" id="chapterStr" name="chapterStr" value="Chapter String">
                                </div>
                            </div>

                            <div class="info">
                                <div class="half">
                                    <label for="author">Author:</label>
                                    <input class="text form-control" id="author" name="author" value="Author">
                                </div>
                                <div class="half">
                                    <label for="artist">Artist:</label>
                                    <input class="text form-control" id="artist" name="artist" value="Artist">
                                </div>
                            </div>

                            <div style="margin-left: 3%;">
                                <label for="descr">Description:</label>
                                <textarea class="text form-control" id="descr" name="chapterDescr">Description</textarea>
                            </div>
                        </div>
                </div>
                <div id="upload" >
                    <div class="row-fluid">
                        <div class="uploadEle col-sm-4">
                            <img  id="pageImgPlus" class="imgPage" src="/img/plus_sign.jpg" >
                                 <%--nclick="$('#pageImgInput').click();" >--%>
                            <input class="hide" type="file" accept="image/*">
                        </div>
                    </div>
                </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal"id="saveChapter">Save</button>
            </div>
        </div>
    </div>
</div>



</body>

</html>
