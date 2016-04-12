<jsp:include page="header.jsp">
        <jsp:param name="title" value="Testing"/>
        <jsp:param name="css" value="/css/navbar.css"/>
</jsp:include>

    <script src="/js/drawingtool.js"></script>
    <script src="/js/fabric.js"></script>
    <script src="/js/drawing.js"></script>

    <div id="canvasContainer" style ="height:600px; width:600px;    overflow: auto;">
        <canvas id="canvas" style="border-style: solid" height="600px" width=" 600px"></canvas>
    </div>


    <button id="Clear">Clear</button>
    <button id="Draw">Draw</button>
    <button id="Select">Select</button>
    <button id="Delete">Delete</button>
    <button id="Undo">Undo Delete</button>
    <button id="Redo">Redo Delete</button>

    <button id ="Rectangle">Rectangle</button>
    <button id = "Square">Square</button>
    <button id = "Triangle">Triangle</button>
    <button id = "Circle">Circle</button>
    <button id = "Line">Line</button>

    <button id = "Text">Text</button>
    <button id="Zoom-in">Zoom In</button>
    <button id="Zoom-out">Zoom Out</button>

<p>JASON DOES NOT HAVE THE FOLLOWING</p>


    <button id ="Eraser">Eraser</button>
    <button id="Fill">Fill</button>

    <div class = "container">
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

    <div class = "container">
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

    <a class="btn btn-default" data-toggle="popover" data-title="Background:"
       data-content="<input id ='Bg-Image' type='file' name='pic' accept='image/*'/>
            <button class='btn btn-default btn-sm pull-right' id='Bg-Submit'>Submit</button>
            <input class='input form-control btn-default' id='Bg-Color' type='color'/>"
       data-html="true" id="Bg-popover">Background
    </a>



    <button id = "Bold">Bold</button>
    <button id = "Italic">Italic</button>
    <button id = "Underline">Underline</button>
    <button id = "Linethrough">Linethrough</button>
    <button id = "Overline">Overline</button>


    <p id="Zoom-level"></p>
    <input id="Drawing-color" type="color">
    <input id="Line-width" type="number">

    <p> Input Image:<input id ="Image-file" type="file" name="pic" accept="image/*">
    </p>

<jsp:include page="footer.jsp" />
