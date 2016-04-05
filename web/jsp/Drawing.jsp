<jsp:include page="header.jsp">
    <jsp:param name="title" value="bullshit"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="/css/drawing.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script src="/js/drawingtool.js"></script>
<script src="/js/fabric.js"></script>
<script src="/js/drawing.js"></script>


<div class="toolbar">
    <a class="btn btn-default" id="Clear">
        <i class="fa fa-trash"></i>
    </a>
    <a class="btn btn-default" id="Draw" data-toggle="popover" data-title="Draw Size"
       data-content="<input id='Line-width'  type='number' value='10' oninput='changeLineWidth()'>"
       data-html="true">
        <i class="fa fa-pencil"></i>
    </a>
    <a class="btn btn-default" id="Select">
        <i class="fa fa-mouse-pointer"></i>
    </a>
    <a class="btn btn-default" id="Delete">
        Del
    </a>
    <a class="btn btn-default" id="Undo">
        <i class="fa fa-undo"></i>
    </a>
    <a class="btn btn-default" id="Redo">
        <i class="fa fa-repeat"></i>
    </a>
    <a class="btn btn-default" id ="Rectangle">
        <i class="fa fa-square"></i>
        <i class="fa fa-square"></i>
    </a>
    <a class="btn btn-default" id ="Square">
        <i class="fa fa-square-o"></i>
    </a>
    <a class="btn btn-default" id ="Triangle">
        <i class="fa fa-caret-up"></i>
    </a>
    <a class="btn btn-default" id ="Circle">
        <i class="fa fa-circle-thin"></i>
    </a>
    <a class="btn btn-default" id ="Line">
        /
    </a>
    <a class="btn btn-default" id ="Text">
        <i class="fa fa-font"></i>
    </a>
    <a class="btn btn-default" id="Zoom-in">
        <i class="fa fa-search-plus"></i>
    </a>
    <a class="btn btn-default" id="Zoom-out">
        <i class="fa fa-search-minus"></i>
    </a>
    <a class="btn btn-default" id="Zoom-level">
    </a>

        <input class="input form-control btn-default" id="Drawing-color" type="color" oninput="changeColor()">
    <%--<input id='Line-width"  type='number' value='10' oninput='changeLineWidth()'>--%>

    <div>
        more like a button
        <input id ="Image-file" type="file" name="pic" accept="image/*"/>
    </div>
</div>

<div class="canvasContainer">
    <canvas id="canvas"></canvas>
</div>

Hello World!
<a href="/">Click me</a>
