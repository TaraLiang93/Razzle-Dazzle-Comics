package com.data.api.queries.external;

import com.data.UserData;
import com.data.api.containers.ResultContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.creation.Doodle;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 4/12/16.
 */
public class GetUserDataByIDCommand extends Readable {
    String userId;

    public GetUserDataByIDCommand(String userId){
        this.userId = userId;
    }

    @Override
    protected Filter getFilter() throws FetchException {
        Filter filter = null;
        if( userId != null ){
            filter = new Query.FilterPredicate("userId",
                    Query.FilterOperator.EQUAL,
                    this.userId);
        }
        else{
            throw new FetchException();
        }
        return filter;
    }

    @Override
    protected Class getType() {
        return UserData.class;
    }

    @Override
    public Container fetch() throws FetchException{ // TODO : do error validation, throw exceptions. Check OFY response codes
        if(this.userId == null || this.userId.equals("") ){
            throw new FetchException();
        }
        LoadResult<Doodle> LoadResultOfID = ofy().load().type(getType()).id(this.userId);
        ResultContainer<Doodle> resultContainer = new ResultContainer<Doodle>(LoadResultOfID);
        return resultContainer;
    }


}
