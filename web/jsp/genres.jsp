<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jason
  Date: 5/14/16
  Time: 6:58 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Genre's"/>
    <jsp:param name="css" value="/css/genre.css"/>
    <jsp:param name="js" value="/js/genre.js"/>
</jsp:include>


<h2>GENRE's</h2>
<div class="genreList col-xs-12 content-border">

    <div class="col-xs-8 col-xs-offset-2">
    <c:forEach var="genre" items="${genres}">
        <form action="/read/genres/${genre.name}" method="post" class="col-xs-3 genre">
            <div class="content-border text-center">
                <img class="genreImg" src="${genre.imageSrc}"/>
            </div>
            <p class="text-center genreName">${genre.name}</p>
            <input type="text" name="genreID" value="${genre.id}" style="display: none;"/>
        </form>
    </c:forEach>
    </div>

</div>

<jsp:include page="footer.jsp"/>