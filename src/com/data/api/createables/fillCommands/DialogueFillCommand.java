package com.data.api.createables.fillCommands;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.FillDataCommand;
import com.data.creation.Dialogue;

/**
 * Created by drodrigues on 5/18/16.
 */
public class DialogueFillCommand implements FillDataCommand<Dialogue> {



    @Override
    public void fillEntity(Dialogue entity) throws CreateException, FetchException {

    }
}
