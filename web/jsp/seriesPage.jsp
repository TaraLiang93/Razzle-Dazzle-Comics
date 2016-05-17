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

            <%--LEFT SIDE: SERIES CHAPTER   --%>
            <div class="col-xs-9 container" id="seriesChapter">

                <c:forEach var="chapter" items="${chapters}">
                    <form action="/create/chapter/load/${chapter.chapterId}" class="chapter" id="${chapter.chapterId}">
                        <input type="text" style="display: none;" name="seriesID" value="${series.seriesID}" />
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
                    </form>
                </c:forEach>


            </div>

            <%--RIGHT SIDE: SERIES INFO--%>
            <div class="col-xs-3 container" id="seriesInfo">
                <div id="seriesImg">
                    <form id="imgForm" method="post" action="${uploadSeriesImageAction}" enctype="multipart/form-data">
                        <input id="series" type="hidden" name="seriesID" value="${series.seriesID}"/>
                        <img src="${series.seriesCover}" id="imgPreview">
                        <input name="imgSrc" id="browseImg" type="file" accept="image/*" class="hide">
                        <a class="btn btn-link" id="uploadButton">
                            <i class="fa fa-upload fa-2x" aria-hidden="true"></i>
                        </a>
                    </form>
                </div>

                <div id="title">
                    <h1>${series.title}</h1>
                </div>

                <div id="Authors">
                    <h5 class="text-center">Author: ${series.author ne null ? series.author : ""}</h5>
                </div>
                <div id="Artist">
                    <h5 class="text-center">Artist: ${series.artist ne null ? series.artist : ""}</h5>
                </div>
                <div id="description">
                    <p id="descrText">${series.description}</p>
                    <a class="btn btn-link updateDescr" id="updateDescr">
                        <i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i>
                    </a>
                </div>

                 <button id="addChapter" type="button" class="btn btn-primary addUnpublish">Add Chapter</button>
                <button id="uploadChapter" type="button" class="btn btn-default addUnpublish">Upload Chapter</button>
                 <button type="button" title="${series.published ne true ? "Unpublish" : "Publish"}" class="btn btn-info addUnpublish publishToggle">${series.published eq true ? "Unpublish" : "Publish"}</button>
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


<jsp:include page="newChapterModal.jsp">
    <jsp:param name="seriesID" value="${series.seriesID}"/>
</jsp:include>

<jsp:include page="uploadChapter.jsp">
    <jsp:param name="seriesID" value="${series.seriesID}"/>
</jsp:include>

<div id="seriesID" style="display: none">${series.seriesID}</div>

<jsp:include page="footer.jsp"/>