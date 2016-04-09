<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="writeTabs center-block">


    <c:set var="pageCount" value="0"/>
    <c:forEach items="${pages}" var="page" varStatus="pageIndex">

        <div role="tabpanel" class="page-pane tab-pane fade fade in ${pageIndex.index eq 0 ? 'active' : none} " id="Page${pageIndex.index}">

            <!-- nav tabs -->
            <ul id="tabHeader" class="nav nav-tabs" role="tablist">
            <c:forEach items = "${page.scenes}" var = "scene" varStatus="i">
                <li role="presentation" class="sceneTab ${i.index eq 0 ? "active" : ""}">
                    <a href="#Page${pageIndex.index}Scene${i.index}" aria-controls="Page${pageIndex.index}Scene${i.index}" role="tab" data-toggle="tab">Scene ${i.index + 1}</a>
                </li>
            </c:forEach>
                <li role="presentation">
                    <a id="addScene" href="#plus" aria-controls="plus" role="tab" data-toggle="tab"> + </a>
                </li>
            </ul>


            <!-- Tab panes -->
            <div id="scene-list" class="tab-content">
                <c:set var="sceneCount" value="0"/>
                <c:forEach items = "${page.scenes}" var = "scene" varStatus="i">
                    <div role="tabpanel" class="scene-tabs tab-pane fade fade in ${i.index eq 0 ? 'active' : none} " id="Page${pageIndex.index}Scene${i.index}">
                        <div class="content">
                            <div class="narritive">
                                <textarea id="Page${pageIndex.index}writingArea${i.index}" name="pages[${pageIndex.index}].scenes[${i.index}].tinyMCEText" class="tinyMCE">
                                        ${scene.tinyMCEText}
                                </textarea>
                            </div>

                        </div>
                        <label for="settingPage${pageIndex.index}Scene${i.index}">Setting:</label>
                        <textarea id="settingPage${pageIndex.index}Scene${i.index}" disabled class="setting form-control" placeholder="setting">${scene.setting}</textarea>
                    </div>
                    <c:set var="sceneCount" value="${sceneCount + 1}"/>
                </c:forEach>
            </div>

        </div>
        <c:set var="pageCount" value="${pageCount + 1}"/>
    </c:forEach>

    <input id="nextScene" type="hidden" value='${sceneCount}'/>
    <input id="nextPage" type="hidden" value=${sceneCount}/>
    <input id="currPage" type="hidden" value="0"/>
</div>


