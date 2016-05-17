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


function pageEvent(selector){
    $(selector).click(function(){
        var index = $('#pageTabs li').index($(this).parent());
        $('#currPage').val(index);
        $('.page-pane:visible').hide();
        var id = $(selector).attr('href');
        $(id).show();
        $(this).tab('show');
    });
}

function addNewScene(){

    var page = $('#currPage').val();
    var scenes = $('#Page'+page+' .sceneTabHeader .sceneTab').length;
    var nextScene = scenes;
    var nextID = "Page"+page+"Scene"+nextScene;


    //Hide the active pages
    $('#Page'+page+' .scene-list .active').each(function(){
        $(this).removeClass('active');
    });


    var scenePanelText= "<div role=\"tabpanel\" class=\"scene-tabs tab-pane fade in active \" id=\""+nextID+"\">                               \
<div class=\"content\"><div class=\"narritive\"><input type=\"hidden\" name=\"pages["+page+"].scenes["+nextScene+"].id\" value=\"\"/>              \
<input type=\"hidden\" name=\"pages["+page+"].scenes["+nextScene+"].index\" value=\""+nextScene+"\"/>                                                           \
<textarea id=\"Page"+page+"writingArea"+nextScene+"\" name=\"pages["+page+"].scenes["+nextScene+"].tinyMCEText\" class=\"tinyMCE\">                    \
</textarea></div></div><label for=\"Setting"+nextID+"\">Setting:</label>                                                                           \
<textarea id=\"Setting"+nextID+"\" name=\"pages["+page+"].scenes["+nextScene+"].setting\" readonly=\"readonly\" class=\"setting form-control\"></textarea></div>";


    //Insert the new scene after the last one
    var nextBody = $('#Page'+page+' .tab-content:first');
    $(nextBody).append(scenePanelText);

    initTinyMCE("#Page"+page+"writingArea"+nextScene);


    var headerText = "<li role=\"presentation\" class=\"sceneTab active\">                              \
                         <a id=\"tabPage"+page+"Scene"+nextScene+"\" href=\"#"+nextID+"\"                   \
                         aria-controls=\""+nextID+"\" role=\"tab\" data-toggle=\"tab\">Scene "+ (nextScene + 1) +"</a></li>";

    var nextTab = $('#Page'+page+' .sceneTabHeader li:last');

    //Put the new Tab after the last one but before the add scene button
    $(nextTab).before(headerText);

    var nextSetting = $('#Setting'+nextID);
    settingsEvent(nextSetting);


    $('#Page'+page+' .sceneTabHeader li:nth-last-child(2)').tab('show');

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
        settingsEvent(this);
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
        pageEvent(this);
    });


    /*  Add a New Scene */

    $('.add-contact').each(function(){
        $(this).click(function(){
            addNewScene();
        });
    });

    /*  END Add a New Scene */
    /////////////////////////////////////////////////////////////




    ///////// BAD //////////




/*  Add a New Page */

    $('#addPage').click(function(){

        var page = $('.page-pane').length;
        $('#currPage').val(page);
/*
        $('.pageList .page-tab').each(function(){
            if($(this).hasClass('active')){
                $(this).removeClass('active');
            }
        });
*/

        $(".page-pane").each(function(){
            if($(this).hasClass('active')){
                $(this).removeClass('active');
                $(this).hide();
            }
        });



            var newPage= "<div role=\"tabpanel\" class=\"page-pane tab-pane fade in active \" id=\"Page"+page+"\">                                     \
<input type=\"hidden\" name=\"pages["+page+"].id\" value=\"\"/><ul class=\"sceneTabHeader nav nav-tabs\" role=\"tablist\">    \
<li role=\"presentation\" class=\"sceneTab active\"><a id=\"tabPage"+page+"Scene0\" href=\"#Page"+page+"Scene0\"              \
aria-controls=\"Page"+page+" Scene0\" role=\"tab\" data-toggle=\"tab\">Scene 1</a></li><li>                                   \
<a id=\"addScene\" href=\"#plus\" class=\"add-contact\"> + </a></li></ul><div  class=\"scene-list tab-content\">              \
<div role=\"tabpanel\" class=\"scene-tabs tab-pane fade in active \" id=\"Page"+page+"Scene0\"><div class=\"content\">        \
<div class=\"narritive\"><input type=\"hidden\" name=\"pages["+page+"].scenes[0].id\" value=\"\"/>                            \
<input type=\"hidden\" name=\"pages["+page+"].scenes[0].index\" value=\"0\"/>                                                 \
<textarea id=\"Page"+page+"writingArea0\" name=\"pages["+page+"].scenes[0].tinyMCEText\" class=\"tinyMCE\"></textarea></div>  \
</div><label for=\"SettingPage"+page+"Scene0\">Setting:</label>                                                               \
<textarea id=\"SettingPage"+page+"Scene0\" name=\"pages["+page+"].scenes[0].setting\" readonly=\"readonly\"                   \
class=\"setting form-control\"></textarea></div></div></div>";


        $('#pageHolder').append(newPage);


        initTinyMCE("#Page"+page+"writingArea0");

        var nextSetting = $('#SettingPage'+page+'Scene0');
        settingsEvent(nextSetting);


        var newPageTab = "<li role=\"presentation\" class=\"page-tab active\"><a href=\"#Page"+page+"\"                       \
aria-controls=\"Page"+page+"\" role=\"tab\" data-toggle=\"tab\">Page "+ (page + 1) +"</a></li>";


        //$('#pageTabs .page-tab:last').after(newPageTab);
        $('#addPage').parent().before(newPageTab);

        pageEvent($('#pageTabs .page-tab:last a'));

        $('#pageTabs .page-tab:last').tab('show');



        var thing = $('#Page'+page+' .sceneTabHeader li:last .add-contact');
        $(thing).click(addNewScene);
    });

    /*  END Add a New Page */
    ////////////////////////////////////////////////////////////










});


