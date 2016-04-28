package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetFlowTypeByIDCommand;
import com.data.structure.FlowTask;
import com.data.structure.FlowType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/27/16.
 */
public class UpdateFlowTaskSetTypeTask implements UpdateTask<FlowTask> {

    Long flowTypeId;
    public UpdateFlowTaskSetTypeTask( Long flowTypeId ){
        this.flowTypeId = flowTypeId;
    }

    public UpdateFlowTaskSetTypeTask(String flowTypeId){
        try {
            this.flowTypeId = Long.parseLong(flowTypeId);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<FlowTask> update(Container<FlowTask> entity) throws UpdateException, FetchException, CreateException {
        FlowTask flowTask = entity.getResult();

        //Get flowType
        Readable<FlowType> flowTypeReadable = new GetFlowTypeByIDCommand(flowTypeId);
        FlowType flowType = flowTypeReadable.fetch().getResult();

        //Set flowType
        flowTask.setFlowTypeKey(flowType.getKey());

        List<FlowTask> flowTaskList = new ArrayList<>();
        flowTaskList.add(flowTask);

        return flowTaskList;
    }

}
