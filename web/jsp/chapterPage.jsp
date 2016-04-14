<%--
  Created by IntelliJ IDEA.
  User: tara
  Date: 4/13/16
  Time: 9:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Edit Chapter Page"/>
        <jsp:param name="css" value="/css/chapterPage.css"/>
        <jsp:param name="js" value="/js/chapterPage.js"/>
    </jsp:include></head></head>
<body>
    <div class="outer">

        <div class="top">
            <div class="team">
                <p>Team</p>
                <a class="btn btn-link updateDescr" id="addTeam">
                    <i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i>
                </a>
                <div class="memberList">

                </div>
            </div>

            <div class="chapterInfo pull-right">
                <a class="btn btn-link updateDescr" id="editChapterInfo">
                    <i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i>
                </a>
                <img src="http://placehold.it/150x150" id="img" class="pull-left">
                <div class="text pull-right">
                    <p>Title:</p>
                    <p>#:</p>
                    <div class="btnInfo">
                        <button type="button" class="btn btn-default">Customize Workflow</button>
                        <button type="button" class="btn btn-default pull-right">Deactive</button>
                    </div>
                </div>
            </div>
        </div>


        <div class="middle">
            <div class="flow">
                <p>Writer</p>
                <div class="flow1" id="writer">

                </div>
            </div>
            <div class="flow">
                <p>Pre-Draw</p>
                <div class="flow2" id="preDraw">

                </div>
            </div>
            <div class="flow">
                <p>Draw</p>
                <div class="flow1" id="draw">

                </div>
            </div>
            <div class="flow">
                <p>Review</p>
                <div class="flow2" id="review">

                </div>
            </div>
            <div class="flow">
                <p>Complete</p>
                <div class="flow1" id="complete">

                </div>
            </div>
        </div>

        <div class="bottom">
            <%--<button type="  "></button>--%>
        </div>


    </div>
</body>
</html>
