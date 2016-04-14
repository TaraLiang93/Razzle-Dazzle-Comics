<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Project Admin Page"/>
    <jsp:param name="subtitle" value="Project Admin Page"/>
    <jsp:param name="css" value="/css/projectAdminPage.css"/>
    <jsp:param name="js" value="/js/projectAdminPage.js"/>
</jsp:include>

<div role="tabpanel" class="tab-pane" id="series">
    <div class="row">
        <div id="newSeries" class="span2">
            <div class="col-sm-3">
                <div class="thumbnail list-item">
                    <img class="idea" src="/img/plus_sign.jpg" alt="New Scribble"/>
                </div>
            </div>
        </div>
        <c:forEach var="i" items="${series}">
            <div class="span2">
                <div class="col-sm-3">
                    <div id="${i.seriesId}" class="thumbnail list-item series">
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

<jsp:include page="footer.jsp" />