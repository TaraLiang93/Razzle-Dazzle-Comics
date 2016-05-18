/**
 * Created by Jason on 4/29/16.
 */


$(document).ready(function() {

    $(".chapterString").change(function() {
        console.log($(this).val());
        //$(".chapterForm").attr("action","/read/"+$(".goToSeries").val()+"/"+$("#chapterID").text());
        $(".chapterForm").submit();
    });

    $(".pageNumber").change(function() {
       console.log($(this).val());
        $("#pageNum").val($(this).val());
       $(".pageForm").submit();
    });

    $(".goToSeries").click(function(){
        $(".gotoSeriesForm").submit();
    });

    //if($("#canvasID").length > 0)
        loadAndDisabled($("#canvasID"), $("#loadCanvasJSON").text());
});