/**
 * Created by Jason on 5/14/16.
 */

$(document).ready(function() {

    $(".topComics").each(function(){
        $(this).click(function() {
            //$.ajax({
            //    url : "/read/" + $(this).find(".chapterTitle").text(),
            //    type : "GET",
            //    async : false,
            //    data : {seriesID : $(this).attr("id")}
            //})
            //    .done(function() {
            //        console.log("it went here");
            //    })
            //    .fail(function(){
            //        console.log("but why");
            //    });
            $(this).submit();

        });
    })

});
