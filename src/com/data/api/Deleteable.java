package com.data.api;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 3/30/16.
 */
public abstract class Deleteable<T> {

    public void delete(Readable<T> query){
        Container<T> result = query.fetch();

        for(T elem : result.getList()){
            System.out.println("Deleting Element : " + elem);
            hook(elem); //See if the user needs to do anything before we delete it
            ofy().delete().entity(elem);
        }
    }

    protected abstract void hook(T item);

}