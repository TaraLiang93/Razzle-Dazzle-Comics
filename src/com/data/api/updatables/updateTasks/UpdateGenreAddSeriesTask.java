package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
import com.data.structure.Genre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 5/13/16.
 */
public class UpdateGenreAddSeriesTask implements UpdateTask<Genre>{

    public UpdateGenreAddSeriesTask(){

    }

    @Override
    public List<Genre> update(Container<Genre> entity) throws UpdateException, FetchException, CreateException {
        Genre genre = entity.getResult();


        List<Genre> genreList = new ArrayList<>();
        genreList.add( genre );
        return genreList;
    }
}
