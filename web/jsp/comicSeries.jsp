<%--
  Created by IntelliJ IDEA.
  User: tara
  Date: 5/13/16
  Time: 9:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Comic Series"/>
        <jsp:param name="css" value="/css/comicSeries.css"/>
        <jsp:param name="js" value="/js/comicSeries.js"/>
    </jsp:include>
</head>
<body>
<div class="container-fluid">
    <p style="display: none" id="seriesTit">${series.title}</p>
    <div class="container-fluid row" id="outer">

        <%--RIGHT SIDE: SERIES INFO--%>
        <div class="pull-right container col-sm-4" id="seriesInfo">
            <div id="seriesImg">
                <img src="http://placehold.it/250x350" style="max-height: 100%; max-width: 100%;"> <%--this is temporary--%>
                <img src="${series.seriesCover}" id="img">
            </div>

            <label for="genre" style="font-size: 20px; margin:0;">Genre: </label>
            <div id="genre">
                <p id="genreList">This is hard data remove me and add JSTL code here</p>
            </div>

            <label for="description" style="font-size: 20px; margin:0;">Description: </label>
            <div id="description">
                <p id="descrText">This is a description blahhh. When there is actual data plz remove me${series.description}</p>
            </div>

        </div>

        <%--LEFT SIDE: SERIES CHAPTER   --%>
        <div class="pull-left col-sm-8" id="left">
            <div id="seriesTitle">
                    <%--<h1>${series.title}</h1>--%>
                <h1>Title ${series.title}</h1>  <%--TITLE GOES HERE --%>
                <h3 style="padding:10px 0 0 10px;">Author, Artist</h3> <%--Author and Artist  GOES HERE but need some space --%>
            </div>

            <br>


            <div class="pull-left container" id="seriesChapter">
                <c:forEach var="chapter" items="${chapters}">
                    <div class="chapter" id="${chapter.chapterId}" ${chapter.published == false ? "style='display:none;'" : ""}>
                        <img class="pull-left chapterImg" src="${chapter.chapterCover}">
                        <div>
                            <h2 id="chapterTitle">Chapter Title${chapter.title}</h2>
                        </div>
                        <div>
                            <p class="stringAndStatus">${chapter.chapterString}</p>
                        </div>
                        <p id="chapterDescr">${chapter.description}</p>

                    </div>
                </c:forEach>
            </div>


        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
