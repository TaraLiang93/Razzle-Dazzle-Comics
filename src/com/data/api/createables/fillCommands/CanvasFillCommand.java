package com.data.api.createables.fillCommands;

import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.FillDataCommand;
import com.data.creation.Canvas;

/**
 * Created by Zhenya on 4/12/16.
 */
public class CanvasFillCommand implements FillDataCommand<Canvas> {
    String canvasJSON;

    public CanvasFillCommand(){

    }

    public CanvasFillCommand( String canvasJSON){
        this.canvasJSON = canvasJSON;
    }

    @Override
    public void fillEntity(Canvas entity) throws CreateException {
        if( this.canvasJSON != null && !this.canvasJSON.equals("")){
            entity.setCanvasImage(this.canvasJSON);
        }

    }

}
