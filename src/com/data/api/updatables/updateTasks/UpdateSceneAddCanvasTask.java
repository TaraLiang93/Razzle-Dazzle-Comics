package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetCanvasByIDCommand;
import com.data.creation.Canvas;
import com.data.creation.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 5/17/16.
 */
public class UpdateSceneAddCanvasTask implements UpdateTask<Scene> {

    Long canvasID;
    public UpdateSceneAddCanvasTask( Long canvasID){
        this.canvasID = canvasID;
    }
     public UpdateSceneAddCanvasTask(String canvasID){
         this.canvasID = Long.parseLong( canvasID);
     }

    @Override
    public List<Scene> update(Container<Scene> entity) throws UpdateException, FetchException, CreateException {
        Scene scene = entity.getResult();

        //get the canvas
        Readable<Canvas> canvasReadable = new GetCanvasByIDCommand(canvasID);
        Canvas canvas = canvasReadable.fetch().getResult();

        scene.setCanvasElement(canvas.getKey());

        List<Scene> sceneList = new ArrayList<>();
        sceneList.add(scene);
        return sceneList;
    }
}
