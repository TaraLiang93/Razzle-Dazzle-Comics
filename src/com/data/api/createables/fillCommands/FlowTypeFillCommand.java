package com.data.api.createables.fillCommands;

import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.FillDataCommand;
import com.data.structure.FlowType;

/**
 * Created by Zhenya on 4/27/16.
 */
public class FlowTypeFillCommand implements FillDataCommand<FlowType> {

    public FlowTypeFillCommand(){

    }

    @Override
    public void fillEntity(FlowType entity) throws CreateException {
        //the only field is the Type name which should be passed into the FlowType Creater
    }

}
