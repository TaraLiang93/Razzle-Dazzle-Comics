/**
 * Created by tara on 3/30/16.
 */

/**
 * All the buttons in drawing tool
  */
var clearButton, drawButton, selectButton;

/**
 * All the colors used in drawing tool
 */
var lineColor;

var canvas;
//var drawButton = $("Draw");


$(document).ready(function() {
    /**
     * Initilize all the drawing tool button avavilable.
     */
    clearButton = document.getElementById("Clear");//The button that trigger the clear canvas action
    drawButton = document.getElementById("Draw");//trigger the free draw action on canvas
    selectButton = document.getElementById("Select"); //turn the free drawing action off and the user can now select obj

    lineColor = document.getElementById("Drawing-color").value;

    /**
     * Link existing canvas with fabric and change some of the default values.
     * @type {fabric.Element}
     */
    canvas = new fabric.Canvas('canvas'); //link the canvas with a fabric canvas
    canvas.selectionBorderColor = ("rgb(227, 37, 107)"); //change selection border to our theme color
    canvas.selectionColor = " rgba(255,255,255,0)";// change selection color to transparent white
    canvas.renderOnAddRemove = true; //make sure all the change to the canvas is render instantly.
    //resizeCanvas(500,500);

    clearButton.onclick = function(){//if the clear button is click all the objects on the canvas is deleted by calling fcn clear.
        console.log("Clear Canvas: Clear clicked");
        Clear();
    };

    drawButton.onclick = function(){//allow the user to draw on the page freely
        console.log("Free Draw: drawing mode is on");
        canvas.freeDrawingBrush.color = lineColor;
        canvas.isDrawingMode = true;
    };

    selectButton.onclick = function(){//all the user to select drawed obj
        console.log("Selection: drawing mode is off");
        canvas.isDrawingMode = false;
    };

    document.getElementById("Drawing-color").onInput = function(){
        console.log("New Line Color is #"+lineColor);
    };
});


/**
 * change the working color with the inputted color
 */
function changeColor(){
    lineColor = document.getElementById("Drawing-color").value;
    console.log("New Line Color is "+lineColor);
}

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



