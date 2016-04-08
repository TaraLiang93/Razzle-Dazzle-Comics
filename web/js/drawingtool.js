/**
 * Created by tara on 3/30/16.
 */

/**
 * All the buttons in drawing tool
  */
var clearButton, drawButton, selectButton, deleteButton, redoButton, undoButton ;

/**
 * All the colors and tool size used in drawing tool
 */
var lineColor, lineWidth, fontName;

/**
 * the stack that store all the deleted object
 */
var Stack, undoStack;

var canvas;


$(document).ready(function() {
    Stack = [];
    undoStack = [];
    fontName = "Times New Roman";
    /**
     * Initilize all the drawing tool button avavilable.
     */
    clearButton = document.getElementById("Clear");//The button that trigger the clear canvas action
    drawButton = document.getElementById("Draw");//trigger the free draw action on canvas
    selectButton = document.getElementById("Select"); //turn the free drawing action off and the user can now select obj
    deleteButton = document.getElementById("Delete");
    undoButton = document.getElementById("Undo");
    redoButton = document.getElementById("Redo");

    lineColor = document.getElementById("Drawing-color").value;
    lineWidth = $("#Line-width").text();
    //document.getElementById("Line-width").value;
    if(!lineWidth && lineWidth == ""){
        lineWidth = 10;
    }

    /**
     * Link existing canvas with fabric and change some of the default values.
     * @type {fabric.Element}
     */
    canvas = new fabric.Canvas('canvas'); //link the canvas with a fabric canvas
    canvas.selectionBorderColor = ("rgb(227, 37, 107)"); //change selection border to our theme color
    canvas.selectionColor = " rgba(255,255,255,0)";// change selection color to transparent white
    canvas.renderOnAddRemove = true; //make sure all the change to the canvas is render instantly.
    //var newHeight = $(window).height()-$("body").height();
    //var newWidth = $(window).width();
        //-$(".toolbar").width();
    canvas.setWidth(600);
    canvas.setHeight(600);
    //canvas.resizeCanvas(600,600);
    canvas.renderAll();

    //$(window).resize(function(){
    //
    //    var newHeight = $(window).height()-$("body").height();
    //    var newWidth = $(window).width()-$(".toolbar").width()-50;
    //    console.log(newHeight + " " + newWidth);
    //
    //    canvas.setHeight(newHeight);
    //    canvas.setWidth(newWidth);
    //    //canvas.calcOffset();
    //    canvas.renderAll();
    //});


    /**********************************************************************************************************************
     * Listeners
     */
    canvas.on("mouse:up", function(){
        if(canvas.isDrawingMode === true) {
            Stack.push(canvas._objects[canvas._objects.length - 1]);
        }
    });


    $(document).keyup(function(e){
        if((e.keyCode === 46) || (e.keyCode === 8)) { //if the user press delete or backspace
            if(canvas._activeObject.type != "i-text") {
                deleteFcn();
            }
        }
    });

    $("#Drawing-color").change(function(){
        changeColor();
        if(canvas._activeObject != null){
            setTextStyle(canvas._activeObject,'fill',lineColor);
        }

    });

    $("#Line-width").change(function(){
        changeLineWidth();
        if(canvas._activeObject != null){
            if(canvas._activeObject.type == "i-text"){
                setTextStyle(canvas._activeObject, 'fontSize', lineWidth);
            }
            setTextStyle(canvas._activeObject, 'strokeWidth', lineWidth);
        }
    })

    $("#Bold").click(function(){
        setTextStyle(canvas._activeObject, 'fontWeight', 'bold');
    });

    $("#Italic").click(function(){
       setTextStyle(canvas._activeObject, 'fontStyle', 'italic');
    });

    $("#Underline").click(function(){
       setTextStyle(canvas._activeObject, 'textDecoration', 'underline');
    });

    $("#Linethrough").click(function(){
       setTextStyle(canvas._activeObject,'textDecoration', 'line-through');
    });

    $("#Overline").click(function(){
       setTextStyle(canvas._activeObject, 'textDecoration', 'overline');
    });

    $("#Font-option").change(function(){
        console.log("Font is now "+this.value);
        fontName = this.value;
        $("#CurrentFont").html(this.value + ' <span class="caret"></span>');
        if(canvas._activeObject != null) {
            setTextStyle(canvas._activeObject, 'fontFamily', fontName);
        }
    });

    /**
     * End of Listener
     **********************************************************************************************************************/

    clearButton.onclick = function(){//if the clear button is click all the objects on the canvas is deleted by calling fcn clear.
        console.log("Clear Canvas: Clear clicked");
        Clear();
    };

    drawButton.onclick = function(){//allow the user to draw on the page freely
        console.log("Free Draw: drawing mode is on");
        console.log("New Line Width is: "+ lineWidth);
        canvas.freeDrawingBrush.color = lineColor;
        canvas.freeDrawingBrush.width = lineWidth;
        canvas.isDrawingMode = true;
    };

    selectButton.onclick = function(){//allow the user to select drawed obj
        console.log("Selection: drawing mode is off");
        canvas.isDrawingMode = false;
    };


    deleteButton.onclick = function(){
        deleteFcn();
    };

    undoButton.onclick = function() {
        if (Stack.length != 0) {
            var undoObj = Stack.pop();
            if(canvas._objects.indexOf(undoObj) === -1){
                canvas.add(undoObj);
            }
            else{
                canvas._objects[canvas._objects.indexOf(undoObj)].remove();
            }
            undoStack.push(undoObj);
        }
        else{
            alert("Cannot Undo");
        }
        console.log("Remaining in Stack: "+Stack);
    };

    redoButton.onclick = function(){
        if(undoStack.length != 0 ){
            var redoObj = undoStack.pop();
            if(canvas._objects.indexOf(redoObj) === -1) {
                canvas.add(redoObj);
            }
            else{
                canvas._objects[canvas._objects.indexOf(redoObj)].remove();
            }
            Stack.push(redoObj);
        }
        else{
            alert("Cannot redo.");
        }
    };

    /*********************************************************************************************************************
     * Creating a default shapes such as rectangle, square, triangle, circle and star, polygon.
     * User can resize the shape and the color is default to the lineColor
     */
    Rectangle.onclick = function(){
        canvas.isDrawingMode = false;
        var Rect = new fabric.Rect({
            width: (canvas.getWidth() /4),
            height: (canvas.getHeight()/6),
            left: 25,
            top: 25,
            fill: lineColor,
            borderOpacityWhenMoving: .5,
            borderColor: "rgb(227, 37, 107)"
        });
        canvas.add(Rect);
        Stack.push(canvas._objects[canvas._objects.length-1]);
    };

    Square.onclick = function(){
        canvas.isDrawingMode = false;
        var Sqr = new fabric.Rect({
            width: (canvas.getWidth()/5),
            height: (canvas.getHeight()/5),
            right: 25,
            bottom: 25,
            fill: lineColor,
            borderColor: "rgb(227, 37, 107)",
            borderOpacityWhenMove:.5
        });
        canvas.add(Sqr);
        Stack.push(canvas._objects[canvas._objects.length-1]);
    };

    Triangle.onclick = function(){
        canvas.isDrawingMode = false;
        var Tri = new fabric.Triangle({
            width: (canvas.getWidth()/6),
            height: (canvas.getHeight()/6),
            right: 25,
            bottom: 25,
            fill: lineColor,
            borderColor: "rgb(227, 37, 107)",
            borderOpacityWhenMove:.5
        });
        canvas.add(Tri);
        Stack.push(canvas._objects[canvas._objects.length-1]);
    };

    Circle.onclick = function(){
        canvas.isDrawingMode = false;
        var Cir = new fabric.Circle({
            radius: (canvas.getWidth()/8),
            right: 25,
            bottom: 25,
            fill: lineColor,
            borderColor: "rgb(227, 37, 107)",
            borderOpacityWhenMove:.5
        });
        canvas.add(Cir);
        Stack.push(canvas._objects[canvas._objects.length-1]);
    };

    Line.onclick = function(){
        canvas.isDrawingMode = false;
        canvas.add(new fabric.Line([canvas.getWidth()/10, canvas.getWidth()/10, 200, 200], {
            left: 170,
            top: 150,
            stroke: lineColor,
            x1: canvas.getWidth()/10,
            x2: canvas.getWidth()/10,
            y1: canvas.getWidth()/10,
            y2: 2*(canvas.getWidth()/10)
        }));
        Stack.push(canvas._objects[canvas._objects.length-1]);
    };
    /**
     *  End of shapes
     *************************************************************************************************************************/

    $("#Text").click(function(){
        canvas.isDrawingMode = false;
        console.log("Text clicked")
        var textbox = new fabric.IText("Text");
        setTextStyle(textbox, 'fontFamily', fontName);
        textbox.fill = lineColor;
        canvas.add(textbox);
        Stack.push(canvas._objects[canvas._objects.length-1]);
    });

    $("#Zoom-in").click(function(){//Zoom in on a canvas,  0 = zoom in, 1 = zoom out
        canvas.isDrawingMode = false;
        zoom(0);
    });

    $("#Zoom-out").click(function(){
        canvas.isDrawingMode = false;
        zoom(1);
    });

    $("#Fill").click(function(){
        canvas._activeObject.fill = lineColor;
        canvas.renderAll();
    });

    $("#Zoom-level").text(Math.round(canvas.getZoom()*100) + "%");

    $("#Image-file").change( function uploadImage(e) {
        canvas.isDrawingMode = false;
        var reader = new FileReader();
        reader.onload = function (event) {
            var img = new Image();
            img.src = event.target.result;
            img.onload = function () {
                var image = new fabric.Image(img);
                canvas.add(image);
            }

        }
        reader.readAsDataURL(e.target.files[0]);
    });

    $("#Bg-Image").change(function BgImage(e) {
        $("#Submit").prop('disabled',false);
        var reader = new FileReader();
        reader.onload = function (event) {
            var img = new Image();
            img.src = event.target.result;
            img.onload = function () {
                canvas.setBackgroundImage(img, canvas.renderAll.bind(canvas));

            }

        }
        reader.readAsDataURL(e.target.files[0]);
    });
});

/**
 * Zoom in or out of a canvas
 * @param num: either 0 == zoom in or 1 == zoom out
 */
function zoom(num){//0 == zoom in , 1 == zoom out
    console.log(canvas.getZoom());
    if(num === 0){
        canvas.setZoom(canvas.getZoom()*1.1);
    }
    else{
        if(canvas.getZoom() > 0.60) {
            canvas.setZoom(canvas.getZoom() / 1.1);
        }
        else{
            alert("Cannot Zoom anymore.");
        }
    }
    $("#Zoom-level").text(Math.round(canvas.getZoom()*100) + "%");
}

function deleteFcn(){
        var currentObj = canvas.getActiveObject();//Get the object the user selected
        var currentGrp = canvas.getActiveGroup();//Get the obj. group the user selected
        if(currentObj!= null){//user only select one object to be remove.
            deleteObj(currentObj);
        }
        else if(currentGrp != null){//user select multiple objs to be remove
            var totalSelected = currentGrp.size();
            var i = 0;
            for(;i<totalSelected; i++){//remove each object one by one.
                // When one obj is deleted the next obj move up to the deleted obj's position
                var beRemove = currentGrp._objects[0];
                deleteObj(beRemove);
            }
        }
        //make sure the user is not selected at anything
        canvas.discardActiveGroup();
        canvas.discardActiveObject();
}

function deleteObj(currentObj){
    Stack.push(currentObj);
    console.log("stack: "+Stack);
    currentObj.remove();
}

/**
 * Change the width of drawing line in free drawing
 */
function changeLineWidth(){
    lineWidth = document.getElementById("Line-width").value;
    if(canvas.isDrawingMode === true){
        console.log("New Line Width is: "+ lineWidth);
        canvas.freeDrawingBrush.width = lineWidth;
    }
}


/**
 * change the working color with the inputted color
 */
function changeColor(){
    lineColor = document.getElementById("Drawing-color").value;
    console.log("New Line Color is "+lineColor);
    if(canvas.isDrawingMode === true){
        canvas.freeDrawingBrush.color = lineColor;
    }

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

function setTextStyle(obj, style, value){
    if(obj.isEditing && obj.setSelectionStyles){
        var sty = {};
        sty[style] = value;
        obj.setSelectionStyles(sty).setCoords();
    }
    else{
        obj[style] = value;
    }
    canvas.renderAll();
}



