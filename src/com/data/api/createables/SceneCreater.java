package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.creation.Dialogue;
import com.data.creation.Scene;
import com.model.SceneModel;

import java.util.List;

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
            throw new CreateException("Scene Model cannot be null");
        }
        Scene scene =  new Scene(sceneModel.getIndex(),
                                sceneModel.getSetting(),
                                sceneModel.getTinyMCEText());



        return scene;
    }

    public static List<Dialogue> parseDialogue(String tinyMCEText){

        String[] dialogue = tinyMCEText.split("\\s*<p>\\s*|\\s*</p>\\s*");

        for(int i=0; i < dialogue.length; i++){

        }
        return null;
    }

}
