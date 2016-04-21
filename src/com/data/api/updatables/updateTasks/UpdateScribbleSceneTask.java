package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.data.creation.Scene;
import com.model.SceneModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drodrigues on 4/18/16.
 */
public class UpdateScribbleSceneTask implements UpdateTask<Scene> {

    private SceneModel sceneModel;

    public UpdateScribbleSceneTask(SceneModel model){
        this.sceneModel = model;
    }

    @Override
    public List<Scene> update(Container<Scene> entity) throws UpdateException, FetchException, CreateException {


        Scene scene = entity.getResult();

        if(scene == null) throw new FetchException("Scene Does not exist!");
        else if(sceneModel == null) throw new UpdateException("Scene Model cannot be null");

        scene.setSetting(sceneModel.getSetting());
        scene.setTinyMCEText(sceneModel.getTinyMCEText());
        scene.setIndex(sceneModel.getIndex());

        List<Scene> temp = new ArrayList<>();
        temp.add(scene);
        return temp;
    }

}
