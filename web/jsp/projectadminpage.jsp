<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Project Admin Page"/>
    <jsp:param name="subtitle" value="Project Admin Page"/>
    <jsp:param name="css" value="/css/projectAdminPage.css"/>
    <jsp:param name="js" value="/js/projectAdminPage.js"/>
</jsp:include>

<div class="papWrapper">
    <div class="seriesList content-border">
        <div role="tabpanel" class="tab-pane" id="series">
            <div class="row">
                <div id="newSeries" class="span2">
                    <div class="col-sm-2">
                        <div class="thumbnail list-item center-block">
                            <img class="idea" src="/img/plus_sign.jpg" alt="New Scribble"/>
                        </div>
                    </div>
                </div>
                <c:forEach var="i" items="${series}">
                    <div class="span2">
                        <div class="col-sm-2">
                            <div id="${i.seriesID}" class="thumbnail list-item center-block series">
                                <h2>${i.title}</h2>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="customFlow">
        <a href="#" class="btn btn-info push-right">Customize Flow</a>
    </div>
</div>

<jsp:include page="footer.jsp" />