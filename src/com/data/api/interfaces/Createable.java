package com.data.api.interfaces;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 3/30/16.
 */
public abstract class Createable<T>{

    protected abstract T getEntity() throws CreateException, FetchException;

    public T createEntity(FillDataCommand<T> data) throws CreateException{
        T entity = null;
        try {
            entity = getEntity();
        }
        catch (CreateException | FetchException ex){
            ex.printStackTrace();
            return null;
        }

        data.fillEntity(entity);
        ofy().save().entity(entity).now();

        return entity;
}


}
