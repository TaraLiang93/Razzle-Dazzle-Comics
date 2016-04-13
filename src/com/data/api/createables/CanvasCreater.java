package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.Createable;
import com.data.creation.Canvas;

/**
 * Created by Zhenya on 4/12/16.
 */
public class CanvasCreater extends Createable<Canvas> {
    String canvasJSON;
    public CanvasCreater(String canvasJSON){
        this.canvasJSON = canvasJSON;
    }

    @Override
    protected Canvas getEntity() throws CreateException {
        if(this.canvasJSON == null || this.canvasJSON.equals("")){
            throw new CreateException();
        }
        Canvas canvas = new Canvas(canvasJSON);
        return canvas;
    }
}
