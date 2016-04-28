package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetFlowTaskByIDCommand;
import com.data.creation.Page;
import com.data.structure.FlowTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/27/16.
 */
public class UpdatePageSetFlowTask implements UpdateTask<Page> {

    Long flowTaskId;

    public UpdatePageSetFlowTask( Long flowTaskId){
        this.flowTaskId = flowTaskId;
    }

    public UpdatePageSetFlowTask( String flowTaskId){
        try {
            this.flowTaskId = Long.parseLong(flowTaskId);
        }
        catch ( NumberFormatException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<Page> update(Container<Page> entity) throws UpdateException, FetchException, CreateException {
        //get page
        Page page = entity.getResult();

        //get Flow Task
        Readable<FlowTask> flowTaskReadable = new GetFlowTaskByIDCommand(flowTaskId);
        FlowTask flowTask = flowTaskReadable.fetch().getResult();

        if( flowTask == null){ throw new UpdateException("flowTask is null"); }

        page.setFlowTask(flowTask.getKey());

        List<Page> pageList = new ArrayList<>();
        pageList.add(page);

        return pageList;
    }

}
