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

        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#scribble" aria-controls="scribbles" role="tab" data-toggle="tab">Scribbles</a></li>
            <li role="presentation"><a href="#doodle" aria-controls="doodles" role="tab" data-toggle="tab">Doodles</a></li>
        </ul>

        <div class="tab-content">
            <div role="tabpanel" class="tab-pane fade in active" id="scribbles">
                ....
            </div>
            <div role="tabpanel" class="tab-pane fade" id="doodles">
                <c:forEach var="i" items="${doodles}">
                    <div class="row-fluid">
                        <div class="col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <img class="doodle" src="/img/logo.jpg" alt="Hmmm">
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

<jsp:include page="footer.jsp"/>