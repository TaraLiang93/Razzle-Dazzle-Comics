<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div style="width:100vw;">
    <div style="width:950px; margin:0 auto;">
<div class="drawingContainer">
    <jsp:include page="Drawing.jsp" />
    <div class="drawingSetting">
        <textarea id="SettingScene" name="setting" placeholder="Setting:" readonly="readonly" class="setting form-control content-border">${firstScene.setting}</textarea>
    </div>
    <c:if test="${firstScene}">
        <div id="loadIntoCanvas" style="display: none">${firstScene.canvas.canvasImage}</div>
    </c:if>

</div>
<div class="content-border diaglogBox">
    <div class="content-border diaglogs">
        <c:forEach var="d" items="${fristScene.dialogues}">
            <p class="dialog content-border">${d.dialogue}</p>
        </c:forEach>
    </div>
    <div id="drawingForm" class="btn-group-vertical">
        <a href="#" type="submit" id="saveDrawing" class="btn btn-primary btn-block" >Save</a>
        <a href="#" class="btn btn-warning btn-block">Discard</a>
        <%--<a href="#" class="btn btn-info btn-block">Page Layout</a>--%>
    </div>
</div>

<div class="drawingScenes content-border">
    <div class="scenes">

        <c:forEach var="scene" items="${page.scene}">
            <div class="scene content-border ${scene.id != fristScene.id ? "":"selected"}">
                <h4>Scene ${scene.index}</h4>
                <div class="canvasImage">${scene.canvas.canvasImage}</div>
                <div class="SceneDialogs">
                    <c:forEach var="dialog" items="${scene.dialogues}">
                        <p class="dialog content-border">${dialog.dialogue}</p>
                    </c:forEach>
                </div>
                <div class="sceneSetting">${scene.setting}</div>
            </div>
        </c:forEach>

        <%--<div class="scene content-border selected">--%>
            <%--<h4>Scene 1</h4>--%>
            <%--<div class="canvasImage" >{"objects":[{"type":"circle","originX":"left","originY":"top","left":0,"top":0,"width":150,"height":150,"fill":"#000000","stroke":null,"strokeWidth":1,"strokeDashArray":null,"strokeLineCap":"butt","strokeLineJoin":"miter","strokeMiterLimit":10,"scaleX":1,"scaleY":1,"angle":0,"flipX":false,"flipY":false,"opacity":1,"shadow":null,"visible":true,"clipTo":null,"backgroundColor":"","fillRule":"nonzero","globalCompositeOperation":"source-over","transformMatrix":null,"radius":75,"startAngle":0,"endAngle":6.283185307179586}],"background":""}</div>--%>
            <%--<div class="SceneDialogs">--%>
                <%--<p class="dialog content-border">James: I have a sandwich</p>--%>
                <%--<p class="dialog content-border">Jason: what kind of sandwich</p>--%>
                <%--<p class="dialog content-border">Terrell: nah i'm good</p>--%>
                <%--<p class="dialog content-border">Shakeeb: I can't eat pork</p>--%>
                <%--<p class="dialog content-border">Tara: Chinese food</p>--%>
                <%--<p class="dialog content-border">Ben: off to moes</p>--%>
                <%--<p class="dialog content-border">Danny: I'm ok</p>--%>
                <%--<p class="dialog content-border">Miu Ki: Let's go get tacos</p>--%>
            <%--</div>--%>
            <%--<div class="sceneSetting">Designer Clothes</div>--%>
        <%--</div>--%>
        <%--<div class="scene content-border">--%>
            <%--<h4>Scene 2</h4>--%>
            <%--<div class="canvasImage" >{"objects":[{"type":"rect","left":50,"top":50,"width":20,"height":20,"fill":"green","overlayFill":null,"stroke":null,"strokeWidth":1,"strokeDashArray":null,"scaleX":1,"scaleY":1,"angle":0,"flipX":false,"flipY":false,"opacity":1,"selectable":true,"hasControls":true,"hasBorders":true,"hasRotatingPoint":false,"transparentCorners":true,"perPixelTargetFind":false,"rx":0,"ry":0},{"type":"circle","left":100,"top":100,"width":100,"height":100,"fill":"red","overlayFill":null,"stroke":null,"strokeWidth":1,"strokeDashArray":null,"scaleX":1,"scaleY":1,"angle":0,"flipX":false,"flipY":false,"opacity":1,"selectable":true,"hasControls":true,"hasBorders":true,"hasRotatingPoint":false,"transparentCorners":true,"perPixelTargetFind":false,"radius":50}],"background":"rgba(0, 0, 0, 0)"}</div>--%>
            <%--<div class="SceneDialogs">--%>
                <%--<p class="dialog content-border">What is love</p>--%>
            <%--</div>--%>
            <%--<div class="sceneSetting">I just want to love ohh</div>--%>
        <%--</div>--%>


    </div>
</div>
</div>
</div>
<jsp:include page="footer.jsp" />
