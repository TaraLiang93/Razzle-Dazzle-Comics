<%--
  Created by IntelliJ IDEA.
  User: drodrigues
  Date: 3/19/16
  Time: 9:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Title</title>

    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Testing"/>
        <jsp:param name="css" value="/StyleSheet/navbar.css"/>
    </jsp:include>
    <script src="../js/drawingtool.js"></script>
    <script src="../js/fabric.js"></script>
</head>
<body>
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
    <p id="Zoom-level"></p>
    <input id="Drawing-color" type="color" oninput="changeColor()">
    <input id="Line-width" type="number" oninput="changeLineWidth()">

    <p> Input Image:<input id ="Image-file" type="file" name="pic" accept="image/*">
    </p>

    Hello World!
    <a href="/">Click me</a>
</body>
</html>
