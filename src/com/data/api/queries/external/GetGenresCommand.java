package com.data.api.queries.external;

import com.data.api.containers.MapContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.structure.Genre;
import com.google.appengine.api.datastore.Query.Filter;
import com.googlecode.objectify.Key;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 5/14/16.
 */
public class GetGenresCommand extends Readable<Genre>{

    @Override
    protected Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class<Genre> getType() {
        return Genre.class;
    }

    @Override
    public Container fetch() throws FetchException{
        Map<Key<Genre>, Genre> map = new HashMap<>();

        List<Genre> genreList = ofy().load().type( getType() ).list();

        for(Genre genre : genreList){
            map.put(genre.getKey(), genre);
        }

        MapContainer<Genre> genreMapContainer = new MapContainer<Genre>(map);
        return genreMapContainer;
    }

}
