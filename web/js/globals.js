/**
 * Created by drodrigues on 4/13/16.
 */


function readURL(input, selector) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $(selector).attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}


function settingsEvent(selector){
    $(selector).click(function(){
        $('#redirectModal').val($(this).attr('id'));
        $('#modalSetting').val($(this).val());
        $('#settingModel').modal('show');
    });
}