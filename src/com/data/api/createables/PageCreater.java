package com.data.api.createables;

import com.data.api.createables.fillCommands.SceneFillCommand;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.api.interfaces.Readable;
import com.data.api.queries.external.GetSceneByIDCommand;
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

        Page page = new Page();
        page.setSummary( pageModel.getSummary());
        page.setLayout( pageModel.getLayout().getKey() );
        page.setNumRevisions(pageModel.getNumRevisions());

        List<Key<Scene>> sceneList = new ArrayList<>();
        for(SceneModel sceneModel: pageModel.getScenes() ){
           Readable<Scene> getScene = new GetSceneByIDCommand(sceneModel.getId() );
            Scene scene = getScene.fetch().getResult();
            if( scene != null){
                sceneList.add( scene.getKey() );
            }
            else{
                Createable<Scene> sceneCreater = new SceneCreater(sceneModel);
                Scene sceneCreated = sceneCreater.createEntity( new SceneFillCommand());
                sceneList.add(sceneCreated.getKey());
            }
        }

        page.setSceneList( sceneList);

//        pageModel.getComments(); ignore for now
        return page;
    }
}
