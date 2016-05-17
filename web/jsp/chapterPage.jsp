<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tara && Jason && Danny
  Date: 4/13/16
  Time: 9:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Edit Chapter Page"/>
        <jsp:param name="css" value="/css/chapterPage.css"/>
        <jsp:param name="js" value="/js/chapterPage.js"/>
    </jsp:include>

    <div class="outer">

        <div class="top">
            <div class="row">
            <div class="team">
                <p>Team</p>
                <a class="btn btn-link change" id="addTeam">
                    <i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i>
                </a>
                <div class="memberList">
                    <c:forEach var="member" items="${teamMembers}" >
                        <span>
                        <p title="Member">${member.userData.nickName}</p>
                        <div class="teamMember" style="display: none">${member.userData.userName}</div>
                        </span>
                    </c:forEach>
                </div>
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
                    </div><%-- End Chapter Info class--%>
            </div>
            <div class="row">
                <div id="bottom" class="pull-right">
                    <button type="button" class="btn btn-primary" id="addPage">Add Page</button>
                    <button type="button" class="btn btn-default">Return to Series Page</button>
                    <button type="button" class="btn btn-primary">Publish Page</button>
                </div>
            </div>
        </div> <%-- End TOP class--%>



        <div id="flowContainer" class="middle">

            <c:forEach var="flowTask" items="${chapter.flow.flowTasks}">
                <div class="flow">
                    <div class="row-fluid">
                        <p>${flowTask.flowTaskName}</p>
                    </div>
                    <div class="flowBody">
                        <ul class="list-group">
                        <c:forEach var="page" items="${pages}">
                            <c:if test="${flowTask.flowTaskName eq page.flowTaskEntity.flowTaskName}">
                                <%--<div class="row">--%>
                                    <li class="list-group-item mItem">
                                        <div class="flowTask">
                                            <div class="">
                                                <h3>${page.title}</h3>
                                                ${page.summary}
                                            </div>
                                        </div>
                                    </li>

                                <%--</div>--%>
                            </c:if>
                        </c:forEach>
                        </ul>
                    </div>
                </div>
            </c:forEach>

            <%--<button class="btn pageTask" id="${page.pageNumber}" data-toggle='modal' data-target='#writeTaskModal'>--%>
                <%--${page.pageName}--%>
            <%--</button>--%>

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
                <form id="imgForm" method="post" action="${uploadAction}" enctype="multipart/form-data">
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
                                <input type="text" name="chapterTitle" value="${chapter.title}">
                                </div>

                                <div id="changeChapterStr">
                                <p style="font-weight: bold">#: </p>
                                <input type="text" name="chapterString" value="${chapter.chapterString}">
                                </div>

                                <div id="editDescr">
                                <p style="font-weight: bold">Description: </p>
                                <textarea id="textboxDescr" name="description">${chapter.description}</textarea>
                                </div>
                            </div>

                            <div class="editImg">
                                <p style="font-weight: bold">Chapter Icon: </p>
                                <img id="imgPreview" src="${chapter.chapterCover}">

                                    <input type="hidden" name="chapterID" value="${chapterId}"/>
                                    <input name="imgSrc"  id="browseImg" type="file" accept="image/*" class="hide">
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" data-dismiss="modal"id="saveInfo">Save</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

<%-- #preDrawTaskModal --%>
    <jsp:include page="preDrawTaskModal.jsp" >
        <jsp:param name="chapterID" value="${chapterId}"/>
    </jsp:include>
<%-- #drawTaskModal --%>
    <jsp:include page="DrawTaskModal.jsp" >
        <jsp:param name="chapterID" value="${chapterId}"/>
    </jsp:include>
<%-- #writeTaskModal --%>
    <jsp:include page="writeTaskModal.jsp" >
        <jsp:param name="chapterID" value="${chapterId}"/>
    </jsp:include>
<%-- #newTaskModal --%>
    <jsp:include page="newTaskModal.jsp" >
        <jsp:param name="chapterID" value="${chapterId}"/>
    </jsp:include>
<%-- #reviewTaskModal --%>
    <jsp:include page="ReviewTaskModal.jsp" >
        <jsp:param name="chapterID" value="${chapterId}"/>
    </jsp:include>


    <c:if test="${chapterId ne null}">
        <div id="chapterId" style="display: none">${chapterId}</div>
    </c:if>

    <c:if test="${userData ne null}">
        <div id="currentUserID" style="display: none">${userData.nickName}</div>
    </c:if>

    <c:if test="${seriesID ne null}">
        <div id="seriesID" style="display: none;">${seriesID}</div>
    </c:if>


<jsp:include page="footer.jsp"/>
