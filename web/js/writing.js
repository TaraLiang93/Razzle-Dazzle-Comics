/**
 * Created by drodrigues on 4/4/16.
 */


$(document).ready(function(){

    $('#tabHeader a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });

    $(".tinyMCE").each(function(){
        tinymce.init({
            selector: '#' + $(this).attr('id'),
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

});