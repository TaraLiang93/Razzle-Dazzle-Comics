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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drodrigues on 4/18/16.
 */
public class UpdatePageTask implements UpdateTask<Page> {

    private PageModel pageModel;

    public UpdatePageTask(PageModel pageModel){
        this.pageModel = pageModel;
    }


    @Override
    public List<Page> update(Container<Page> entity) throws UpdateException, FetchException, CreateException {

        Page page = entity.getResult();

        if(pageModel == null || page == null) throw new UpdateException("Page Info can't be null");


        for(SceneModel sceneModel : pageModel.getScenes()){

            if(sceneModel.getId() == null || sceneModel.getId().equals("")){ //New Scene
                Createable<Scene> sceneCreater = new SceneCreater(sceneModel);
                Scene sceneCreated = sceneCreater.createEntity(new SceneFillCommand());
                page.getSceneList().add(sceneCreated.getKey());
            }
            else{//Update the old one
                new SceneUpdater()
                        .updateEntity(
                                new GetSceneByIDCommand(sceneModel.getId()),
                                new UpdateSceneTask(sceneModel)
                        );
            }

        }

        List<Page> pages = new ArrayList<>();
        pages.add(page);
        return pages;
    }
}
