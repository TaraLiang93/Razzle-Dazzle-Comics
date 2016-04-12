<jsp:include page="header.jsp">
    <jsp:param name="title" value="Draw Page"/>
    <jsp:param name="css" value="/css/doodles.css"/>
    <jsp:param name="js" value="/js/doodles.js"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="/css/drawing.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

<script src="/js/drawingtool.js"></script>
<script src="/js/fabric.js"></script>
<script src="/js/drawing.js"></script>

<div style="position: relative">
    <div class="drawingPage center-block">
        <div class="drawingContainer">
            <jsp:include page="Drawing.jsp"/>
        </div>
    </div>

    <div class="doodleInfo center-block">
        <div class="doodleDesc">
            <div class="input-group">
                <label for="doodleTitle">Title</label>
                <input type="text" id="doodleTitle" name="doodleTitle" class="input-sm" />
            </div>
            <label for="doodleDescription">Description</label>
            <div class="input-group">
                <textarea id="doodleDescription" name="doodleDescription" class="input-lg" maxlength="100"  ></textarea>
            </div>

        </div>
        <div class="doodleButtons btn-group-vertical">
            <a href="#new" class="btn btn-lg btn-warning" data-toggle="modal" >New</a>
            <a  class="btn btn-lg btn-primary saveDoodle">Save</a>
        </div>
    </div>
</div>

    <div class="modal fade" tabindex="-1" id="newDoodleModal" role="dialog" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Do you want to save your work</h4>
                </div>
                <div class="modal-body">
                        <a href="#" class="btn btn-primary btn-lg active center saveDoodle"  role="button">Save</a>
                        <a href="#" class="btn btn-default  btn-lg center " data-dismiss="modal" id="cancelDoodle" role="button">cancel</a>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
<jsp:include page="footer.jsp"/>