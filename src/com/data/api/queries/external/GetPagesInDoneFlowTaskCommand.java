package com.data.api.queries.external;

import com.data.api.containers.QueryContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.creation.Page;
import com.data.structure.FlowTask;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.googlecode.objectify.cmd.Query;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 4/28/16.
 */
public class GetPagesInDoneFlowTaskCommand extends Readable{
    Filter filter;

    //We can make it generic to take the flowTypeID if we wanted to
    public GetPagesInDoneFlowTaskCommand(){}

    @Override
    protected Filter getFilter() throws FetchException {

        //create filter
        filter = new FilterPredicate("flowTaskName",
                FilterOperator.EQUAL,
                "Done Task");
        return filter;

    }

    @Override
    protected Class<Page> getType() {
        return Page.class;
    }

    @Override
    public QueryContainer<GetPagesInDoneFlowTaskCommand> fetch() throws FetchException{
        // Filter flow by name
        Query<FlowTask> flowTaskQuery = ofy().load().type(FlowTask.class).filter(getFilter());
        FlowTask flowTask = flowTaskQuery.first().now();

        //get the key of done FlowTask
        Query pageQuery = ofy().load().type(Page.class).filter("flowTask",flowTask.getKey());
        QueryContainer<GetPagesInDoneFlowTaskCommand> queryContainer =  new QueryContainer(pageQuery);

        return queryContainer;

    }







}
