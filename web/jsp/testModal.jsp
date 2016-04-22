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
    <title>Title</title>
</head>
<body>


<%--Modal for upload chapter--%>
<div class="modal fade" id="infoModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Upload Chapter</h4>
            </div>
            <div class="modal-body">
                <div class="top">
                    <div class="editImg pull-left">
                        <p style="font-weight: bold">Chapter Icon: </p>
                        <img id="imgPreview" src="">
                        <form id="imgForm" method="post" action="/create/newChapter/updateNewChapterImage" enctype="multipart/form-data">
                            <input name="imgSrc"  id="browseImg" type="file" accept="image/*">
                        </form>
                    </div>

                    <div class="editInfo pull-right">

                    </div>

                </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Save</button>
            </div>
        </div>
    </div>
</div>
</div>

</body>
</html>
