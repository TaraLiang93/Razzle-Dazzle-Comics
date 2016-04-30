<jsp:include page="header.jsp">
    <jsp:param name="title" value="${chapterName}"/>
    <jsp:param name="css" value="/css/readAChapter.css"/>
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="readChapterContainer col-md-8">
    <h2 class="col-md-8">
        <ul class="breadcrumb">
            <li><a href="/read">All Comics</a></li>
            <li><a href="/create/series/load/${seriesID}">${seriesName}</a></li>
            <li class="active">${chapterStringName}</li>
        </ul>
    </h2>

    <div class="form-group col-md-4">
        <select class="chapterString">
        <c:forEach var="c" items="${chapters}">
            <option value="${c.chapterString}">${c.chapterString}</option>
        </c:forEach>
        </select>
        <select class="pageNumber">
        <c:forEach var="p" items="${pages}">
            <option value=""></option>
        </c:forEach>
        </select>
    </div>

    <div class="page col-lg-12">
        <div class="canvasScreen content-border">
            <canvas></canvas>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>