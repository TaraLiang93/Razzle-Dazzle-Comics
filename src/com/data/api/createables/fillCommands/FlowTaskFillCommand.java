package com.data.api.createables.fillCommands;

import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.FillDataCommand;
import com.data.structure.FlowTask;

/**
 * Created by Zhenya on 4/27/16.
 */
public class FlowTaskFillCommand implements FillDataCommand<FlowTask> {

    public FlowTaskFillCommand(){}

    @Override
    public void fillEntity(FlowTask entity) throws CreateException {

    }
}
