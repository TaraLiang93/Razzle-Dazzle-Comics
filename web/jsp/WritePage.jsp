<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="names" value="//cdn.tinymce.com/4/tinymce.min.js,/js/writing.js" />
<c:set var="style" value="/css/writing.css, /css/scribbles.css" />
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Writing Page"/>
    <jsp:param name="css" value="${style}"/>
    <jsp:param name="js" value="${names}"/>
</jsp:include>



<div class="scribbleWrapper center-block">

    <form:form method="post" action="/create/writePage/save" modelAttribute="writePageModel">

        <input type="hidden" name="parentID" value="${chapterID}"/>

        <jsp:include page="Writing.jsp"/>

    </form:form>

</div>
<jsp:include page="footer.jsp"/>