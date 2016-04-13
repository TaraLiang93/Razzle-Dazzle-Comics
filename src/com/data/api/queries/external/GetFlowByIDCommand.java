package com.data.api.queries.external;

import com.data.api.containers.ResultContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.creation.Doodle;
import com.data.structure.Flow;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 4/12/16.
 */
public class GetFlowByIDCommand extends Readable {

    Long flowId;

    public  GetFlowByIDCommand(Long flowId){
        this.flowId = flowId;
    }

    public GetFlowByIDCommand(String flowId){
        this.flowId = Long.parseLong(flowId);
    }

    @Override
    protected Filter getFilter() throws FetchException {
        Filter filter = null;
        if(this.flowId != null){
            filter = new FilterPredicate("flowId", FilterOperator.EQUAL, this.flowId);
        }
        else{
            throw new FetchException("GetFlowByIDCommand flowId is null");
        }
        return filter;
    }

    @Override
    protected Class getType() {
        return Flow.class;
    }

    @Override
    public Container fetch() throws FetchException{ // TODO : do error validation, throw exceptions. Check OFY response codes
        if(this.flowId == null){
            throw new FetchException();
        }
        LoadResult<Doodle> LoadResultOfID = ofy().load().type(getType()).id(this.flowId);
        ResultContainer<Doodle> resultContainer = new ResultContainer<Doodle>(LoadResultOfID);
        return resultContainer;
    }

}
