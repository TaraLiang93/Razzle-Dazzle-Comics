<div class = "btn-toolbar" role = "toolbar" style="display: inline-flex; margin-bottom: 5px;">
    <div class = "btn-group"  style="">
        <div class = "container" style="padding: 0; width: auto;">
            <div class="dropdown" >
                <button class="btn dropdown-toggle" type="button" data-toggle="dropdown" id="CurrentFont">Times New Roman
                    <span class="caret"></span></button>
                <select class="dropdown-menu" id="Font-option" multiple="multiple">
                    <option >Arial</option>
                    <option >Helvetica</option>
                    <option>Gadget</option>
                    <option>Comic Sans MS</option>
                    <option>Courier</option>
                    <option>Georgia</option>
                    <option>Impact</option>
                    <option>Lucida Console</option>
                    <option>Tahoma</option>
                    <option>Geneva</option>
                    <option>Verdana</option>
                    <option>Times New Roman</option>

                </select>
            </div>
        </div>
    </div>

    <div class="btn-group" >
        <a class="btn btn-default" id = "Bold"><i class="fa fa-bold" aria-hidden="true"></i></a>
        <a class="btn btn-default" id = "Italic"><i class="fa fa-italic" aria-hidden="true"></i></a>
        <a class="btn btn-default" id = "Underline"><i class="fa fa-underline" aria-hidden="true"></i></a>
        <a class="btn btn-default" id = "Linethrough"><i class="fa fa-strikethrough" aria-hidden="true"></i></a>
        <a class="btn btn-default" id = "Overline"><p style="margin-bottom: 0;  border-top: 1px solid; color: black; padding:0;">A</p></a>
    </div>

    <div class="btn-group">
        <div class = "container" style="padding: 0; width: auto;">
            <div class="dropdown" >
                <button class="btn dropdown-toggle" type="button" data-toggle="dropdown" id="CurrentBrush">Pencil
                    <span class="caret"></span></button>
                <select class="dropdown-menu" id="Brush-Option" multiple="multiple">
                    <option>Pencil</option>
                    <option >Circle</option>
                    <option >Spray</option>
                    <option>Horizontal Line</option>
                    <option>Vertical Line</option>
                    <option>Pattern</option>
                    <option>Diamond</option>
                    <option>Square</option>
                </select>
            </div>
        </div>
    </div>

    <div class="btn-group">
        <label for="widthRange" id="labelWidth">Size: 10</label>
        <input type="range" id="widthRange" value="10">
    </div>

</div>


<div>
<div class="toolbar">

    <div class="btn-group">
        <a class="btn btn-default" id="Clear" title="Clear Canvas">
            <i class="fa fa-trash"></i>
        </a>
        <a class="btn btn-default" id="Draw" title="Free Draw" data-toggle="popover" data-title="Draw Size"
           data-content="<input id='Line-width'  type='number' value='10' oninput='changeLineWidthDraw()'>"
           data-html="true">
            <i class="fa fa-pencil"></i>
        </a>
    </div>

    <div class="btn-group">
        <a class="btn btn-default" id="Select" title="Select">
            <i class="fa fa-mouse-pointer"></i>
        </a>
        <a class="btn btn-default" id="Delete" title="Delete">
            Del
        </a>
    </div>

    <div class="btn-group">
        <a class="btn btn-default" id="Undo" title="Undo">
            <i class="fa fa-undo"></i>
        </a>
        <a class="btn btn-default" id="Redo" title="Redo">
            <i class="fa fa-repeat"></i>
        </a>
    </div>

    <div class="btn-group">
        <a class="btn btn-default" id ="Rectangle" title="Rectangle">
            <i class="fa fa-stop"></i>
            <i class="fa fa-stop" style="margin-left:-5px; "></i>
        </a>
        <a class="btn btn-default" id ="Square" title="Square">
            <i class="fa fa-stop"></i>
        </a>
    </div>

    <div class="btn-group">
        <a class="btn btn-default" id ="Triangle" title="Triangle">
            <i class="fa fa-play" style="transform:rotateZ(-89deg);;"></i>
        </a>
        <a class="btn btn-default" id ="Circle" title="Circle">
            <i class="fa fa-circle"></i>
        </a>
    </div>

    <div class="btn-group">
        <a class="btn btn-default" id ="Line" style="font-weight: bold;" title="Line">
            /
        </a>
        <a class="btn btn-default" id ="Text" title="Text">
            <i class="fa fa-font"></i>
        </a>
    </div>

    <div class="btn-group">
        <a class="btn btn-default" id="Zoom-in" title="Zoom In">
            <i class="fa fa-search-plus"></i>
        </a>
        <a class="btn btn-default" id="Zoom-out" title="Zoom Out">
            <i class="fa fa-search-minus"></i>
        </a>
    </div>
    <div class="btn-group">
        <a class="btn btn-default" id="Zoom-level"></a>
        <%--<a class="btn btn-default" data-toggle="popover" data-title="Upload file"--%>
           <%--data-content="  "--%>
           <%--data-html="true">--%>
            <%----%>
        <%--</a>--%>
        <a class="imageUpload btn btn-default" title="Import Image">
            <i class="fa fa-picture-o"></i>
            <input id ='Image-file' type='file' name='pic' accept='image/*'/>
        </a>
    </div>
    <div class="btn-group">
        <a class="btn btn-default" id="Eraser" title="Eraser">
            <i class="fa fa-eraser" aria-hidden="true"></i>
        </a>
        <a class="btn btn-default" id="Fill" title="Fill">
            <i class="fa fa-tint" aria-hidden="true"></i>
        </a>
    </div>
    <div class="btn-group">
        <a class="btn btn-default" title="Change Background" data-toggle="popover" data-title="Background:"
           data-content="<input id ='Bg-Image' type='file' name='pic' accept='image/*'/>
            <input class='input btn-default' id='Bg-Color' type='color'/>"
           data-html="true" id="Bg-popover">
            <i class="fa fa-bitbucket" aria-hidden="true"></i>
        </a>

    </div>
    <%--<div class="dropdown" >--%>
        <%--<button class="btn dropdown-toggle" type="button" data-toggle="dropdown" id="CurrentFont">Times New Roman--%>
            <%--<span class="caret"></span></button>--%>
        <%--<select class="dropdown-menu" id="Font-option" multiple="multiple">--%>
            <%--<option >Arial</option>--%>
            <%--<option >Helvetica</option>--%>
            <%--<option>Gadget</option>--%>
            <%--<option>Comic Sans MS</option>--%>
            <%--<option>Courier</option>--%>
            <%--<option>Georgia</option>--%>
            <%--<option>Impact</option>--%>
            <%--<option>Lucida Console</option>--%>
            <%--<option>Tahoma</option>--%>
            <%--<option>Geneva</option>--%>
            <%--<option>Verdana</option>--%>
            <%--<option>Times New Roman</option>--%>

        <%--</select>--%>
    <%--</div>--%>
        <input class="input form-control btn-default" id="Drawing-color" type="color" oninput="changeColor()" title="Change Color">
    <%--<input id='Line-width"  type='number' value='10' oninput='changeLineWidth()'>--%>


</div>

<div id="canvasContainer" style ="height:600px; width:900px;    overflow: scroll;">
    <canvas id="canvas" style="border-style: solid" height="900px" width=" 600px"></canvas>
</div>
</div>

