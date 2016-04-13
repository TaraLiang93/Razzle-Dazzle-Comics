package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.data.creation.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/12/16.
 */
public class UpdateCanvasTask implements UpdateTask<Canvas> {
    String updatedCanvasJSON;

    public UpdateCanvasTask( String updatedCanvasJSON){
        this.updatedCanvasJSON = updatedCanvasJSON;
    }

    @Override
    public List<Canvas> update(Container<Canvas> entity) throws UpdateException, FetchException {
        if( this.updatedCanvasJSON == null){
            throw new UpdateException();
        }

        Canvas canvas = entity.getResult();
        canvas.setCanvasImage(updatedCanvasJSON);

        List<Canvas> canvasList = new ArrayList<>();
        canvasList.add(canvas);

        return canvasList;
    }

}
