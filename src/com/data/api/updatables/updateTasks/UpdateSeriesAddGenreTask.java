package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetGenreByIDCommand;
import com.data.structure.Genre;
import com.data.structure.Series;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 5/14/16.
 */
public class UpdateSeriesAddGenreTask implements UpdateTask<Series> {

    String genreId;

    public UpdateSeriesAddGenreTask( String genreId){
        this.genreId = genreId;
    }

    @Override
    public List<Series> update(Container<Series> entity) throws UpdateException, FetchException, CreateException {
        Series series = entity.getResult();
        Readable<Genre> genreReadable = new GetGenreByIDCommand(genreId);
        Genre genre = genreReadable.fetch().getResult();


        series.addGenreToGenreList(genre.getKey());

        List<Series> seriesList = new ArrayList<>();
        seriesList.add(series);
        return seriesList;
    }
}
