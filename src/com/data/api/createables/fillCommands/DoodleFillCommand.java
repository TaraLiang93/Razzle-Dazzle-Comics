package com.data.api.createables.fillCommands;

import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.FillDataCommand;
import com.data.creation.Doodle;

/**
 * Created by Zhenya on 4/5/16.
 */
public class DoodleFillCommand implements FillDataCommand<Doodle>{
    String canvas;

    public DoodleFillCommand(){
        this(null);
    }

    public DoodleFillCommand(String canvas){
        this.canvas = canvas;
    }

    @Override
    public void fillEntity(Doodle entity) throws CreateException{
        if( this.canvas != null){
            entity.setCanvasJSON(this.canvas);
        }
        else{
            throw new CreateException("DoodleFillCommand why is canvas null? ");
        }
    }
}
