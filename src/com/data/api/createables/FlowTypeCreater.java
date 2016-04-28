package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.structure.FlowType;

/**
 * Created by Zhenya on 4/27/16.
 */
public class FlowTypeCreater extends Createable<FlowType> {
    String flowTypeName;

    public FlowTypeCreater( String flowTypeName){
        this.flowTypeName = flowTypeName;

    }

    @Override
    protected FlowType getEntity() throws CreateException, FetchException {
        if(flowTypeName == null || flowTypeName.equals("")){
            throw new CreateException("invalid flowType name");
        }

        FlowType flowType = new FlowType();
        flowType.setFlowTypeName(flowTypeName);

        return flowType;
    }
}
