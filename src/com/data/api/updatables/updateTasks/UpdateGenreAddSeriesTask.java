package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetSeriesByIDCommand;
import com.data.structure.Genre;
import com.data.structure.Series;
import com.googlecode.objectify.Key;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 5/13/16.
 */
public class UpdateGenreAddSeriesTask implements UpdateTask<Genre>{
    String seriesIdToAdd;
    public UpdateGenreAddSeriesTask( String seriesIdToAdd){
        this.seriesIdToAdd = seriesIdToAdd;
    }

    @Override
    public List<Genre> update(Container<Genre> entity) throws UpdateException, FetchException, CreateException {
        Genre genre = entity.getResult();
        Readable<Series> seriesReadable = new GetSeriesByIDCommand( seriesIdToAdd );
        Key<Series> seriesKey = seriesReadable.fetch().getResult().getKey();
        genre.addSeriesToSeriesList( seriesKey );

        List<Genre> genreList = new ArrayList<>();
        genreList.add( genre );
        return genreList;
    }
}
