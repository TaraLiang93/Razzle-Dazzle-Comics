<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>Filter Chapters</h3>
<div class="filterBox col-xs-12 content-border">
    <div class="checkboxes col-xs-8 col-xs-offset-2">
        <c:forEach var="genre" items="${genres}">
            <div class="checkbox">
                <label type="text" class="col-xs-3">
                    <input type="checkbox" id="${genre.name}Genre" name="${genre.name}Genre"/>${genre.name}
                </label>
            </div>
        </c:forEach>
    </div>
</div>