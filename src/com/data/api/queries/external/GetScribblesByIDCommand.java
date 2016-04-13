package com.data.api.queries.external;

import com.data.api.containers.ResultContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.creation.Doodle;
import com.data.creation.Scribble;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 4/5/16.
 */
public class GetScribblesByIDCommand extends Readable{
    Long longID;
    Filter filter;
    /**
     * constructor for for the command which gets doodle with an ID
     * @param longID
     */
    public GetScribblesByIDCommand(Long longID){
        this.longID = longID;
    } //TODO : For all 'ID' input, allow for ID as string


    public GetScribblesByIDCommand(String stringID){
        this.longID = Long.parseLong(stringID);
    }

    @Override
    protected Filter getFilter() throws FetchException{// TODO : do error validation, throw exceptions
        if(longID != null) {
            filter = new Query.FilterPredicate("doodleId",
                    Query.FilterOperator.EQUAL,
                    this.longID);
        }
        else {
            throw new FetchException();
        }
        return filter;
    }


    @Override
    public Container fetch() throws FetchException{
        if(this.longID == null){
            throw new FetchException("GetScribblesByID command longId null");
        }
        LoadResult<Doodle> LoadResultOfID = ofy().load().type(getType()).id(this.longID);
        ResultContainer<Doodle> resultContainer = new ResultContainer<Doodle>(LoadResultOfID);
        return resultContainer;
    }


    protected Class getType() {
        return Scribble.class;
    }
}
