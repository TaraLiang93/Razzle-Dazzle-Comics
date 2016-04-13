package com.data.creation;

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

    String canvasJSON;

    public Canvas(){
        // shouldn't use this any more. should create with JSON String
    }
    public Canvas( String canvasImage){
        this.canvasJSON = canvasImage;
    }

    public String getCanvasImage() {
        return canvasJSON;
    }

    public void setCanvasImage(String canvasImage) {
        this.canvasJSON = canvasImage;
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
