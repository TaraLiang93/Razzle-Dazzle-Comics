package com.data.api.queries.external;

import com.data.api.containers.MapContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.structure.Series;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.googlecode.objectify.Key;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.googlecode.objectify.ObjectifyService.ofy;



/**
 * Created by Zhenya on 5/1/16.
 */
public class GetLatestReleasedSeriesCommand extends Readable {
    int numberOfSeries;

    public GetLatestReleasedSeriesCommand( int numberOfSeries){
        this.numberOfSeries = numberOfSeries;
    }

    @Override
    protected Filter getFilter() throws FetchException {
        // filter on the published bolean;
        Filter filter = new FilterPredicate("published",
                FilterOperator.EQUAL,
                true);
        return filter;
    }

    @Override
    protected Class getType() {
        return Series.class;
    }

    @Override
    public Container fetch() throws FetchException{

        Map<Key<Series>, Series> map = new HashMap<>();

//        Query q = ofy().load().type(getType()).filter(getFilter());
//        List<Series> seriesList1 = q.list();

        List<Series> seriesList2 = ofy().load().type(getType()).filter( getFilter() ).order("-createDate").limit(this.numberOfSeries).list();

        for( Series series: seriesList2 ){
            map.put(series.getKey(), series);
        }

        MapContainer<Series> seriesMapContainer = new MapContainer<>(map);

        return seriesMapContainer;
    }

}
