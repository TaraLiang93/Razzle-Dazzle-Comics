package com.data.api.createables;

import com.data.api.createables.fillCommands.NoWork;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.creation.Dialogue;
import com.data.creation.Scene;
import com.model.SceneModel;

import java.util.ArrayList;
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

        List<Dialogue> getDialogs = parseDialogue(sceneModel.getTinyMCEText());
        for(Dialogue d : getDialogs){
            scene.getDialogueList().add(d.getKey());
        }

        return scene;
    }

    public static List<Dialogue> parseDialogue(String tinyMCEText){

        String[] dialogue = tinyMCEText.split("\\s*<p>\\s*|\\s*</p>\\s*");

        List<String> dialogueStrings = new ArrayList<>();
        for(int i = 0; i < dialogue.length; i++){
            if(dialogue[i].trim().startsWith("&") || dialogue[i].trim().equals("")){
                System.out.println("nahh");
            }
            else{
                dialogueStrings.add(dialogue[i]);
            }
        }

        List<Dialogue> dialogs = new ArrayList<>();

        for(int i = 0;i < dialogueStrings.size();i++){
            try {
                Dialogue dialog = new DialogueCreater(dialogueStrings.get(i)).createEntity(new NoWork<Dialogue>());
                dialogs.add(dialog);
            } catch (CreateException | FetchException e) {
                e.printStackTrace();
            }
        }

        return dialogs;
    }

}
