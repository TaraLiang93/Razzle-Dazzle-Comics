<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="${searchTitle} Results"/>
    <jsp:param name="css" value="/css/searchPage.css"/>
</jsp:include>


<div class="searchResultsList col-xs-9 content-border">

 <c:forEach var="s" items="${seriesResults}">
    <div class="love"
 </c:forEach>

</div>

<div class="refineSerch col-xs-2 content-border">
    <h3>Refine Search</h3>
    <div class="input-group">
        <p>Author Name</p>
        <input type="text" name="filter" class="form-control"/>
    </div>
    <div class="form-group">
        <p>Genre</p>
        <div class=" filterBoxes content-border">
            <c:forEach var="genre" items="${genres}">
                <div class="checkbox">
                    <label type="text"><input type="checkbox" id="${genre}" name="${genre}">${genre}</label>
                </div>
            </c:forEach>
        </div>
    </div>
</div>


<jsp:include page="footer.jsp"/>