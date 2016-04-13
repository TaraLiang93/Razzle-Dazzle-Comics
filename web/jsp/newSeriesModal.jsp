<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    function formSubmit(){
        $('#seriesModal').modal('hide');
        var selected = $('.flowButtons input:radio:checked').val();
        $('#flow').val(selected);
        $('#seriesForm').submit();
    }
</script>

<form id="seriesForm" method="post" action="/create/series/new" enctype="multipart/form-data">
    <div id="seriesModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Create New Series</h4>
                </div>
                <div class="modal-body">
                    <div class="topNewChapter center-stage row" style="width:90%; margin:auto; margin-bottom: 5em; ">
                        <div class="seriesImage pull-left" style="width:40%;">
                            <input id="fileUpload" type="file" name="seriesImage" accept="image/*" style="display:none;">
                            <img src="/img/profile_default.png" alt="Profile Image" style="width:80%; height: inherit; border:3px solid black;"
                                 onclick="$('#fileUpload').click();"/>
                        </div>
                        <div class="seriesText pull-right" style="width:60%;">
                            <div class="row">
                                <div class="col-xs-10">
                                    <label for="seriesTitle">Series Title :</label>
                                    <input type="text" id="seriesTitle" name="title" size="69"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6">
                                    <label for="seriesAuthor">Author : </label>
                                    <input id="seriesAuthor" type="text" name="author" style="width:100%;"/>
                                </div>
                                <div class="col-xs-6">
                                    <label for="seriesArtist">Artist : </label>
                                    <input id="seriesArtist" type="text" name="artist" style="width:100%;"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <label for="seriesDescription">Description : </label>
                                    <textarea id="seriesDescription" type="text" name="description" style="width:inherit;"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button id="createButton" type="submit" class="btn btn-primary" onclick="formSubmit();">Create Series</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</form>