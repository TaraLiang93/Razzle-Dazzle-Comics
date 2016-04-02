package com.data.api.DoodleQueries;

/**
 * Created by Zhenya on 4/1/16.
 */

import com.data.api.Readable;
import com.data.creation.Doodle;
import com.google.appengine.api.datastore.Query.*;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 *  This is the object for the DoodleQuery command getDoodles
 */

public class getDoodlesCommand extends Readable {

    public getDoodlesCommand(){}

    //getDoodles with a specific tag

    @Override
    protected Filter getFilter() {
        //create filter
        Filter doodleIDFilter = new FilterPredicate("", FilterOperator.EQUAL, "" );
        return doodleIDFilter;
    }

    @Override
    protected Class<?> getType() {
        return Doodle.class;
    }


}

