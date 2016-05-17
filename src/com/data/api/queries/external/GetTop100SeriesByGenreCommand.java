package com.data.api.queries.external;

import com.data.api.containers.MapContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.queries.Comparators.SeriesViewsComparator;
import com.data.api.queries.internal.GetEntityListFromKeyListCommand;
import com.data.structure.Genre;
import com.data.structure.Series;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.googlecode.objectify.Key;

import java.util.*;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 5/1/16.
 */
public class GetTop100SeriesByGenreCommand extends Readable{
    int numberOfSeries;
    List<String> genreNameList;

    public GetTop100SeriesByGenreCommand(int numberOfSeries, List<String> genreNameList){
        this.numberOfSeries = numberOfSeries;
        this.genreNameList = genreNameList;
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
        if( genreNameList == null || genreNameList.size() == 0){
            throw new FetchException("genreNameList invalid");
        }



        List<Series> seriesList = new ArrayList<>();
        for( String genreName: genreNameList){
            //query for the genre
            Filter filter = new FilterPredicate("name",
                    FilterOperator.EQUAL,
                    genreName);

            Filter publishedFilter = new FilterPredicate( "published",
                    FilterOperator.EQUAL,
                    true
                    );

            Genre genre = ofy().load().type(Genre.class).filter( filter ).filter(publishedFilter).first().now();
            Readable<Series> seriesReadable = new GetEntityListFromKeyListCommand<>( genre.getSeriesList() );
            seriesList.addAll( seriesReadable.fetch().getList() );

        }

        Collections.sort( seriesList , new SeriesViewsComparator() );

        Map<Key<Series>, Series> map = new HashMap<>();
        for( int i = 0; i < numberOfSeries && i< seriesList.size(); i++){
            Series series = seriesList.get(i);
            map.put( series.getKey(), series );
        }

        MapContainer<Series> seriesMapContainer = new MapContainer<>(map);
        return seriesMapContainer;

    }
}
