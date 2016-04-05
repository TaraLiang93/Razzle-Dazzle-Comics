<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="names" value="//cdn.tinymce.com/4/tinymce.min.js,/js/writing.js" />
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Scribbles"/>
    <jsp:param name="css" value="/css/writing.css"/>
    <jsp:param name="js" value="${names}"/>
</jsp:include>

<jsp:include page="Writing.jsp"/>

<jsp:include page="footer.jsp"/>