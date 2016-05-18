<jsp:include page="header.jsp">
    <jsp:param name="title" value="${chapterName}"/>
    <jsp:param name="css" value="/css/readAChapter.css"/>
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="readChapterContainer col-xs-10 col-xs-offset-1">
    <h2 class="col-md-8">
        <ul class="breadcrumb">
            <li><a href="/read"><u>All Comics</u></a></li>
            <li><a href="/create/series/load/${series.seriesID}"><u>${series.title}</u></a></li>
            <li class="active"><u>${chapter.chapterString}</u></li>
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
            <option value="${p.index}">${p.index}</option>
        </c:forEach>
        </select>
    </div>

    <div class="currentPage">
        <c:choose>
            <c:when test="${publishPage.type eq 0}">
                <div class="canvasScreen content-border">
                    <c:forEach var="c" items="${publishPage.canvas}">
                        <canvas class="$">${c.canvasJSON}</canvas>
                    </c:forEach>
                </div>
            </c:when>
            <c:when test="${publishPage.type eq 1}">
                <img class="readPageImg" src="${publishPage.image}"/>
            </c:when>

        </c:choose>
    </div>
</div>

<jsp:include page="footer.jsp"/>