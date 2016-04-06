<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="writeTabs center-block">

    <form method="post" action="/create/scribble/save" >

        <!-- nav tabs -->
        <ul id="tabHeader" class="nav nav-tabs" role="tablist">
        <c:forEach items = "${page.scenes}" var = "scene" varStatus="i" begin="1">
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
            <c:forEach items = "${page.scenes}" var = "scene" varStatus="i" begin="1">
                <div role="tabpanel" class="tab-pane fade fade in ${i.index eq 1 ? 'active' : none} " id="Scene${i.index}">
                    <div class="content">
                        <div class="title">
                            Miuki has job
                        </div>
                        <div class="narritive">
                            <textarea id="writingArea${i.index}" class="tinyMCE">
                                 <c:forEach items="${scene.dialogue}" var="dialog">
                                    <c:out value="${dialog.dialogue}"/>
                                </c:forEach>
                            </textarea>
                        </div>

                    </div>
                    <textarea disabled class="setting form-control" placeholder="setting">${scene.setting}</textarea>
                </div>

            </c:forEach>

        </div>

            <div class="description center-block">
                <form role="form" class="form-horizontal">

                    <div id="textForm" class="col-sm-2">
                        <div>
                            <label for="storyTitle">Title </label>
                            <input id="storyTitle" class="form-control " type="text"/>
                        </div>
                        <div>
                            <label for="storyDesc">Description</label>
                            <textarea id="storyDesc" class="form-control" maxlength="100"></textarea>
                        </div>
                    </div>
                    <div id="buttons" class="col-sm-2 center-block">
                        <button type="submit" class="btn btn-primary storySave center-block">Save Draft</button>

                    </div>
                </form>
            </div>

    </form>
</div>


