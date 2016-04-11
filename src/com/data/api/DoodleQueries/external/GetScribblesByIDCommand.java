package com.data.api.DoodleQueries.external;

import com.data.api.Container;
import com.data.api.Readable;
import com.data.api.ResultContainer;
import com.data.creation.Doodle;
import com.data.creation.Scribble;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.*;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 4/5/16.
 */
public class GetScribblesByIDCommand extends Readable{
    Long id;
    Filter filter;

    /**
     * constructor for for the command which gets doodle with an ID
     * @param id
     */
    public GetScribblesByIDCommand(Long id){
        this.id = id;
    }

    @Override
    protected Filter getFilter() {
        filter = new Query.FilterPredicate("doodleId",
                Query.FilterOperator.EQUAL,
                this.id);
        return filter;
    }


    @Override
    @Deprecated
    public Container fetch(){

        LoadResult<Doodle> LoadResultOfID = ofy().load().type(getType()).id(this.id);
        ResultContainer<Doodle> resultContainer = new ResultContainer<Doodle>(LoadResultOfID);
        return resultContainer;
    }


    protected Class getType() {
        return Scribble.class;
    }
}
