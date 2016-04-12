package com.data.creation;

import com.google.appengine.labs.repackaged.org.json.JSONString;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class Canvas {

    @Id
    Long canvasId;

    JSONString canvasImage;

    public Canvas(){
        // shouldn't use this any more. should create with JSON String
    }
    public Canvas( JSONString canvasImage){
        this.canvasImage = canvasImage;
    }

    public JSONString getCanvasImage() {
        return canvasImage;
    }

    public void setCanvasImage(JSONString canvasImage) {
        this.canvasImage = canvasImage;
    }

    public Long getCanvasId() {
        return canvasId;
    }

    public void setCanvasId(Long canvasId) {
        this.canvasId = canvasId;
    }

    public Key<Canvas> getKey() {
        return Key.create(Canvas.class, canvasId);
    }
}
