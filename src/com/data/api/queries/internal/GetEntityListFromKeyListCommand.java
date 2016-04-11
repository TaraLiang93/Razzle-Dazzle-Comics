package com.data.api.queries.internal;

import com.data.api.containers.MapContainer;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.google.appengine.api.datastore.Query.Filter;
import com.googlecode.objectify.Key;

import java.util.List;
import java.util.Map;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 4/4/16.
 */
public class GetEntityListFromKeyListCommand<T> extends Readable<T>{


    List<Key<T>> listOfKeys; // listOfKeys to fetch the entities of

    public GetEntityListFromKeyListCommand( List<Key<T>> listOfKeys){
        this.listOfKeys = listOfKeys;
    }

    /**
     * Exists because the Readable requires it to be implemented but it will never be used
     * @return
     */
    @Override
    protected Filter getFilter() {
        return null;
    }

    /**
     * Exists because the Readable requires it to be implemented but it will never be used
     * @return
     */
    @Override
    protected Class<T> getType() {
        return null;
    }

    /**
     * fetch will return a MapContainer with the results
     * @return MapContainer containingthe map
     */
    @Override
    public Container fetch(){
        Map<Key<T>, T> mapOfT = ofy().load().keys(listOfKeys);
        MapContainer<T> TResultContainer = new MapContainer<T>(mapOfT);
        return TResultContainer;
    }
}
