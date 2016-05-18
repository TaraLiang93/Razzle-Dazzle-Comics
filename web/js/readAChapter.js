/**
 * Created by Jason on 4/29/16.
 */


$(document).ready(function() {

    $(".chapterString").change(function() {
        console.log($(this).val());
    });

    $(".pageNumber").change(function() {
       console.log($(this).val());
        $(location).attr("href","/read/"+$(".breadcrumb").find(".active").text()+"/"+$("#chapterID").text()+"?pageNum="+$(this).val()+"&seriesID=" + $("#seriesID").text());
    });

});