/**
 * Created by drodrigues on 4/29/16.
 */

$(document).ready(function() {

    $(".seriesItem").each(function () {
        $(this).click(function () {
            $(location).attr("href", "/create/series/load/" + $(this).attr("id"));
        });
    });
});