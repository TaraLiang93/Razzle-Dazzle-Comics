package com.data.api.createables.fillCommands;

import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.FillDataCommand;
import com.data.creation.Scene;

/**
 * Created by Zhenya on 4/13/16.
 */
public class SceneFillCommand implements FillDataCommand<Scene> {

    public SceneFillCommand(){}

    @Override
    public void fillEntity(Scene entity) throws CreateException {
        //TODO: Nothing yet?
    }
}
