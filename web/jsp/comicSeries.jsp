<%--
  Created by IntelliJ IDEA.
  User: tara
  Date: 5/13/16
  Time: 9:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Comic Series"/>
        <jsp:param name="css" value="/css/comicSeries.css"/>
        <jsp:param name="js" value="/js/comicSeries.js"/>
    </jsp:include>
<div class="container-fluid">
    <p style="display: none" id="seriesTit">${series.title}</p>
    <div class="container-fluid row" id="outer">

        <%--RIGHT SIDE: SERIES INFO--%>
        <div class="pull-right container col-sm-4" id="seriesInfo">
            <div id="seriesImg">
                <%--<img src="http://placehold.it/250x350" style="max-height: 100%; max-width: 100%;"> &lt;%&ndash;this is temporary&ndash;%&gt;--%>
                <img src="${series.seriesCover}" id="img">
            </div>

            <label for="genre" style="font-size: 20px; margin:0;">Genre: </label>
            <div id="genre">
                <p id="genreList">
                    <c:choose>
                        <c:when test="${series.genres ne null}">
                            <c:forEach var="genre" items="${series.genres}">
                                <form action="/read/genres/${genre.name}" method="post">
                                    <button type="submit">${genre.name}</button>
                                    <input type="text" style="display: none;" name="genreID" value="${genre.id}"/>
                                </form>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            This is hard data remove me and add JSTL code here
                        </c:otherwise>
                    </c:choose>
                </p>
            </div>

            <label for="description" style="font-size: 20px; margin:0;">Description: </label>
            <div id="description">
                <p id="descrText">${series.description}</p>
            </div>

        </div>

        <%--LEFT SIDE: SERIES CHAPTER   --%>
        <div class="pull-left col-sm-8" id="left">
            <div id="seriesTitle">
                    <%--<h1>${series.title}</h1>--%>
                <h1>${series.title}</h1>  <%--TITLE GOES HERE --%>
                <h5 style="padding:30px 0 0 10px;">Auth: ${series.author}, Art: ${series.artist}</h5> <%--Author and Artist  GOES HERE but need some space --%>
            </div>

            <br>


            <div class="pull-left container" id="seriesChapter">
                <c:forEach var="chapter" items="${chapters}">
                    <div class="chapter" id="${chapter.chapterId}" >
                        <div class="pull-left col-xs-3">
                            <img class="chapterImg" src="${chapter.chapterCoverString}">
                        </div>
                        <div class="col-xs-9">
                            <div class="col-xs-12">
                                <h3 id="chapterTitle col-xs-4">${chapter.title}</h3>
                                <p class="stringAndStatus col-xs-3">${chapter.chapterString}</p>
                                    <%--<p class="chapterDate pull-right">${chapter.createdDate}</p>--%>
                            </div>
                            <div class="chapterDescription col-xs-12">
                                <p id="chapterDescr">${chapter.description}</p>
                            </div>
                        </div>

                    </div>
                </c:forEach>
            </div>


        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
