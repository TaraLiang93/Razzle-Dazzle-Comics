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


    //////// GOOD //////////

    //Event Handler for clicking save on settings modal
    $('#modalSave').click(function (e) {
        e.preventDefault();
        var what = $('#modalSetting').val(); //Get the text that was written
        var where = $('#redirectModal').val(); //Get the stored id of the value to update
        $('#' +where).val(what); //Update the where with the what
        $('#settingModel').modal('hide');
    });

    //Initialize all Tiny MCE textareas
    $(".tinyMCE").each(function(){
        initTinyMCE('#' + $(this).attr('id'));
    });

    //On Click handler for the setting section. Update the Modal with new Data
    $('.setting').each(function(){
        $(this).click(function(){
            $('#redirectModal').val($(this).attr('id'));
            $('#modalSetting').val($(this).val());
            $('#settingModel').modal('show');
        });
    });


    //If any of the tabs are selected, except for the add scene tab, display that tab.
    $('.sceneTabHeader li a:not(.add-contact)').each(function () {
        $(this).click(function(e){
            e.preventDefault();
            $(this).tab('show');
        });
    });


    //Hide all pages that are not the first one because of a weird
    // bug that if the underlying scene is active, the page tab is active
    $(".page-pane:not(:first)").each(function(){
        if(! $(this).hasClass('active')){
            $(this).hide();
        }
    });


    //When the page tab is selected hide the other tabs
    // and show this new one
    $('.page-tab a').each(function(){
        $(this).click(function(){
            $('.page-pane:visible').hide();
            var id = $(this).attr('href');
            $(id).show();
            $(this).tab('show');
        });
    });

    ///////// BAD //////////




/*  Add a New Page */

    $('#addPage').click(function(){

        $('.pageList .page-tab').each(function(){
            if($(this).hasClass('active')){
                $(this).removeClass('active');
            }
        });


        $('.page-pane').each(function(){
            if($(this).hasClass('active')){
                $(this).removeClass('active');
            }

            if($(this).hasClass('in')){
                $(this).removeClass('in');
            }

        });


            var newPane= "<div role=\"tabpanel\" class=\"page-pane tab-pane fade in active\" id=\"Page${pageIndex.index}\">\
         <ul id=\"tabHeader\" class=\"nav nav-tabs\" role=\"tablist\"> <li role=\"presentation\" class=\"sceneTab active\">\
         <a id=\"tabPage${pageIndex.index}Scene${i.index}\" href=\"#Page${pageIndex.index}Scene${i.index}\" aria-controls=\"Page${pageIndex.index}Scene${i.index}\" role=\"tab\" data-toggle=\"tab\">Scene 1</a> \
         </li><li><a id=\"addScene\" href=\"#plus\" class=\"add-contact\"></a></li></ul>  <div id=\"scene-list\" class=\"tab-content\">\
         <div role=\"tabpanel\" class=\"scene-tabs tab-pane fade in active \" id=\"Page${pageIndex.index}Scene${i.index}\"> <div class=\"content\"><div class=\"narritive\">\
             <textarea id=\"Page${pageIndex.index}writingArea${i.index}\" name=\"pages[${pageIndex.index}].scenes[${i.index}].tinyMCEText\" class=\"tinyMCE\"></textarea></div></div><label for=\"SettingPage${pageIndex.index}Scene${i.index}\">Setting:</label>\
              <textarea id=\"SettingPage${pageIndex.index}Scene${i.index}\" name=\"pages[${pageIndex.index}].scenes[${i.index}].setting\" \readonly=\"readonly\" class=\"setting form-control\"></textarea></div></div></div> "


    });

    /*  END Add a New Page */
    ////////////////////////////////////////////////////////////



    /*  Add a New Scene */

    $('#addScene').click(function(){
        var page = $('#currPage').val();
        var scenes = $('#Page'+page+' .sceneTabHeader .sceneTab').length;
        var nextScene = scenes + 1;
        var nextID = "Page"+page+"Scene"+nextScene;


        /*
        $('#scene-list .scene-tabs').each(function(){
            if($(this).hasClass('active')){
                $(this).removeClass('active');
            }

            if($(this).hasClass('in')){
                $(this).removeClass('in');
            }

        });
*/
        var scenePanelText= "<div role=\"tabpanel\" class=\"scene-tabs tab-pane fade in active \" id=\""+nextID+"\">                               \
<div class=\"content\"><div class=\"narritive\"><input type=\"hidden\" name=\"pages["+page+"].scenes["+nextScene+"].id\" value=\"\"/>              \
<input type=\"hidden\" name=\"pages["+page+"].scenes["+nextScene+"].index\" value=\"\"/>                                                           \
<textarea id=\"Page"+page+"writingArea"+nextScene+"\" name=\"pages["+page+"].scenes["+nextScene+"].tinyMCEText\" class=\"tinyMCE\">                    \
</textarea></div></div><label for=\"Setting"+nextID+"\">Setting:</label>                                                                           \
<textarea id=\"Setting"+nextID+"\" name=\"pages["+page+"].scenes["+nextScene+"].setting\" readonly=\"readonly\" class=\"setting form-control\"></textarea></div>";

        $('#Page'+page+' .scene-tabs').append(scenePanelText);

        initTinyMCE("#Page"+page+"writingArea"+nextScene);

        var headerText ="<li role=\"presentation\" class=\"sceneTab active\">\
            <a href=\"#"+nextID+"\" aria-controls=\""+nextID+"\" role=\"tab\" data-toggle=\"tab\">\
            Scene "+sceneInt+"</a></li>";

        $('#tabHeader li:last').prev().after(headerText); //Insert the new scene before the '+'

        $('#nextScene').val(sceneInt); //Increment the amount of scenes we have


        $('#tab' + nextID).first().click();

    });



    /*  END Add a New Scene */
    /////////////////////////////////////////////////////////////


});


