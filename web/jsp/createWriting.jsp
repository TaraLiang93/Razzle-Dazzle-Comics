<jsp:include page="header.jsp">
    <jsp:param name="title" value="Create Writing"/>
    <jsp:param name="css" value="/css/createWriting.css"/>
</jsp:include>


<h1>I love Batman</h1>

<div class="writeTabs">
    <div>
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active">
                <a href="#Scene1" aria-controls="Scene1" role="tab" data-toggle="tab">Scene 1</a>
            </li>
            <li role="presentation">
                <a href="#Scene2" aria-controls="Scene2" role="tab" data-toggle="tab">Scene 2</a>
            </li>
            <li role="presentation">
                <a href="#Scene3" aria-controls="Scene3" role="tab" data-toggle="tab">Scene 3</a>
            </li>
            <li role="presentation">
                <a id="addScene" href="#plus" aria-controls="plus" role="tab" data-toggle="tab"> + </a>
            </li>
        </ul>

        <div class="tabDivider"></div>

        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="Scene1">
                I'm thinking of putting a jsp include here to grab the scene and might make a jsp include for the page
            </div>
            <div role="tabpanel" class="tab-pane" id="Scene2">
                added stuff
            </div>
            <div role="tabpanel" class="tab-pane" id="Scene3">
                all we do is work work work work work
            </div>

        </div>

    </div>
</div>

<jsp:include page="footer.jsp"/>
