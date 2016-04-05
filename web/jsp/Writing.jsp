<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src='//cdn.tinymce.com/4/tinymce.min.js'></script>
<script src="/js/writing.js"/>
<div class="writeTabs">

    <!-- nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
    <c:forEach items = "${param.items}" var = "scene" varStatus="i" begin="1">
        <li role="presentation" class="${i.index eq 1 ? "active" : ""}">
            <a href="#Scene${i.index}" aria-controls="Scene${i.index}" role="tab" data-toggle="tab">Scene ${i.index}</a>
        </li>
    </c:forEach>
        <li role="presentation">
            <a id="addScene" href="#plus" aria-controls="plus" role="tab" data-toggle="tab"> + </a>
        </li>
    </ul>


    <!-- Tab panes -->
    <div class="tab-content">

        <c:forEach items = "${param.items}" var = "scene" varStatus="i" begin="1">
            <div role="tabpanel" class="tab-pane active" id="Scene${i.index}">
                <div class="content">
                    <div class="title">
                        Miuki has job
                    </div>
                    <div class="narritive">
                        <textarea id="writingArea${i.index}" class="tinyMCE">
                            ${scene.dialogue}
                            <%--<c:forEach items="${scene.dialogue}" var="dialog">
                                <c:out value="${dialog.dialogue}"/>
                            </c:forEach>--%>
                        </textarea>
                    </div>
                    <textarea disabled class="setting form-control" placeholder="setting">${scene}</textarea>
                </div>
            </div>

        </c:forEach>

    </div>

    <div class="description">
        <form role="form" class="form-horizontal">
            <div class="form-group">
                <label for="storyTitle">Title </label>
                <input id="storyTitle" class="form-control col-sm-2" type="text"/>
            </div>
            <div class="form-group">
                <label for="storyDesc">Description</label>
                <textarea class="col-sm-4" id="storyDesc"  maxlength="100"></textarea>
            </div>
            <button type="submit" class="btn btn-default storySave">Save Draft</button>
        </form>
    </div>

</div>


