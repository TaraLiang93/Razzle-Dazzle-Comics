<jsp:include page="header.jsp">
    <jsp:param name="title" value="Razzle Dazzle Comics"/>
    <jsp:param name="css" value="/css/homepage.css"/>
</jsp:include>

<div class="container">
    <a class="signin" href="${logInURL}">SIGN IN</a>
</div>

<h1 class="RDC">RDC <br>Read Design Collaborate</h1>

<div class="selection">
    <div class="main creation">
        create
    </div>

    <div class="main reading">
        read
    </div>
</div>

<jsp:include page="footer.jsp"/>