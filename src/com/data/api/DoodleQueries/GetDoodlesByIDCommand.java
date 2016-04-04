package com.data.api.DoodleQueries;

import com.data.api.Container;
import com.data.api.QueryContainer;
import com.data.api.Readable;
import com.data.api.ResultContainer;
import com.data.creation.Doodle;
import com.google.appengine.api.datastore.Query.*;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.googlecode.objectify.LoadResult;
import static com.googlecode.objectify.ObjectifyService.ofy;


/**
 * Created by Zhenya on 4/4/16.
 */
public class GetDoodlesByIDCommand extends Readable{
    Long id;
    Filter filter;

    /**
     * constructor for for the command which gets doodle with an ID
     * @param id
     */
    public GetDoodlesByIDCommand(Long id){
        this.id = id;
    }

    @Override
    @Deprecated
    protected Filter getFilter() {
        filter = new FilterPredicate("doodleId",
                FilterOperator.EQUAL,
                this.id);
        return filter;
    }

    /**
     *  This exists only so that it extends readable so the querying follows the same format shouldn't be used
     * @return
     */
    @Override
    @Deprecated
    public Container fetch(){
//        Query doodleById = ofy().load().type(getType()).filter(getFilter());
//        QueryContainer<GetDoodlesByIDCommand> commandContainer = new QueryContainer(doodleById);
//        return  commandContainer;
        LoadResult<Doodle> LoadResultOfID = ofy().load().type(getType()).id(this.id);
        ResultContainer<Doodle> resultContainer = new ResultContainer<Doodle>(LoadResultOfID);
        return resultContainer;
    }




    protected Class getType() {
        return Doodle.class;
    }
}
