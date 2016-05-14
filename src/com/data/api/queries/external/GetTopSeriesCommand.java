package com.data.api.queries.external;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.structure.Series;
import com.google.appengine.api.datastore.Query.Filter;

/**
 * Created by Zhenya on 5/14/16.
 */
public class GetTopSeriesCommand extends Readable {

    public GetTopSeriesCommand(){

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

    }
}
