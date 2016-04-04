package com.data.api;

import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 3/30/16.
 */
public abstract class Updateable<T> {

    public void updateEntity(Readable<T> r, UpdateTask<T> task) throws FetchException, UpdateException {
        Container<T> list = r.fetch();

        if(list == null) throw new FetchException();

        List<T> updates = task.update(list);

        if(updates == null || updates.isEmpty()) throw new UpdateException();

        try{
            ofy().save().entities(updates).now();
        }
        catch(Throwable t){

        }



    }

}
