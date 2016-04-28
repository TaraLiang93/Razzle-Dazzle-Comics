package com.data.api.queries.external;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.creation.Page;
import com.data.structure.FlowTask;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.googlecode.objectify.Key;

/**
 * Created by Zhenya on 4/28/16.
 */
public class GetPagesInDoneFlowTaskCommand extends Readable<Page> {

    //We can make it generic to take the flowTypeID if we wanted to
    public GetPagesInDoneFlowTaskCommand(){}

    @Override
    protected Filter getFilter() throws FetchException {

        //get the key of done FlowTask
        Readable<FlowTask> flowTaskReadable = new GetFlowTaskByNameCommand("Done Task");
        FlowTask flowTask = flowTaskReadable.fetch().getResult();
        Key<FlowTask> doneFlowTaskKey = flowTask.getKey();

        //create filter
        Filter filter = new FilterPredicate("flowTask",
                FilterOperator.EQUAL,
                doneFlowTaskKey);
        return filter;

    }

    @Override
    protected Class<Page> getType() {
        return Page.class;
    }






}
