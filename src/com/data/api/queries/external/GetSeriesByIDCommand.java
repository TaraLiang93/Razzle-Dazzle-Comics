package com.data.api.queries.external;

import com.data.api.containers.ResultContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.structure.Series;
import com.google.appengine.api.datastore.Query.Filter;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 4/13/16.
 */
public class GetSeriesByIDCommand extends Readable{
    Long seriesId;

    public GetSeriesByIDCommand( Long seriesId){
        this.seriesId = seriesId;
    }

    public GetSeriesByIDCommand( String strSeriesId){
        try {
            this.seriesId = Long.parseLong(strSeriesId);
        }
        catch ( NumberFormatException ex){
            ex.printStackTrace();
        }
    }


    @Override
    protected Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class getType() {
        return Series.class;
    }

    @Override
    public Container fetch() throws  FetchException{ // TODO : do error validation, throw exceptions. Check OFY response codes
        if( this.seriesId == null){
            throw new FetchException("GetSeriesByIdCommand seriesId null");
        }
        LoadResult<Series> LoadResultOfID = ofy().load().type(getType()).id(this.seriesId);
        ResultContainer<Series> resultContainer = new ResultContainer<Series>(LoadResultOfID);
        return resultContainer;
    }


}
