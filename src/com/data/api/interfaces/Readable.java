package com.data.api.interfaces;


import com.data.api.containers.QueryContainer;
import com.google.appengine.api.datastore.Query.Filter;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 3/30/16.
 */
public abstract class Readable<T> {

    public Readable(){
        // ofy.load
//        Hi james
    }

    // every readable object will have a filter
    abstract protected Filter getFilter();

    // every readable object will have a method which will returns it's type
    // This is so that we can create the Filter objects for the query
    abstract protected Class<T> getType();

    /**
     *
     * @return results the QueryContainer<T> containing the query
     */
    public Container<T> fetch() {
        Query query = ofy().load().type(getType()).filter(getFilter()); // returns the results as a Query Object
        Container<T> results = new QueryContainer(query); // pass the Query Object into QueryContainer for returning later
        return results;
    }

    /**
     *  fetch an entity via key query
     * @param theId
     * @return the entity
     */
    public T fetchById( Long theId){
        return ofy().load().type(getType()).id(theId).now();
    }


    public T fetchByKey(Key<T> key){
        return ofy().load().key(key).now();
    }
}
