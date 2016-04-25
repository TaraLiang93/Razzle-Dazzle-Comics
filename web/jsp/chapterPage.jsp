<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <a class="btn btn-link change" id="addTeam">
                    <i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i>
                </a>
                <div class="memberList">
                    <%--<c:if test="${teamMember ne null}">--%>
                        <%--<c:forEach var="member" items="${teamMember}" >--%>
                            <%--<p>${member}</p>--%>
                        <%--</c:forEach>--%>
                            <p title="Owner">Tara</p>
                            <p title="Artist">Miuki</p>
                            <p title="Writer">Shakeeb</p>
                            <p title="Member">Terrell</p>
                            <p title="Manager">James</p>
                    <%--</c:if>--%>
                </div>
            </div>

            <div class="chapterInfo pull-right">
                <a class="btn btn-link change" id="editChapterInfo">
                    <i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i>
                </a>
                <img src="${chapter.chapterCover}" id="img" class="pull-left">
                <div class="text pull-right">
                    <div class="info">
                        <p style="font-weight: bold">Title:</p>
                        <p id="chapterTitle">${chapter.title}</p>
                    </div>

                    <div class="info">
                        <p style="font-weight: bold">#:</p>
                        <p id="chapterString">${chapter.chapterString}</p>
                    </div>

                    <div class="btnInfo">
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


        <div id="bottom" class="pull-right">
            <button type="button" class="btn btn-primary" id="addPage">Add Page</button>
            <button type="button" class="btn btn-default">Return to Series Page</button>
            <button type="button" class="btn btn-primary">Publish Page</button>
        </div>


        <%--MODAL FOR TEAM--%>
        <div class="modal fade" id="teamModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Team Member</h4>
                    </div>
                    <div class="modal-body" id="teamBody">

                    </div>
                    <div class="modal-footer">
                        <div id="addMember">
                            <p>New Member: </p>
                            <div>
                                <input type="text" id="newMember" value="Member Id">
                                <button type="button" class="btn" id="addButton">Add</button>
                            </div>
                        </div>
                        <div>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%--Modal for Chapter info--%>
        <div class="modal fade" id="infoModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Edit Chapter Info</h4>
                    </div>
                    <div class="modal-body editInfo">

                        <div class="einfo">
                            <div id="changeTitle">
                            <p style="font-weight: bold">Title: </p>
                            <input type="text" id="Title">
                            </div>

                            <div id="changeChapterStr">
                            <p style="font-weight: bold">#: </p>
                            <input type="text" id="myNumber">
                            </div>

                            <div id="editDescr">
                            <p style="font-weight: bold">Description: </p>
                            <textarea id="textboxDescr">${chapter.description}</textarea>
                            </div>
                        </div>

                        <div class="editImg">
                            <p style="font-weight: bold">Chapter Icon: </p>
                            <img id="imgPreview" src="">
                            <form id="imgForm" method="post" action="/create/chapter/updateChapterImage" enctype="multipart/form-data">
                                <input name="imgSrc"  id="browseImg" type="file" accept="image/*">
                            </form>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal"id="saveInfo">Save</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <c:if test="${chapterId ne null}">
        <div id="chapterId" style="display: none">${chapterId}</div>
    </c:if>
</body>
</html>
