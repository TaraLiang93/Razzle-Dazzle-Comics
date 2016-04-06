package com.data.api.DoodleQueries;

import com.data.api.Container;
import com.data.api.MapContainer;
import com.data.api.Readable;
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
    public Container fetch(){
        Map<Key<T>, T> mapOfT = ofy().load().keys(entityKey);
        MapContainer<T> TResultContainer = new MapContainer<T>(mapOfT);
        return TResultContainer;
    }
}
