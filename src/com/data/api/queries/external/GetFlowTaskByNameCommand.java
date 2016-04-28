package com.data.api.queries.external;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.structure.FlowTask;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

/**
 * Created by Zhenya on 4/28/16.
 */
public class GetFlowTaskByNameCommand extends Readable<FlowTask> {

    String flowTaskName;

    public GetFlowTaskByNameCommand( String flowTaskName){
        this.flowTaskName = flowTaskName;
    }

    @Override
    protected Filter getFilter() throws FetchException {
        //create filter
        Filter filter = new FilterPredicate("flowTaskName",
                FilterOperator.EQUAL,
                this.flowTaskName);
        return filter;
    }

    @Override
    protected Class<FlowTask> getType() {
        return FlowTask.class;
    }

}
