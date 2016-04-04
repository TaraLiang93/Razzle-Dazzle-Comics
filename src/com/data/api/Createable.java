package com.data.api;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 3/30/16.
 */
public abstract class Createable<T>{

    protected abstract T getEntity();
    public void createEntity(DataItem<T> data){
        T entity = getEntity();
        data.fillEntity(entity);
        ofy().save().entity(entity).now();
}


}
