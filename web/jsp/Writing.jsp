<%--To include this file it take a param num that must be passed in so that each one is unique--%>
<div class="tinyWrapper" style="padding: 0;">
    <script src='//cdn.tinymce.com/4/tinymce.min.js'></script>
    <script>
        tinymce.init({
            selector: '#mytextarea${param.num}',
            statusbar: false,
            menubar:false,
            height: "338",
            toolbar: 'redo undo styleselect bold italic underline strikethrough alignleft aligncenter alignright bullist numlist outdent indent code',
            toolbar_items_size : 'small',

        });
    </script>
    <textarea id="mytextarea${param.num}" class="tinyMCE" style="position: relative" >Please Start writing</textarea>
</div>