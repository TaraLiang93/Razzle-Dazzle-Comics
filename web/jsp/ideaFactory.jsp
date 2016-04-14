<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: drodrigues
  Date: 4/3/16
  Time: 8:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp">
    <jsp:param name="title" value="Idea Factory"/>
    <jsp:param name="subtitle" value="Idea Factory"/>
    <jsp:param name="css" value="/css/ideaFactory.css"/>
    <jsp:param name="js" value="/js/ideaFactory.js"/>
</jsp:include>

<div class="center-block">

    <div id="wrapper" class="panel panel-default wrapper center-block">

        <ul id="ideaFactory" class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active tabHeader"><a href="#scribbles"  role="tab" data-toggle="tab">Scribbles</a></li>
            <li role="presentation" class="tabHeader"><a href="#doodles" role="tab" data-toggle="tab">Doodles</a></li>
        </ul>

        <div class="tab-content">
            <div role="tabpanel" class="tab-pane fade in active" id="scribbles">
                <div class="row">
                    <div id="newScribble" class="span2">
                        <div class="col-sm-3 col-md-3">
                            <div class="thumbnail list-item">
                                <img class="idea" src="/img/plus_sign.jpg" alt="New Scribble"/>
                                <div class="caption">
                                    <h3>Add a New Scribble!</h3>
                                    <p>A way to free write!</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:forEach var="i" items="${scribbles}">
                        <div class="span2">
                            <div class="col-sm-3 col-md-3">
                                <div id="thing" class="thumbnail list-item scribble">
                                    <img class="idea" src="/img/logo.jpg">
                                    <div class="caption">
                                        <h3>${i.title}</h3>
                                        <p>${i.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane fade" id="doodles">
                <div class="row">
                    <div id="newDoodle" class="span2">
                        <div class="col-sm-3 col-md-3">
                            <div class="thumbnail list-item">
                                <img class="idea" src="/img/plus_sign.jpg" alt="New Doodle"/>
                                <div class="caption">
                                    <h3>Add a New Doodle!</h3>
                                    <p>A way to free draw!</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:forEach var="i" items="${doodles}">
                        <div class="span2">
                            <div class="col-sm-3 col-md-3">
                                <div id="${i.doodleId}" class="thumbnail list-item doodle">
                                    <img class="idea" src="/img/logo.jpg">
                                    <div class="caption">
                                        <h3>${i.title}</h3>
                                        <p>${i.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>


    </div>

</div>

<jsp:include page="footer.jsp"/>