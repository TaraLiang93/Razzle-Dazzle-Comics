package com.data.api.queries.external;

import com.data.api.containers.ResultContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.structure.FlowTask;
import com.google.appengine.api.datastore.Query;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 4/27/16.
 */
public class GetFlowTaskByIDCommand extends Readable<FlowTask> {
    Long flowTaskId;

    public GetFlowTaskByIDCommand( Long flowTaskId ){
        this.flowTaskId = flowTaskId;
    }

    public GetFlowTaskByIDCommand( String flowTaskId){
        try {
            this.flowTaskId = Long.parseLong(flowTaskId);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
    }



    @Override
    protected Query.Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class<FlowTask> getType() {
        return FlowTask.class;
    }

    @Override
    public Container fetch() throws FetchException{ // TODO : do error validation, throw exceptions. Check OFY response codes
        if(this.flowTaskId == null){
            throw new FetchException();
        }
        LoadResult<FlowTask> LoadResultOfID = ofy().load().type(getType()).id(this.flowTaskId);
        ResultContainer<FlowTask> resultContainer = new ResultContainer<FlowTask>(LoadResultOfID);
        return resultContainer;
    }

}
