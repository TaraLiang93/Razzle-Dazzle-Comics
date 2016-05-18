<jsp:include page="header.jsp">
    <jsp:param name="title" value="${chapterName}"/>
    <jsp:param name="css" value="/css/readAChapter.css"/>
    <jsp:param name="js" value="/js/readAChapter.js" />
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="readChapterContainer col-xs-10 col-xs-offset-1">
    <h2 class="col-md-8">
        <ul class="breadcrumb">
            <li><a href="/read"><u>All Comics</u></a></li>
            <li><form class="gotoSeriesForm" style="display: inline" action="/read/${series.title}" method="post">
                <input type="hidden" name="seriesID" value="${series.seriesID}"/>
                <a class="goToSeries"><u>${series.title}</u></a>
            </form></li>
            <li class="active"><u>${chapter.title}</u></li>
        </ul>
    </h2>

    <div class="form-group col-md-4 pull-right">
        <select class="chapterString form-control">
            <option>Choose A Chapter</option>
            <c:forEach var="c" items="${series.chapters}">
                <option value="${c.chapterString}">${c.chapterString}</option>
            </c:forEach>
        </select>

        <select class="pageNumber form-control">
            <option>select page</option>
        <c:forEach var="p" items="${chapter.publishPages}">
            <option value="${p.index}" ${publishPage.index eq p.index ? 'selected' : ''}>${p.index+1}</option>
        </c:forEach>
        </select>
    </div>

    <div class="currentPage">
        <c:choose>
            <c:when test="${publishPage.type eq 'CANVAS_TYPE'}">
                <div class="canvasScreen content-border">
                    <c:forEach var="c" items="${publishPage.canvas}">
                        <canvas class="$">${c.canvasJSON}</canvas>
                    </c:forEach>
                </div>
            </c:when>
            <c:when test="${publishPage.type eq 'IMAGE_TYPE'}">
                <img  class="readPageImg col-md-8 col-md-offset-2" src="${publishPage.image}"/>
            </c:when>

        </c:choose>
    </div>
</div>

<form class="pageForm" style="display: none" action="/read/${series.title}/${chapter.chapterId}" method="post">
<input type="hidden" name="chapterID" id="chapterID" value="${chapter.chapterId}" />
<input type="hidden" id="pageNum" name="pageNum" />
<input type="hidden" name="seriesID" value="${series.seriesID}" />
</form>

<form class="chapterForm" style="display: none" method="post">
    <input type="hidden" name="seriesID" value="${series.seriesID}"/>
</form>


<jsp:include page="footer.jsp"/>