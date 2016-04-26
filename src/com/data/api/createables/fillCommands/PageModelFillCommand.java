package com.data.api.createables.fillCommands;

import com.data.api.createables.SceneCreater;
import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.Createable;
import com.data.api.interfaces.FillDataCommand;
import com.data.creation.Page;
import com.data.creation.Scene;
import com.googlecode.objectify.Key;
import com.model.PageModel;
import com.model.SceneModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drodrigues on 4/26/16.
 */
public class PageModelFillCommand implements FillDataCommand<Page>{

    private PageModel pageModel;

    public PageModelFillCommand(PageModel model){
           this.pageModel = model;
    }


    @Override
    public void fillEntity(Page entity) throws CreateException {


        if(pageModel == null){
            throw new CreateException("Page Model should not be null");
        }
        else if(entity == null){
            throw new CreateException("Page Entity cannot be null");
        }

        List<Key<Scene>> sceneList = new ArrayList<>();

        for(SceneModel sceneModel: pageModel.getScenes() ){
            Createable<Scene> sceneCreater = new SceneCreater(sceneModel);
            Scene sceneCreated = sceneCreater.createEntity( new SceneFillCommand());
            sceneList.add(sceneCreated.getKey());
        }

        entity.setSceneList(sceneList);

    }
}
