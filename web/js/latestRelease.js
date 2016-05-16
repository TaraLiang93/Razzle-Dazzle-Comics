/**
 * Created by Jason on 5/14/16.
 */

$(document).ready(function() {

    $(".latestChapter").each(function(){
        $(this).click(function(){
            //$(location).attr("href", "/read/" +$(this).find(".chapterTitle").text());
            $(this).submit();
        });
    });

});