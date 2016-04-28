package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.structure.Flow;

/**
 * Created by Zhenya on 4/27/16.
 */
public class FlowCreater extends Createable<Flow> {

    String flowName;

    public FlowCreater( String flowName){
        this.flowName = flowName;
    }

    @Override
    protected Flow getEntity() throws CreateException, FetchException {
        if(flowName == null || flowName.equals("")){
            throw new CreateException("invalid flow name");
        }

        Flow flow = new Flow();
        flow.setFlowName(flowName);

        return flow;
    }


}
