package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.interfaces.Updateable;
import com.data.api.queries.internal.GetEntityFromKeyCommand;
import com.data.api.updatables.CanvasUpdater;
import com.data.creation.Canvas;
import com.data.creation.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/22/16.
 */
public class UpdateDrawSceneTask implements UpdateTask<Scene> {
    //update a scene's canvasJSON
    String JSONString;

    public UpdateDrawSceneTask( String JSONString ){
        this.JSONString = JSONString;
    }

    @Override
    public List<Scene> update(Container<Scene> entity) throws UpdateException, FetchException, CreateException {
        Scene scene = entity.getResult();

        Updateable<Canvas> canvasUpdateable = new CanvasUpdater();
        Readable<Canvas> canvasReadable = new GetEntityFromKeyCommand<>( scene.getCanvasElement() );
        canvasUpdateable.updateEntity( canvasReadable, new UpdateCanvasTask( JSONString) );

        //updateSceneTask calls update CanvasTask
        List<Scene> sceneList = new ArrayList<>();

        return sceneList;
    }


}
