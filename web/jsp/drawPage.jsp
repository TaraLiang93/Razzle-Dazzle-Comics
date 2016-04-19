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
    <jsp:param name="js" value="/js/drawpage.js"/>
</jsp:include>

<link rel="stylesheet" type="text/css" href="/css/drawing.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

<script src="/js/drawingtool.js"></script>
<script src="/js/fabric.js"></script>
<script src="/js/drawing.js"></script>
<div class="drawingContainer">
    <jsp:include page="Drawing.jsp" />
    <div class="drawingSetting">
        <textarea id="SettingScene" name="setting" placeholder="Setting:" readonly="readonly" class="setting form-control content-border">Designer Clothes</textarea>
    </div>
</div>
<div class="content-border diaglogBox">
    <div class="content-border diaglogs">
        <p class="dialog content-border">James: I have a sandwich</p>
        <p class="dialog content-border">Jason: what kind of sandwich</p>
        <p class="dialog content-border">Terrell: nah i'm good</p>
        <p class="dialog content-border">Shakeeb: I can't eat pork</p>
        <p class="dialog content-border">Tara: Chinese food</p>
        <p class="dialog content-border">Ben: off to moes</p>
        <p class="dialog content-border">Danny: I'm ok</p>
        <p class="dialog content-border">Miu Ki: Let's go get tacos</p>
    </div>
    <div id="drawingForm" class="btn-group-vertical">
        <a href="#" type="submit" id="saveDrawing" class="btn btn-primary btn-block" >Save</a>
        <a href="#" class="btn btn-warning btn-block">Discard</a>
        <a href="#" class="btn btn-info btn-block">Page Layout</a>
    </div>
</div>

<div class="drawingScenes content-border">The Scenes will go here</div>

<jsp:include page="footer.jsp" />
