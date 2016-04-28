package com.data.api.interfaces;

import com.data.api.containers.EntityContainer;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 3/30/16.
 */
public class Updateable<T> {

    public void updateEntity(Readable<T> r, UpdateTask<T> task) throws FetchException, UpdateException, CreateException{

        Container<T> list = r.fetch();

        if(list == null) throw new FetchException();
        //updates is the list of entities to update
        List<T> updates = task.update(list);

        if(updates == null || updates.isEmpty()) throw new UpdateException();

        try{
            // save all the entities to update
            ofy().save().entities(updates).now();
        }
        catch(Throwable t){
            t.printStackTrace();
        }

    }

    public void updateEntity(T ent, UpdateTask<T> task) throws CreateException, FetchException, UpdateException{
        if(ent == null) throw new UpdateException("Cannot be null");

        task.update(new EntityContainer<T>(ent));

        try{
            // save all the entities to update
            ofy().save().entity(ent).now();
        }
        catch(Throwable t){
            t.printStackTrace();
        }
    }

}
