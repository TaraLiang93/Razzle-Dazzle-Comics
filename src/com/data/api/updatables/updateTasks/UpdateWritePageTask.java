package com.data.api.updatables.updateTasks;

import com.data.api.createables.SceneCreater;
import com.data.api.createables.fillCommands.SceneFillCommand;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Createable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetSceneByIDCommand;
import com.data.api.updatables.SceneUpdater;
import com.data.creation.Page;
import com.data.creation.Scene;
import com.model.PageModel;
import com.model.SceneModel;
import com.model.WritePageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drodrigues on 5/17/16.
 */
public class UpdateWritePageTask implements UpdateTask<Page> {


    private WritePageModel writeModel;

    public UpdateWritePageTask(WritePageModel model){
        this.writeModel = model;
    }


    @Override
    public List<Page> update(Container<Page> entity) throws UpdateException, FetchException, CreateException {
        Page page = entity.getResult();

        if(writeModel == null || page == null) throw new UpdateException("Page Info can't be null");

        if(writeModel.getPages().isEmpty()) throw new UpdateException("No Pages found in model");

        PageModel pageModel = writeModel.getPages().get(0);

        for(SceneModel sceneModel : pageModel.getScenes()){

            if(sceneModel.getId() == null || sceneModel.getId().equals("")){ //New Scene
                Createable<Scene> sceneCreater = new SceneCreater(sceneModel);
                Scene sceneCreated = sceneCreater.createEntity(new SceneFillCommand());
                page.getSceneList().add(sceneCreated.getKey());
            }
            else{//Update the old one
                //Update this scene
                new SceneUpdater()
                        .updateEntity(
                                new GetSceneByIDCommand(sceneModel.getId()),
                                new UpdateScribbleSceneTask(sceneModel)
                        );
            }
        }

        List<Page> pages = new ArrayList<>();
        pages.add(page);
        return pages;
    }
}
