/**
 * Created by tara on 3/30/16.
 */

var clearButton, drawButton;
var canvas;
//var drawButton = $("Draw");


$(document).ready(function() {
    clearButton = document.getElementById("Clear");//The button that trigger the clear canvas action
    drawButton = document.getElementById("Draw");//trigger the free draw action on canvas

    canvas = new fabric.Canvas('canvas'); //link the canvas with a fabric canvas
    canvas.renderOnAddRemove = true; //make sure all the change to the canvas is render instantly.
    //resizeCanvas(500,500);

    clearButton.onclick = function(){//if the clear button is click all the objects on the canvas is deleted by calling fcn clear.
        console.log("Clear clicked");
        Clear();
    };

    drawButton.onclick = function(){
        console.log("drawing mode is on");
        canvas.isDrawingMode = true;
    };
});


/**
 * resizing the canvas size
 * @param canvas: The canvas that is being resize
 * @param width: The width of the new resize canvas.
 * @param height: The height of the new resize canvas.
 */
function resizeCanvas(width, height) {
    $('canvas').width = width;
    $('canvas').height = height;
    $('canvas').redraw();
}
/**
 * Clear the existing canvas by calling the canvas's clear method
 */
function Clear(){
    canvas.clear();
}



