package com.data.api.queries.external;

import com.data.api.containers.MapContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.structure.Series;
import com.google.appengine.api.datastore.Query;
import com.googlecode.objectify.Key;
import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 5/15/16.
 */
public class GetSeriesFromSearch extends Readable{

    String searchItems[];

    public GetSeriesFromSearch(String searchItems){
        if(searchItems.equals(" "))
            this.searchItems = searchItems.split(" ");// String by spaces
        else
        {
            this.searchItems = new String[1];
            this.searchItems[0] = searchItems;
        }
    }


    @Override
    protected Query.Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class getType() { return Series.class;}

    @Override
    public Container fetch() throws FetchException {

        if( searchItems == null || searchItems.length == 0){
            throw new FetchException("genreNameList invalid");
        }

        List<Series> seriesList = new ArrayList<>();

        Readable<Series> seriesReadable = new GetSeriesCommand();
        List<Series> allSeries = seriesReadable.fetch().getList();

        for(Series series : allSeries){

            for(String search : this.searchItems){

                String title = series.getTitle().toLowerCase();
                if(title.contains(search.toLowerCase())){
                    seriesList.add(series);
                    break;
                }
            }
        }

        Map<Key<Series>, Series> map = new HashMap<>();
        for(Series series : seriesList){
            map.put(series.getKey(), series);
        }



        MapContainer<Series> seriesMapContainer = new MapContainer<>(map);
        return seriesMapContainer;
    }
}
