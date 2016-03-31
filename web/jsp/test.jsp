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

    <jsp:include page="navbar.jsp">
        <jsp:param name="title" value="Testing"/>
        <jsp:param name="css" value="/StyleSheet/navbar.css"/>
    </jsp:include>
    <script src="../js/drawingtool.js"></script>
    <script src="../js/fabric.js"></script>
</head>
<body>
    <canvas id="canvas" style="border-style: solid"></canvas>
    <button id="Clear">Clear</button>
    <button id="Draw">Draw</button>

    Hello World!
    <a href="/">Click me</a>
</body>
</html>
