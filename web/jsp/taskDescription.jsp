<script>
    $(document).ready(function(){

        $('#${param.selector}Summary').click(function(){
            $(this).removeAttr("readonly");
            $('#${param.selector}editSummary').removeClass('hide');
        });

        $('#${param.selector}editSummary').click(function(){
            $('#${param.selector}Summary').attr("readonly", "readonly");
            $('#${param.selector}editSummary').addClass('hide');
            var summary = $('#${param.selector}Summary').val();

            $.ajax({
                url:"/create/page/editSummary",
                data: {summary:summary, pageID: $('#${param.selector}HiddenPageID').val()},
                type:"POST",
                error: function(){
                    alert("Failed to edit the Summary.");
                }
            });
        });

    });

</script>
<div class="row"> <!-- Summary -->
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Summary : </h3>
        </div>
        <div class="panel-body">
            <div class="input-group" style="width:100%;">
                <textarea id="${param.selector}Summary" readonly="readonly" name="description" class="form-control" style="width:inherit;" maxlength="100">
                </textarea>
                <span id="${param.selector}editSummary" class="input-group-addon hide">
                    <a id="editSummaryButton" class="btn btn-default btn-sm" style="padding:0px;">Save Changes</a>
                </span>
            </div>
        </div>
    </div>

</div>