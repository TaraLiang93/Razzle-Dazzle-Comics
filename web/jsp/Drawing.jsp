<jsp:include page="header.jsp">
    <jsp:param name="title" value="bullshit"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="/css/drawing.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script src="/js/drawingtool.js"></script>
<script src="/js/fabric.js"></script>

<div class="toolbar">
<a class="btn btn-default" id="Clear">
    <i class="fa fa-mouse-pointer"></i>
</a>
    <a class="btn btn-default" id="Draw">
        <i class="fa fa-pencil"></i>
    </a>
    <a class="btn btn-default" id="Select">

    </a>
    <a class="btn btn-default" id="Delete">

    </a>
    <a class="btn btn-default" id="Undo">

    </a>
    <a class="btn btn-default" id="Redo">

    </a>
    <a class="btn btn-default" id ="Rectangle">

    </a>
    <a class="btn btn-default" id ="Square">

    </a>
    <a class="btn btn-default" id ="Triangle">

    </a>
    <a class="btn btn-default" id ="Circle">

    </a>
    <a class="btn btn-default" id ="Line">

    </a>
    <a class="btn btn-default" id ="Text">

    </a>
    <a class="btn btn-default" id="Zoom-in">

    </a>
    <a class="btn btn-default" id="Zoom-out">

    </a>
    <a class="btn btn-default" id="Zoom-level">

    </a>
    <input class="input" id="Drawing-color" type="color" oninput="changeColor()">
    <input id="Line-width"  type="number" oninput="changeLineWidth()">

    <span>
        Input Image:
        <input id ="Image-file" type="file" name="pic" accept="image/*"/>
    </span>
</div>

<div class="canvasContainer">
    <canvas id="canvas"></canvas>
</div>

Hello World!
<a href="/">Click me</a>
