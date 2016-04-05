/**
 * Created by drodrigues on 4/4/16.
 */


$(document).ready(function(){

    $(".tinyMCE").each(function(){
        tinymce.init({
            selector: $(this).attr('id'),
            statusbar: false,
            menubar:false,
            height: "40%",
            toolbar: 'redo undo styleselect bold italic underline strikethrough alignleft aligncenter alignright bullist numlist outdent indent code',
            toolbar_items_size : 'small'
        });
    });
});