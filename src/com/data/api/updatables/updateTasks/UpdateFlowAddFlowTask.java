package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetFlowTaskByIDCommand;
import com.data.structure.Flow;
import com.data.structure.FlowTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/27/16.
 */
public class UpdateFlowAddFlowTask implements UpdateTask<Flow> {

    Long flowTaskId;

    public UpdateFlowAddFlowTask( Long flowTaskId){

        this.flowTaskId = flowTaskId;

    }

    public UpdateFlowAddFlowTask( String flowTaskId){
        try {
            this.flowTaskId = Long.parseLong(flowTaskId);
        }
        catch ( NumberFormatException ex){
            ex.printStackTrace();
        }
    }


    @Override
    public List<Flow> update(Container<Flow> entity) throws UpdateException, FetchException, CreateException {
        // get flow
        Flow flow = entity.getResult();

        // get flowTask
        Readable<FlowTask> flowTaskReadable = new GetFlowTaskByIDCommand(flowTaskId);
        FlowTask flowTask = flowTaskReadable.fetch().getResult();

        // add flowTask to Flow
        flow.addFlowTaskToFlowTaskList( flowTask.getKey() );


        List<Flow> flowList = new ArrayList<>();
        flowList.add(flow);

        return flowList;
    }
}
