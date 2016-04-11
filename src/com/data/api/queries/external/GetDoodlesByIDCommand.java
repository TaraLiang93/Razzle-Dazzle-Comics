package com.data.api.queries.external;

import com.data.api.containers.ResultContainer;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.creation.Doodle;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
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
    protected Filter getFilter() { // TODO : do error validation, throw exceptions
        filter = new FilterPredicate("doodleId",
                FilterOperator.EQUAL,
                this.id);
        return filter;
    }


    @Override
    public Container fetch(){ // TODO : do error validation, throw exceptions. Check OFY response codes
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
