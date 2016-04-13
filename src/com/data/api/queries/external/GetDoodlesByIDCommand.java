package com.data.api.queries.external;

import com.data.api.containers.ResultContainer;
import com.data.api.exceptions.FetchException;
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


    /**
     * constructor for for the command which gets doodle with an ID
     * @param id
     */
    public GetDoodlesByIDCommand(Long id){
        this.id = id;
    }

    public GetDoodlesByIDCommand(String strID){
        this.id = Long.parseLong(strID);
    }


    @Override
    protected Filter getFilter() throws FetchException{
        Filter filter;
        if( this.id != null) {
            filter = new FilterPredicate("doodleId",
                    FilterOperator.EQUAL,
                    this.id);
        }
        else{
            throw new FetchException();
        }

        return filter;

    }


    @Override
    public Container fetch() throws  FetchException{ // TODO : do error validation, throw exceptions. Check OFY response codes
        if( this.id == null){
            throw new FetchException("GetDoodlesByIDCommand doodleId null");
        }
        LoadResult<Doodle> LoadResultOfID = ofy().load().type(getType()).id(this.id);
        ResultContainer<Doodle> resultContainer = new ResultContainer<Doodle>(LoadResultOfID);
        return resultContainer;
    }




    protected Class getType() {
        return Doodle.class;
    }
}
