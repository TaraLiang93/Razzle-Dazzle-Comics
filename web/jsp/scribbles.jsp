<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="names" value="//cdn.tinymce.com/4/tinymce.min.js,/js/writing.js" />
<c:set var="style" value="/css/writing.css, /css/scribbles.css" />
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Scribbles"/>
    <jsp:param name="css" value="${style}"/>
    <jsp:param name="js" value="${names}"/>
</jsp:include>


<c:set var="pages" value="${scribble.pages}" scope="request"/>


<div class="scribbleWrapper center-block">

    <div class="pageList pull-right">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Pages</h3>
            </div>
            <div class="panel-body">

                <!-- Page Nav Tab -->
                <ul id="pageTabs" class="nav nav-pills nav-stacked">
                    <c:forEach items = "${scribble.pages}" var = "page" varStatus="i">
                        <li role="presentation" class="page-tab ${i.index eq 0 ? "active" : ""}">
                            <a href="#Page${i.index}" aria-controls="Page${i.index}" role="tab" data-toggle="tab">Page ${i.index + 1}</a>
                        </li>
                    </c:forEach>
                    <li>
                        <a id="addPage" href="#addPage"> + Page </a>
                    </li>
                </ul>
                <!-- END Page Nav Tab -->
            </div>
        </div>
    </div>
    <form:form method="post" action="/create/scribble/save" modelAttribute="scribbleModel">

        <input type="hidden" name="id" value="${scribble.scribbleId}"/>
        <jsp:include page="Writing.jsp">
            <jsp:param name="textRequired" value="${true}"/>
            <jsp:param name="text" value="${scribble.title}"/>
            <jsp:param name="textarea" value="${scribble.description}"/>
        </jsp:include>


    </form:form>

</div>
<jsp:include page="footer.jsp"/>