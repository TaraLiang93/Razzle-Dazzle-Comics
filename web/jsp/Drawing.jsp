<link rel="stylesheet" type="text/css" href="/css/drawing.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script src="/js/drawingtool.js"></script>
<script src="/js/fabric.js"></script>
<script src="/js/drawing.js"></script>


<div class="toolbar">

    <div class="btn-group">
        <a class="btn btn-default" id="Clear">
            <i class="fa fa-trash"></i>
        </a>
        <a class="btn btn-default" id="Draw" data-toggle="popover" data-title="Draw Size"
           data-content="<input id='Line-width'  type='number' value='10' oninput='changeLineWidth()'>"
           data-html="true">
            <i class="fa fa-pencil"></i>
        </a>
    </div>

    <div class="btn-group">
        <a class="btn btn-default active" id="Select">
            <i class="fa fa-mouse-pointer"></i>
        </a>
        <a class="btn btn-default" id="Delete">
            Del
        </a>
    </div>

    <div class="btn-group">
        <a class="btn btn-default" id="Undo">
            <i class="fa fa-undo"></i>
        </a>
        <a class="btn btn-default" id="Redo">
            <i class="fa fa-repeat"></i>
        </a>
    </div>

    <div class="btn-group">
        <a class="btn btn-default" id ="Rectangle">
            <i class="fa fa-square"></i>
            <i class="fa fa-square"></i>
        </a>
        <a class="btn btn-default" id ="Square">
            <i class="fa fa-square-o"></i>
        </a>
    </div>

    <div class="btn-group">
        <a class="btn btn-default" id ="Triangle">
            <i class="fa fa-caret-up"></i>
        </a>
        <a class="btn btn-default" id ="Circle">
            <i class="fa fa-circle-thin"></i>
        </a>
    </div>

    <div class="btn-group">
        <a class="btn btn-default" id ="Line">
            /
        </a>
        <a class="btn btn-default" id ="Text">
            <i class="fa fa-font"></i>
        </a>
    </div>

    <div class="btn-group">
        <a class="btn btn-default" id="Zoom-in">
            <i class="fa fa-search-plus"></i>
        </a>
        <a class="btn btn-default" id="Zoom-out">
            <i class="fa fa-search-minus"></i>
        </a>
    </div>
    <div class="btn-group">
        <a class="btn btn-default" id="Zoom-level"></a>
        <a class="btn btn-default" data-toggle="popover" data-title="Upload file"
           data-content="<input id ='Image-file' type='file' name='pic' accept='image/*'/> "
           data-html="true">
            <i class="fa fa-picture-o"></i>
        </a>
    </div>

        <input class="input form-control btn-default" id="Drawing-color" type="color" oninput="changeColor()">
    <%--<input id='Line-width"  type='number' value='10' oninput='changeLineWidth()'>--%>




</div>

<div class="canvasContainer">
    <canvas id="canvas"></canvas>
</div>