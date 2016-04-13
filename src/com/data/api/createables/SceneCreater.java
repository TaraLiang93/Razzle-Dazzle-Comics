package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.creation.Scene;
import com.model.SceneModel;

/**
 * Created by Zhenya on 4/13/16.
 */
public class SceneCreater extends Createable<Scene> {

    SceneModel sceneModel;

    public SceneCreater(SceneModel sceneModel){
        this.sceneModel = sceneModel;
    }

    @Override
    protected Scene getEntity() throws CreateException, FetchException {
        if(sceneModel == null){
            throw new CreateException();
        }
        Scene scene =  new Scene(0, sceneModel.getSetting());
        scene.setTinyMCEText( sceneModel.getTinyMCEText() );
        scene.setIndex( sceneModel.getIndex() );

        return scene;
    }

}
