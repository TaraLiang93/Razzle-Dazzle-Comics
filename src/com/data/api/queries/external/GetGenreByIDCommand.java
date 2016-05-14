package com.data.api.queries.external;

import com.data.api.containers.ResultContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.structure.Genre;
import com.google.appengine.api.datastore.Query;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 5/13/16.
 */
public class GetGenreByIDCommand extends Readable {
    Long id;

    public GetGenreByIDCommand(Long id){
        this.id = id;
    }
    public GetGenreByIDCommand(String id){
        try {
            this.id = Long.parseLong(id);
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    @Override
    protected Query.Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class getType() {
        return Genre.class;
    }

    @Override
    public Container fetch() throws  FetchException{
        if( this.id == null){
            throw new FetchException("genre id null");
        }

        LoadResult<Genre> LoadResultOfID = ofy().load().type(getType()).id(this.id);
        ResultContainer<Genre> resultContainer = new ResultContainer<Genre>(LoadResultOfID);
        return resultContainer;
    }
}
