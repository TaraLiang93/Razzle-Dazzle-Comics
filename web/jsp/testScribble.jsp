<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Scribbles Test"/>
</jsp:include>


<form:form method="post" action="/test/scribble/save" modelAttribute="scribbleModel">

    <form:input path="title" value="${scribbleModel.title}"/><br/>

    <c:forEach items="${scribble.pages}" var="page" varStatus="i">
        <c:forEach items="${page.scenes}" var="scene" varStatus="j">
            <c:forEach items="${scene.dialogue}" var="dialogue" varStatus="k">
                <input name="pages[${i.index}].scenes[${j.index}].dialogue[${k.index}].dialogue" value="${dialogue.dialogue}"><br/>
            </c:forEach>
        </c:forEach>
    </c:forEach>

    <input type="submit" value="Save"/>
</form:form>

<jsp:include page="footer.jsp"/>