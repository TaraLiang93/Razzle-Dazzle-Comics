<jsp:include page="header.jsp">
    <jsp:param name="title" value="Create Writing"/>
    <jsp:param name="css" value="/css/createWriting.css"/>
</jsp:include>


<h1>title of document</h1>

    <div class="writeTabs">
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

        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="Scene1">
                <div class="content">
                    <div class="title">
                        Miuki has job
                    </div>
                    <div class="narritive">
                        <jsp:include page="Writing.jsp">
                            <jsp:param name="num" value="1"/>
                        </jsp:include>
                    </div>
                    <textarea disabled class="setting form-control" placeholder="setting">Cold Spring Habor Laboratory</textarea>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="Scene2">
                <div class="content">
                    <div class="title">
                        Miuki has job
                    </div>
                    <div class="narritive">
                        <jsp:include page="Writing.jsp">
                            <jsp:param name="num" value="2"/>
                        </jsp:include>
                    </div>
                    <textarea class="setting" placeholder="setting">Cold Spring Habor Laboratory</textarea>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="Scene3">
                <div class="content">
                    <div class="title">
                        Miuki has job
                    </div>
                    <div class="narritive">
                        <jsp:include page="Writing.jsp">
                            <jsp:param name="num" value="3"/>
                        </jsp:include>
                    </div>
                    <textarea disabled class="setting form-control" placeholder="setting">Cold Spring Habor Laboratory</textarea>
                </div>
            </div>

        </div>

    </div>

<jsp:include page="footer.jsp"/>
