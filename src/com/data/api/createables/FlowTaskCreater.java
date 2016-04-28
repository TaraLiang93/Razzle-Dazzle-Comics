package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.structure.FlowTask;

/**
 * Created by Zhenya on 4/27/16.
 */
public class FlowTaskCreater extends Createable<FlowTask> {
    String flowTaskName;
    int index;
    public FlowTaskCreater(String flowTaskName, int index){
        this.flowTaskName = flowTaskName;
        this.index = index;
    }

    @Override
    protected FlowTask getEntity() throws CreateException, FetchException {
        FlowTask flowTask = new FlowTask();
        flowTask.setFlowTaskName(flowTaskName); //set flowTaskName
        flowTask.setIndex(index); // set index
        return flowTask;
    }


}
