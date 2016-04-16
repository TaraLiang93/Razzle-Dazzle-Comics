<%--
  Created by IntelliJ IDEA.
  User: Jason
  Date: 4/15/16
  Time: 8:51 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Draw Page"/>
    <jsp:param name="css" value="/css/drawPage.css" />
</jsp:include>

<link rel="stylesheet" type="text/css" href="/css/drawing.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

<script src="/js/drawingtool.js"></script>
<script src="/js/fabric.js"></script>
<script src="/js/drawing.js"></script>
<div class="drawingContainer">
    <jsp:include page="Drawing.jsp" />
</div>
<div class="content-border diaglogBox">
    <div class="content-border diaglogs">
    </div>
    <form action="#" class=" btn-group-vertical">
        <a href="#" id="saveDrawing" class="btn btn-primary" >Save</a>
        <a href="#" class="btn btn-warning">Discard</a>
        <a href="#" class="btn btn-info">Page Layout</a>
    </form>
</div>

<div class="drawingScenes content-border">The Scenes will go here</div>

<jsp:include page="footer.jsp" />
