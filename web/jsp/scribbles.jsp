<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="names" value="//cdn.tinymce.com/4/tinymce.min.js,/js/writing.js" />
<c:set var="style" value="/css/writing.css, /css/scribbles.css" />
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Scribbles"/>
    <jsp:param name="css" value="${style}"/>
    <jsp:param name="js" value="${names}"/>
</jsp:include>

<jsp:include page="Writing.jsp"/>


<jsp:include page="footer.jsp"/>