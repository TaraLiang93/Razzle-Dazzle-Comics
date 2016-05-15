<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jason
  Date: 5/15/16
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="${currentGenre.name} Genre"/>
    <jsp:param name="css" value="/css/genre.css"/>
</jsp:include>

<h2>${currentGenre.name} comics</h2>
<div class="genreList col-xs-12 content-border">
    <div class="col-xs-8 col-xs-offset-2">
        <c:if test="${currentGenre.series ne null}">
        <c:forEach var="series" items="${currentGenre.series}">
            <div class="col-xs-3 genre" class="genreForm">
                <div class="content-border text-center">
                    <img class="genreImg" src="${series.seriesCover}"/>
                </div>
                <p class="text-center genreName">${series.title}</p>
                <input type="text" name="genreID" value="${series.seriesID}" style="display: none;"/>
            </div>
        </c:forEach>
        </c:if>
    </div>
</div>

<jsp:include page="footer.jsp"/>