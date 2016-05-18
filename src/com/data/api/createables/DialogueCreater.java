package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.creation.Dialogue;

/**
 * Created by drodrigues on 5/17/16.
 */
public class DialogueCreater extends Createable<Dialogue> {

    private String dialogue;

    public DialogueCreater(String dialogue){
        this.dialogue = dialogue;
    }

    @Override
    protected Dialogue getEntity() throws CreateException, FetchException {
        return new Dialogue(dialogue);
    }
}
