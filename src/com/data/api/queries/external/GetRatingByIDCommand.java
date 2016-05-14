package com.data.api.queries.external;

import com.data.api.containers.ResultContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.structure.Rating;
import com.google.appengine.api.datastore.Query.Filter;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 5/13/16.
 */
public class GetRatingByIDCommand extends Readable {

    Long id;

    public GetRatingByIDCommand(Long id){
        this.id = id;
    }
    public GetRatingByIDCommand(String id){
        try {
            this.id = Long.parseLong(id);
        }
        catch ( NumberFormatException e){
            e.printStackTrace();
        }
    }

    @Override
    protected Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class getType() {
        return Rating.class;
    }

    @Override
    public Container fetch() throws  FetchException{
        if( this.id == null){
            throw new FetchException("rating id null");
        }
        LoadResult<Rating> LoadResultOfID = ofy().load().type(getType()).id(this.id);
        ResultContainer<Rating> resultContainer = new ResultContainer<Rating>(LoadResultOfID);
        return resultContainer;
    }
}
