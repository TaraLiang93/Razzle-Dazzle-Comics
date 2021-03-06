<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    $(document).ready(function() {
        $("[type='checkbox']").click(function(){
            console.log($(this).attr("id"));
            if($("[type='checkbox']:checked").size() > 0)
            {
                $(".filter").hide();// hides all the checkboxes
                $("[type='checkbox']:checked").each(function(){// shows only the checkboxes that are selected
                    var selectClass = "."+$(this).attr("id");
                    $(".filter").hide();
                    $(selectClass).parent().show();
                })

            }
            else
            {
                $(".filter").show();
            }
        });
    });
</script>

<c:choose>
    <c:when test="${param.search eq 'search'}">
        <div class="refineSerch col-xs-2 content-border">
            <h3>Refine Search</h3>
            <%--<div class="input-group">--%>
                <%--<p>Author Name</p>--%>
                <%--<input type="text" name="filter" class="form-control"/>--%>
            <%--</div>--%>
            <div class="form-group">
                <p>Genre</p>
                <div class=" filterBoxes content-border">
                    <c:forEach var="genre" items="${genres}">
                        <div class="checkbox">
                            <label type="text"><input type="checkbox" id="${genre.name}" name="${genre.name}">${genre.name}</label>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <h3>Filter Series</h3>
        <div class="filterBox col-xs-12 content-border">
            <div class="checkboxes col-xs-8 col-xs-offset-2">
                <c:forEach var="genre" items="${genres}">
                    <div class="checkbox">
                        <label type="text" class="col-xs-3"><input type="checkbox" id="${genre.name}" name="${genre.name}"/>${genre.name}</label>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:otherwise>
</c:choose>
