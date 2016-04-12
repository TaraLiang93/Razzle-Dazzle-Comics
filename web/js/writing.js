/**
 * Created by drodrigues on 4/4/16.
 */


function initTinyMCE(selector){

    tinymce.init({
        selector: selector,
        statusbar: false,
        menubar:false,
        height: "500px",
        width: "100%",
        toolbar: 'redo undo styleselect bold italic underline strikethrough alignleft aligncenter alignright bullist numlist outdent indent code',
        toolbar_items_size : 'small',
        theme_advanced_resizing: true,
        theme_advanced_resizing_use_cookie : false,
        editor_selector: "tinyMCE"
    });
}

$(document).ready(function(){

    $('#tabHeader a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });

    $('#modalSave').click(function (e) {
        e.preventDefault();
        var what = $('#modalSetting').val(); //Get the text that was written
        var where = $('#redirectModal').val(); //Get the stored id of the value to update
        $('#' +where).val(what); //Update the where with the what
        $('#settingModel').modal('hide');
    });


    $(".tinyMCE").each(function(){
        initTinyMCE('#' + $(this).attr('id'));
    });

    $('.setting').each(function(){
       $(this).click(function(){
            $('#redirectModal').val($(this).attr('id'));
            $('#modalSetting').val($(this).val());
           $('#settingModel').modal('show');
       });
    });

    $(".page-pane").each(function(){
        if(! $(this).hasClass('active')){
            $(this).hide();
        }
    });

    $('.page-tab').each(function(){

        $(this).click(function(){
            $('.page-pane:visible').hide();
            var id = $(this).children('a:first').attr('href');
            $(id).show();
        });
    });

    $('#addScene').click(function(){
        var page = $('#currPage').val();
        var scene =$('#nextScene').val();
        var sceneInt = parseInt(scene, 10) + 1;
        var nextID = "Page"+page+"Scene"+scene;

        var panelText= "<div role=\"tabpanel\" class=\"tab-pane fade fade in active \" id=\""+nextID+"\"><div class=\"content\">                  \
        <div class=\"narritive\"><textarea id=\"Page"+page+"writingArea"+scene+"\" name=\"pages["+page+"].scenes["+scene+"].tinyMCEText\" class=\"tinyMCE\"> \
            </textarea></div></div><label for=\"setting"+nextID+"\">Setting:</label>                                                         \
        <textarea id=\"setting"+nextID+"\" disabled class=\"setting form-control\" placeholder=\"\"></textarea></div>"

        $('#scene-list').append(panelText);


        initTinyMCE("#Page"+page+"writingArea"+scene);

        var headerText ="<li role=\"presentation\" class=\"active\">\
            <a href=\"#"+nextID+"\" aria-controls=\""+nextID+"\" role=\"tab\" data-toggle=\"tab\">\
            Scene "+sceneInt+"</a></li>";

        $('#tabHeader li:last').prev().after(headerText);

        $('#nextScene').val(sceneInt);

        $('#tabHeader .sceneTab a').each(function(){
            if($(this).hasClass('active')){
                $(this).tab('hide');
            }

            if($(this).attr('href') === '#'+nextID){
                $(this).tab('show');
                $(this).click();
            }
        });



    });

});


