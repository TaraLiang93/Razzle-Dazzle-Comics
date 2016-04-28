package com.data.api.createables.fillCommands;

import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.FillDataCommand;
import com.data.structure.Flow;

/**
 * Created by Zhenya on 4/27/16.
 */
public class FlowFillCommand implements FillDataCommand<Flow> {

    public FlowFillCommand(){}

    @Override
    public void fillEntity(Flow entity) throws CreateException {
        // adding the flow task should be in update flow so nothing here
    }

}
