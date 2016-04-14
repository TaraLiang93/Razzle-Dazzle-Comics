package com.data.api.createables;

import com.data.api.createables.fillCommands.SceneFillCommand;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.creation.Page;
import com.data.creation.Scene;
import com.googlecode.objectify.Key;
import com.model.PageModel;
import com.model.SceneModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/13/16.
 */
public class PageCreater extends Createable<Page> {

    PageModel pageModel;
    public PageCreater(PageModel pageModel){
        this.pageModel = pageModel;
    }

    @Override
    protected Page getEntity() throws CreateException, FetchException{

        if(pageModel == null){
            throw new CreateException("Page Model should not be null");
        }

        Page page = new Page();
//        page.setSummary( pageModel.getSummary());
//        if(pageModel.getLayout() != null) {
//            page.setLayout(pageModel.getLayout().getKey());
//        }
//        page.setNumRevisions(pageModel.getNumRevisions());

        List<Key<Scene>> sceneList = new ArrayList<>();

        for(SceneModel sceneModel: pageModel.getScenes() ){
                Createable<Scene> sceneCreater = new SceneCreater(sceneModel);
                Scene sceneCreated = sceneCreater.createEntity( new SceneFillCommand());
                sceneList.add(sceneCreated.getKey());
        }

        page.setSceneList(sceneList);
        return page;
    }
}
