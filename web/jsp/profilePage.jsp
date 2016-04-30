<%--
  Created by IntelliJ IDEA.
  User: drodrigues
  Date: 4/29/16
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="title" value="Profile Page"/>
    <jsp:param name="subtitle" value="${user.nickName} Profile"/>
    <jsp:param name="css" value="/css/profilePage.css"/>
    <jsp:param name="js" value="/js/profilePage.js"/>
</jsp:include>


<div class="profilePage center-block">

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li class="headerTab" role="presentation" class="active"><a href="#profile" aria-controls="home" role="tab" data-toggle="tab">Profile</a></li>
        <li class="headerTab" role="presentation"><a href="#settings" aria-controls="profile" role="tab" data-toggle="tab">Settings</a></li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="profile">
            <div class="row image center-block">
                    <img id="img" src="${user.userImage}" alt="Profile Image" style="width:100%; height: inherit; border:3px solid black;"/>
            </div>
            <div class="row name center-block"> <!-- name -->
                <h2>${user.nickName}</h2>
            </div>
            <div class="row aboutMe center-block"> <!-- About me -->
                <h3>${user.userName}</h3>
            </div>
            <div class="row aboutMe center-block"> <!-- About me -->
                <h4>${user.location}</h4>
            </div>
            <div class="row aboutMe center-block"> <!-- About me -->
                <p>${user.description}</p>
            </div>
            <div class="panel panel-default ">
                <div class="panel-heading">
                    <h3 class="panel-title">Portfolio</h3>
                </div>
                <div class="panel-body">
                    <div class="span2 portfolio">
                        <c:forEach var="series" items="${user.series}">
                            <div class="col-sm-2 ">
                                <div id="${series.seriesID}" class="thumbnail seriesItem ">
                                        <div class="col-md-12 center-block">
                                            <img class="idea seriesImg" src="${series.seriesCover}"/>
                                        </div>
                                    <div class="seriesCaption">
                                        <h3>${series.title}</h3>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="settings">
            <form id="imageForm" method="post" action="${uploadAction}" enctype="multipart/form-data">
                <div class="row center-block settingsForm">
                    <div class="pull-left">
                        <div class="form-group">
                            <label for="nicknameInput">Nickname : </label>
                            <input type="text" class="form-control" name="nickName" id="nicknameInput" value="${user.nickName}">
                        </div>
                        <div class="form-group">
                            <label for="locationInput">Location : </label>
                            <input type="text" class="form-control" name="location" id="locationInput" placeholder="Location" value="${user.location}">
                        </div>
                        <div class="form-group">
                            <label for="descTextarea">About Me : </label>
                            <textarea type="text" class="form-control" name="description" id="descTextarea" placeholder="About Me">${user.description}</textarea>
                        </div>
                    </div>
                    <div class="pull-right">
                        <input id="fileUpload" type="file" name="profileImage" accept="image/*" style="display:none;" onchange="readURL(this, '#profileImg');">
                        <img id="profileImg" src="${user.userImage}" alt="Profile Image"
                             style="width:100%; height: inherit; border:3px solid black;" onclick="$('#fileUpload').click();"/>
                    </div>
                </div>
                <div class="row settingsForm center-block">
                    <button type="submit" class="btn btn-primary">Update</button>
                </div>
            </form>
        </div>
    </div>


</div>

<jsp:include page="footer.jsp"/>
