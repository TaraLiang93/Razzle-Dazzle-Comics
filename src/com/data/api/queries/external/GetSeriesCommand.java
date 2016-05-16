package com.data.api.queries.external;

import com.data.api.containers.MapContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;

import com.data.structure.Series;
import com.google.appengine.api.datastore.Query.Filter;
import com.googlecode.objectify.Key;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 5/15/16.
 */
public class GetSeriesCommand extends Readable{


    @Override
    protected Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class<Series> getType() { return Series.class; }

    @Override
    public Container fetch() throws FetchException{
        Map<Key<Series>, Series> map = new HashMap<>();

        List<Series> genreList = ofy().load().type( getType() ).list();

        for(Series series : genreList){
            map.put(series.getKey(), series);
        }

        MapContainer<Series> seriesMapContainer = new MapContainer<Series>(map);
        return seriesMapContainer;
    }

}
