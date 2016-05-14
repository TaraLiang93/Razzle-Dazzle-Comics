package com.data.api.queries.external;


import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.structure.Series;
import com.google.appengine.api.datastore.Query.Filter;

/**
 * Created by Zhenya on 5/1/16.
 */
public class GetTop100SeriesCommand extends Readable {
    //TODO Need to do Ratings first
    @Override
    protected Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class getType() {
        return Series.class;
    }
}
