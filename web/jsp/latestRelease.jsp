<%--
  Created by IntelliJ IDEA.
  User: Jason
  Date: 5/14/16
  Time: 1:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Latest Releases"/>
    <jsp:param name="subtitle" value="Latest Realease"/>
    <jsp:param name="css" value="/css/latestRelease.css"/>
    <jsp:param name="js" value="/js/latestRelease.js"/>
</jsp:include>

<jsp:include page="filterBoxes.jsp" />

<div class="col-xs-8 col-xs-offset-2">
    <div class="latestChapters">
        <c:forEach var="s" items="${series}">
            <form  class="latestChapter col-xs-3 filter" id="${s.seriesID}">
                <c:forEach var="genre" items="${s.genres}">
                    <div style="display: none" class="${genre.name}"></div>
                </c:forEach>
                <div class="content-border text-center">
                    <img class="chapterImg" src="${s.seriesCover}"/>
                </div>
                <p class="text-center chapterTitle">${s.title}</p>
            </form>
        </c:forEach>
    </div>
</div>


<jsp:include page="footer.jsp"/>