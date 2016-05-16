<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="${searchTitle} Results"/>
    <jsp:param name="css" value="/css/searchPage.css"/>
    <jsp:param name="js" value="/js/searchPage.js"/>
</jsp:include>

<div class="searchResultsList col-xs-9 content-border">

 <c:forEach var="s" items="${seriesResults}">
    <form action="/read/${s.title}" method="post" id="${s.seriesID}" class="searchResult col-xs-12 content-border series filter">
        <c:forEach var="genre" items="${s.genres}">
            <div style="display: none" class="${genre.name}"></div>
        </c:forEach>
        <input type="text" style="display: none;" name="seriesID" value="${s.seriesID}"/>
        <div class="col-xs-2 content-border seriesImg">
            <img src="${s.seriesCover}" class="seriesIcon" />
        </div>
        <div class="col-xs-10">
            <h3 class="seriesTitle">${s.title}</h3>
            <div class="seriesDescription">
                <textarea class="form-control searchDescrip" readonly>${s.description}</textarea>
            </div>
        </div>
    </form>
 </c:forEach>

</div>



<jsp:include page="filterBoxes.jsp">
    <jsp:param name="search" value="search"/>
</jsp:include>


<jsp:include page="footer.jsp"/>