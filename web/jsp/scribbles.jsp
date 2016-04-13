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
                <ul id="pageTabs" class="nav nav-pills nav-stacked">
                    <c:forEach items = "${scribble.pages}" var = "page" varStatus="i">
                        <li role="presentation" class="page-tab ${i.index eq 0 ? "active" : ""}">
                            <a href="#Page${i.index}" aria-controls="Page${i.index}" role="tab" data-toggle="tab">Page ${i.index + 1}</a>
                        </li>
                    </c:forEach>
                    <li role="presentation">
                        <a id="addPage" href="#addPage" aria-controls="plus" role="tab" data-toggle="tab"> + Page </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <form:form method="post" action="/create/scribble/save" modelAttribute="scribbleModel">

        <input type="hidden" name="id" value="${scribble.scribbleId}"/>
        <jsp:include page="Writing.jsp"/>

        <div class="description writeTabs center-block">

                <div id="textForm" class="col-sm-2">
                    <div>
                        <label for="storyTitle">Title </label>
                        <form:input id="storyTitle" path="title" value="${scribble.title}" type="text" class="form-control"/>
                    </div>
                    <div>
                        <label for="storyDesc">Description</label>
                        <form:textarea id="storyDesc" path="description" class="form-control" maxlength="100" placeholder="${scribble.description}"/>
                    </div>
                </div>
                <div id="buttons" class="col-sm-2 center-block">
                    <button type="submit" class="btn btn-primary storySave center-block">Save Draft</button>
                </div>
        </div>
    </form:form>

</div>
<jsp:include page="footer.jsp"/>