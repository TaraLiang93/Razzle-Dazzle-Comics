package com.data.api.queries.external;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.structure.Series;
import com.google.appengine.api.datastore.Query.Filter;
import com.googlecode.objectify.Key;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<Key<Series>, Series> map = new HashMap<>();

    }
}
