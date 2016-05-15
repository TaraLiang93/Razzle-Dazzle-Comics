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
</jsp:include>


<h2>Genre's</h2>
<div class="genreList col-xs-12 content-border">

    <c:forEach var="genre" items="${genres}">
        <div class="col-xs-2 genre" id="${genre.name}">
            <div class="content-border text-center">
                <%--<img class="genreImg" src="${genre}"/>--%>
            </div>
            <p class="text-center genreName">${genre.name}</p>
        </div>
    </c:forEach>

</div>

<jsp:include page="footer.jsp"/>