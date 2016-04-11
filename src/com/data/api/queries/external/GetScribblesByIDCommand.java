package com.data.api.queries.external;

import com.data.api.containers.ResultContainer;
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
    Long id;
    Filter filter;

    /**
     * constructor for for the command which gets doodle with an ID
     * @param id
     */
    public GetScribblesByIDCommand(Long id){
        this.id = id;
    } //TODO : For all 'ID' input, allow for ID as string

    @Override
    protected Filter getFilter() {// TODO : do error validation, throw exceptions
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
