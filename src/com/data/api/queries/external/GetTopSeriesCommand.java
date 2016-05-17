package com.data.api.queries.external;

import com.data.api.containers.MapContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.structure.Series;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.googlecode.objectify.Key;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 5/14/16.
 */
public class GetTopSeriesCommand extends Readable {

    int numberOfSeries;
    public GetTopSeriesCommand( int numberOfSeries){
        this.numberOfSeries = numberOfSeries;
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
    public Container fetch() throws FetchException{
        Map<Key<Series>, Series> map = new HashMap<>();

        Filter publishedFilter = new Query.FilterPredicate( "published",
                Query.FilterOperator.EQUAL,
                true
        );

        // order based on views field
        List<Series> seriesList2 = ofy().load().type(getType()).filter(publishedFilter).order("-views").limit(this.numberOfSeries).list();

        for( Series series: seriesList2 ){
            map.put(series.getKey(), series);
        }

        MapContainer<Series> seriesMapContainer = new MapContainer<>(map);

        return seriesMapContainer;
    }
}
