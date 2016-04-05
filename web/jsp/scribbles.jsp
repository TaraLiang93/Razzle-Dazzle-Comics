<jsp:include page="header.jsp">
    <jsp:param name="title" value="Scribbles"/>
    <jsp:param name="css" value="/css/writing.css"/>
    <jsp:param name="js" value="//cdn.tinymce.com/4/tinymce.min.js"/>
</jsp:include>


<jsp:include page="Writing.jsp">
    <jsp:param name="items" value="${page.scenes}"/>
</jsp:include>


<jsp:include page="footer.jsp"/>