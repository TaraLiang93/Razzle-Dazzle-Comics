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

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="/js/testModal.js"></script>
    <title>Title</title>
</head>
<body>

<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal" id="uploadChap">Open Modal</button>


<%--MODAL FOR TEAM--%>
<div class="modal fade" id="uploadChapterModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Upload Chapter</h4>
            </div>
            <div class="modal-body" id="teamBody">
                <div id="teamTop">
                    <img id="chapterIcon" src="/img/chapter_default.jpeg" onclick="$('#browseChapIcon').click();">
                    <input id="browseChapIcon" name="ChapterIcon"  class="hide" type="file" accept="image/*">
                </div>
                <div id="pageUploader">

                </div>
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
