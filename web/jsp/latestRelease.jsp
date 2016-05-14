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
    <jsp:param name="subtitle" value="Latest Release"/>
    <jsp:param name="js" value="/js/latestRelease.js"/>
</jsp:include>

<div class="filterBox col-xs-12 content-border">
    <c:forEach var="genre" items="${genres}">
        <label class="col-xs-3"><input type="checkbox" name="${genre}Genre"/>${genre}<label>
    </c:forEach>
</div>


<jsp:include page="footer.jsp"/>