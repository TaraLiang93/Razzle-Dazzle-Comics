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

            <div class="col-sm-3 item">
                <div id="newSeries" class="thumbnail " data-toggle="modal" data-target="#seriesModal">
                    <div style="height: 85%;">
                        <img class="idea" src="/img/plus_sign.jpg" alt="New Scribble"/>
                    </div>
                    <div class="caption text-center">Add New Series</div>
                </div>
            </div>

            <c:forEach var="i" items="${series}">
                <div class="col-sm-3  item">
                <div id="${i.seriesID}" class="thumbnail series" >
                    <div style="height: 85%;">
                        <img src="${i.seriesCover}" class="seriesImg">
                    </div>
                    <div class="caption text-center">
                        <p>${i.title}</p>
                    </div>
                </div>
                </div>
            </c:forEach>

            </div>
        </div>
    </div>
</div>

<jsp:include page="newSeriesModal.jsp"></jsp:include>

<jsp:include page="footer.jsp" />