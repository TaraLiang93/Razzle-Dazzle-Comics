<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jason
  Date: 5/14/16
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Top 100"/>
    <jsp:param name="subtitle" value="Top 100 "/>
    <jsp:param name="css" value="/top100Comics.css"/>
</jsp:include>


<jsp:include page="filterBoxes.jsp" />

<div class="top100Comics">
    <div class="topComic col-xs-4">
        <div class="topComicsList col-xs-12" id="${topSeries.seriesID}">
            <div class="content-border text-center">
                <img class="chapterImg" src="${topSeries.seriesCover}"/>
            </div>
            <p class="text-center chapterTitle">${topSeries.title}</p>
            <p class="text-center numberof Views">${topSeries.views}</p>
        </div>
    </div>

    <div class="col-xs-8">
        <c:forEach var="s" items="${series}">
            <div class="topComicsList col-xs-4" id="${s.seriesID}">
                <div class="content-border text-center">
                    <img class="chapterImg" src="${s.seriesCover}"/>
                </div>
                <p class="text-center chapterTitle">${s.title}</p>
                <p class="text-center numberof Views">${s.views}</p>
            </div>
        </c:forEach>
    </div>

</div>

<jsp:include page="footer.jsp"/>