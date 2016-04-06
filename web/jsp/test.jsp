<jsp:include page="header.jsp">
        <jsp:param name="title" value="Testing"/>
        <jsp:param name="css" value="/css/navbar.css"/>
</jsp:include>

    <script src="/js/drawingtool.js"></script>
    <script src="/js/fabric.js"></script>


    <canvas id="canvas" style="border-style: solid" height="600px" width=" 600px"></canvas>
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
    <button id="Fill">Fill</button>


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

    oninput = changeColor()"
    <a href="/">Click me</a>
<jsp:include page="footer.jsp" />
