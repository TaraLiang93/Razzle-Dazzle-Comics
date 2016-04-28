package com.data.api.queries.external;

import com.data.api.containers.ResultContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.structure.FlowType;
import com.google.appengine.api.datastore.Query;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 4/27/16.
 */
public class GetFlowTypeByIDCommand extends Readable<FlowType> {
    Long flowTypeId;

    public GetFlowTypeByIDCommand(Long flowTypeId){
        this.flowTypeId = flowTypeId;
    }

    @Override
    protected Query.Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class<FlowType> getType() {
        return FlowType.class;
    }

    @Override
    public Container fetch() throws FetchException{ // TODO : do error validation, throw exceptions. Check OFY response codes
        if(this.flowTypeId == null){
            throw new FetchException();
        }
        LoadResult<FlowType> LoadResultOfID = ofy().load().type(getType()).id(this.flowTypeId);
        ResultContainer<FlowType> resultContainer = new ResultContainer<FlowType>(LoadResultOfID);
        return resultContainer;
    }
}
