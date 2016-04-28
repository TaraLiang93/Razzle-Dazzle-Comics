package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.internal.GetEntityFromKeyCommand;
import com.data.creation.Page;
import com.data.structure.FlowTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/27/16.
 */
public class UpdatePageMoveToPreviousFlowTask implements UpdateTask<Page> {

    public UpdatePageMoveToPreviousFlowTask(){  }

    @Override
    public List<Page> update(Container<Page> entity) throws UpdateException, FetchException, CreateException {

        //get page
        Page page = entity.getResult();

        //get FlowTask
        Readable<FlowTask> flowTaskReadable = new GetEntityFromKeyCommand<>( page.getFlowTask()  );
        FlowTask flowTask = flowTaskReadable.fetch().getResult();

        // update the flowTask to the previous one
        //if prev is null do nothing
        if( flowTask.getPrevTask() != null) {
            page.setFlowTask(flowTask.getPrevTask());
        }

        //make like
        List<Page> pageList = new ArrayList<>();
        pageList.add(page);

        return pageList;

    }

}

