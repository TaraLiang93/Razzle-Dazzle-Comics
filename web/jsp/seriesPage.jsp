<%--
  Created by IntelliJ IDEA.
  User: tara
  Date: 4/11/16
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
        <jsp:param name="title" value="Series Page"/>
        <jsp:param name="css" value="/css/seriesPage.css"/>
        <jsp:param name="js" value="/js/seriesPage.js"/>
</jsp:include>
    <div class="container-fluid">
        <div class="container-fluid" id="outer">

            <%--RIGHT SIDE: SERIES INFO--%>
            <div class="pull-right container" id="seriesInfo">
                <div id="seriesImg">
                    <img src="${series.seriesCover}" id="img">
                    <a class="btn btn-link" id="uploadButton">
                        <i class="fa fa-upload fa-2x" aria-hidden="true"></i>
                    </a>
                </div>

                <div id="title">
                    <h1>${series.title}</h1>
                </div>

                <div id="description">
                    <p id="descrText">${series.description}</p>
                    <a class="btn btn-link updateDescr" id="updateDescr">
                        <i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i>
                    </a>
                </div>

                 <button id="addChapter" type="button" class="btn btn-default addUnpublish">Add Chapter</button>
                <button id="uploadChapter" type="button" class="btn btn-default addUnpublish">Upload Chapter</button>
                 <button type="button" class="btn btn-default addUnpublish">${series.published eq true ? "Unpublish Series" : "Publish Series"}</button>
            </div>

             <%--LEFT SIDE: SERIES CHAPTER   --%>
            <div class="pull-left container" id="seriesChapter">

                <c:forEach var="chapter" items="${chapters}">
                    <div class="chapter" id="${chapter.chapterId}">
                        <img class="pull-left chapterImg" src="${chapter.chapterCover}">
                        <div>
                            <h2 id="chapterTitle">Chapter Title${chapter.title}</h2>
                        </div>
                        <div>
                            <p class="stringAndStatus">${chapter.chapterString}</p>
                            <p class="stringAndStatus pull-right">${chapter.published ? "Published" : "Publish"}</p>
                        </div>
                        <p id="chapterDescr">${chapter.description}</p>
                        <a class="btn btn-link updateDescr">
                            <i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i>
                        </a>
                    </div>
                </c:forEach>


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
                    <form id="imgForm" method="post" action="/create/series/updateSeriesImage" enctype="multipart/form-data">
                        <input name="imgSrc"  id="browseImg" type="file" accept="image/*">
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal"id="saveImg">Save</button>
                </div>
            </div>
        </div>
    </div>

<jsp:include page="newChapterModal.jsp">
    <jsp:param name="seriesID" value="${series.seriesID}"/>
</jsp:include>

<jsp:include page="uploadChapter.jsp">
    <jsp:param name="seriesID" value="${series.seriesID}"/>
</jsp:include>

<jsp:include page="footer.jsp"/>