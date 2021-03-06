package com.data.api.queries.internal;

import com.data.api.containers.MapContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.google.appengine.api.datastore.Query;
import com.googlecode.objectify.Key;

import java.util.Map;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 4/5/16.
 */
public class GetEntityFromKeyCommand<T> extends Readable<T> {
    Key<T> entityKey;

    public GetEntityFromKeyCommand(Key<T> entityKey){
        this.entityKey = entityKey;
    }

    /**
     * Exists because the Readable requires it to be implemented but it will never be used
     * @return
     */
    @Override
    protected Query.Filter getFilter() {
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

    @Override
    public Container fetch() throws FetchException{// TODO : do error validation, throw exceptions
        Map<Key<T>, T> mapOfT = ofy().load().keys(entityKey);
        if(mapOfT == null){
            throw new FetchException("GetEntityFromKeyCommand mapOfT null");
        }
        MapContainer<T> TResultContainer = new MapContainer<T>(mapOfT);

        return TResultContainer; // TODO : have this one use the getEntityListFromKeyList
    }
}
